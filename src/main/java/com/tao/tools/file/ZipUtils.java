package com.tao.tools.file;


import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhangantao on 2017/4/12.
 * @descripe 文件压缩工具
 */
public class ZipUtils {

    private static byte[] hexBase={ '0','1','2','3',
            '4','5','6','7',
            '8','9','a','b',
            'c','d','e','f'};

    /**
     * 压缩单个文件
     * @param filePath
     * @param zipPath
     */
    public static void zipFile(String filePath,String zipPath){

        File file=new File(filePath);

        File zipFile=new File(zipPath);

        try {

            InputStream input=new FileInputStream(file);

            ZipOutputStream zipOut=new ZipOutputStream(new FileOutputStream(zipFile));

            zipOut.putNextEntry(new ZipEntry(file.getName()));

            int temp=0;

            while((temp=input.read())!=-1){

                zipOut.write(temp);

            }

            input.close();

            zipOut.close();

            System.out.println("压缩完成...");

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    /**
     * 压缩多个文件
     * @param filePath
     * @param zipPath
     */
    public static void zipMutiFile(String filePath,String zipPath){

        File file=new File(filePath);

        File zipFile=new File(zipPath);

        InputStream input=null;

        try {

            ZipOutputStream zipOut=new ZipOutputStream(new FileOutputStream(zipFile));

            if(file.isDirectory()){

                File[] files=file.listFiles();

                for (int i = 0; i < files.length; i++) {

                    input=new FileInputStream(files[i]);

                    zipOut.putNextEntry(new ZipEntry(file.getName()+File.separator+files[i].getName()));

                    int temp=0;

                    while((temp=input.read())!=-1){

                        zipOut.write(temp);

                    }

                    input.close();
                }
            }

            zipOut.close();

            System.out.println("压缩完成...");

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    public static String zipFileMd5(String fileName){

        FileInputStream fis=null;

        String rc="";

        try
        {

            byte buff[]=new byte[4096];

            MessageDigest messagedigest = MessageDigest.getInstance("MD5");

            fis=new FileInputStream(fileName);

            int len=0;

            while((len=fis.read(buff))>0)
            {

                messagedigest.update(buff,0,len);

            }

            fis.close();

            fis=null;

            byte result[] = messagedigest.digest();

            rc=byte2Hex(result);

        }
        catch(IOException ex)
        {

            System.out.println("加密类型: "+ex.getMessage());

        }
        catch (NoSuchAlgorithmException e)
        {

            System.out.println("加密类型: "+e.getMessage());

        }
        finally
        {

            try{ if(fis!=null) fis.close();} catch(IOException e){System.out.println("加密类型:"+e.getMessage());}

        }

        return rc;
    }


    public static String byte2Hex(byte b[])
    {

        if(b==null) return "";

        StringBuffer tmp=new StringBuffer();

        int len=b.length;

        for(int i=0;i<len;i++)
        {

            tmp.append((char)hexBase[(b[i] & 0xF0)>>4]);

            tmp.append((char)hexBase[b[i] & 0x0F]);

        }

        while(tmp.length()<16) tmp.append("00");

        return tmp.toString();
    }




    public static void main(String[] args) {
        //zipFile("E://netbarInfo.txt", "E://tao.zip");
        zipMutiFile("C:\\Users\\DELL\\Desktop\\Test.class", "C:\\Users\\DELL\\Desktop\\Test.zip");

    }
}
