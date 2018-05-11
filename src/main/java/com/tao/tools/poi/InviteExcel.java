package com.tao.tools.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import com.tao.tools.http.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class InviteExcel {

	public static void exportExcel(String pathName){

		String res= HttpUtil.get("");

		JSONObject json=JSONObject.parseObject(res);
		
		if(json.getString("code").equals("001")){
			JSONArray list=json.getJSONObject("data").getJSONArray("list");
			
			 XSSFWorkbook wb=new XSSFWorkbook();
			 FileOutputStream output=null;
			 
			 Sheet sheet=wb.createSheet();
			 Row row0=sheet.createRow(0);
			 
			 String[] titleName=new String[]{"ID","微信号","钱包地址","填写余额","邀请码","时间","实际余额"};
			 for(int i=0;i<titleName.length;i++){
				 sheet.setColumnWidth(i, 10 * 512);  
				 row0.createCell(i).setCellValue(titleName[i]);
			 }
			 
			 JSONObject jst=null;
			 for(int i=0;i<list.size();i++){
				 jst=list.getJSONObject(i);
				 Row row=sheet.createRow(i+1);
				 row.createCell(0).setCellValue(jst.getString("id"));
				 row.createCell(1).setCellValue(jst.getString("wxNo"));
				 row.createCell(2).setCellValue(jst.getString("address"));
				 row.createCell(3).setCellValue(jst.getString("value"));
				 row.createCell(4).setCellValue(jst.getString("inviteCode"));
				 row.createCell(5).setCellValue(jst.getString("createTm"));
				 
				 String address=StringUtils.deleteWhitespace(jst.getString("address"));
			 }
			
			 try {
				 File file=new File(pathName);
				 if(!file.exists()){
					 file.mkdir();
				 }
				 output = new FileOutputStream(file+"/invite.xlsx");
				 wb.write(output);
				 output.flush();  
				 output.close();  
				 wb.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}finally{
				try {
					if(wb!=null){
						wb.close();
					}
					if(output!=null){
						output.close();
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}  
			
		}
	}
	
	public static void main(String[] args) {
		exportExcel("E:/invite");
	}
}
