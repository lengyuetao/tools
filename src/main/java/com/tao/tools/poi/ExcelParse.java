package com.tao.tools.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelParse {

	public final static String CONTRACT_ADDRESS_STC = "0x8f136cc8bef1fea4a7b71aa2301ff1a52f084384";
	
	private static List<String> resList;
	
	private static Set<Object> repeatSet;
	private static Set<Object> noRepeatSet;
	

	static{
		resList=new ArrayList<>();
		repeatSet=new TreeSet<>();
		noRepeatSet=new TreeSet<>();
	}
	
	public static void main(String[] args) {	   
        readExcel("E:/coin");
	
	}
	
	
	/**
	 * 解析excel数据
	 * @param
	 * @param fileName
	 */
	public static void  readExcel(String fileName){
        XSSFWorkbook wb=null;
		FileInputStream in=null;
		FileOutputStream excelFileOutPutStream=null;
		try {
			File file=new File(fileName);
			if(!file.isDirectory()){
				System.out.println("不是文件夹");
				return;
			}
			File[] f=file.listFiles();
			
			for(int i=0;i<f.length;i++){
				in=new FileInputStream(f[i]);
				wb=new XSSFWorkbook(in);
				
				parseExcel(wb,f.length);	
				wb.close();
				in.close();
			}
			
			
			for(String str:resList){
				if(!noRepeatSet.contains(str)){
					noRepeatSet.add(str);
				}else{
					repeatSet.add(str);
				}
			}
			
		
		for(int index=0;index<f.length;index++){	
			in=new FileInputStream(f[index]);
			wb=new XSSFWorkbook(in);
			CellStyle style=wb.createCellStyle();
		    style.setFillForegroundColor(IndexedColors.RED.getIndex());  
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);  
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	            Sheet sheet=wb.getSheetAt(i);
				if(sheet==null) continue;
				
				for(int j=4;j<sheet.getLastRowNum();j++){
					Row row=sheet.getRow(j);
					
					if(repeatSet.contains(row.getCell(2).toString())){
						row.getCell(1).setCellStyle(style);
					}
					String address=row.getCell(2).getStringCellValue().replaceAll("\\s*", "");
				    
					address=StringUtils.deleteWhitespace(address);
					
					if(StringUtils.isNotBlank(address)){
						System.out.println("***"+address);
					}else{
						row.createCell(5).setCellValue(0);
					}
				}
				System.out.println("表"+index);
			}	
			excelFileOutPutStream = new FileOutputStream(f[index]);
			wb.write(excelFileOutPutStream);  
			excelFileOutPutStream.flush();  
			excelFileOutPutStream.close();  
			wb.close();
			in.close();
		
		 }				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件不存在："+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally{
			try {
				if(in!=null){
					in.close();
				}
				if(wb!=null){
					wb.close();
				}
				if(excelFileOutPutStream!=null){
					excelFileOutPutStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	private static void parseExcel(XSSFWorkbook wb,int total){		
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet=wb.getSheetAt(i);
			if(sheet==null) continue;
			
			for(int j=4;j<sheet.getLastRowNum();j++){
				Row row=sheet.getRow(j);
				if(StringUtils.isEmpty(row.getCell(2).toString())){
					continue;
				}
				
				if(total>0){
					resList.add(row.getCell(2).toString());
				}
				
			}
		}
	}
}
