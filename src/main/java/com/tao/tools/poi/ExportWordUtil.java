package com.tao.tools.poi;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import sun.misc.BASE64Decoder;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 导出Excel公共方法
 *
 * @author lyq
 */
public class ExportWordUtil {


    /**
     * 导出word
     * @param base64Info 报表图片数据
     * @return
     */
    public XWPFDocument export(String applicant,List<String> base64Info) {
        try {
            CustomXWPFDocument xdoc = new CustomXWPFDocument();
            // 创建页眉
            createCtp(xdoc);
            // 标题
            createTitle(xdoc);
            //添加一个段落
            XWPFParagraph p0=xdoc.createParagraph();
            XWPFRun r0=p0.createRun();
            r0.setText("");
            //添加一个段落
            XWPFParagraph p1=xdoc.createParagraph();
            XWPFRun r1=p1.createRun();
            r1.setFontFamily("仿宋");
            r1.setText("单位名称: "+"上标科技有限公司");
            r1.setFontSize(14);
            //添加一个段落
            XWPFParagraph p2=xdoc.createParagraph();
            XWPFRun r2=p2.createRun();
            r2.setFontFamily("仿宋");
            r2.setText("报告时间: "+ getStringDate());
            r2.setFontSize(14);
            //添加一个段落
            XWPFParagraph p3=xdoc.createParagraph();
            XWPFRun r3=p3.createRun();
            r3.setFontFamily("仿宋");
            r3.setText("分析平台: "+"管知网www.gzwip.com");
            r3.setFontSize(14);
            //XWPFTable dTable = xdoc.createTable(3, 2);
            //createBaseInfoTable(dTable, xdoc, getStringDate(),	"管知网www.gzwip.com");
            //添加一个段落
            XWPFParagraph p4=xdoc.createParagraph();
            XWPFRun r4 = p4.createRun();
            r4.setText("");
            // 标题一、未来科技数据统计分析
            createTitle(xdoc, applicant+"知识产权数据统计分析");
            XWPFParagraph p5=xdoc.createParagraph();
            XWPFRun r5 = p5.createRun();
            r5.setText("");
            // 报表数据分析
            XWPFTable dataReportTable = xdoc.createTable();
            for (String echartSource :base64Info){
                byte[] bytes = decodeBase64(echartSource);
                createDataReportTable(dataReportTable, xdoc, bytes);
            }
            return xdoc;
        } catch (Exception e) {
            throw new RuntimeException("生成文件失败");
        }
    }

    /**
     * 在cell 里面插入图片
     * @param xdoc
     * @param paragraph
     * @param imageByte
     */
    private void createPic(CustomXWPFDocument xdoc, XWPFParagraph paragraph, byte[] imageByte) {
        try {
            xdoc.addPictureData(imageByte, XWPFDocument.PICTURE_TYPE_JPEG);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        xdoc.createPicture(paragraph, xdoc.getAllPictures().size() - 1, 560, 480, "    ");
    }

    // 图片到byte数组
    public byte[] image2byte(String path) {
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    /**
     * 创建标题
     */
    private void createTitle(CustomXWPFDocument xdoc) {
        // 标题
        XWPFParagraph titleMes = xdoc.createParagraph();
        titleMes.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = titleMes.createRun();
        r1.setBold(true);
        r1.setFontFamily("华文仿宋");
        r1.setText("管知网知识产权分析报告");// 活动名称
        r1.setFontSize(18);
        r1.setColor("333333");
        r1.setBold(true);
    }

    /**
     * 生成页眉
     */
    public void createCtp(CustomXWPFDocument document) {
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

       /* // 添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "www.gzwip.com";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        // 设置为左对齐
        headerParagraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        try {
            policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        //添加页脚
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "www.gzwip.com";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        // 设置为左对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        try {

            XWPFFooter xwpfFooter = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成基础信息Table
     *
     * @param table
     * @param xdoc
     */
    public void createBaseInfoTable(XWPFTable table, CustomXWPFDocument xdoc, String reportingtime,String analysisplatform) {
        String bgColor = "111111";
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        tblWidth.setW(new BigInteger("8600"));
        tblWidth.setType(STTblWidth.AUTO); // STTblWidth.AUTO 自动长度

        setCellText(xdoc, getCellHight(table, 0, 0, 600), "报告时间:", bgColor, 1280, 14, "仿宋");
        setCellText(xdoc, getCellHight(table, 0, 1, 600), reportingtime, bgColor, 2800, 14, "仿宋");
        setCellText(xdoc, getCellHight(table, 1, 0, 600), "分析平台:", bgColor, 1280, 14, "仿宋");
        setCellText(xdoc, getCellHight(table, 1, 1, 600), analysisplatform, bgColor, 2800, 14, "仿宋");
        setCellText(xdoc, getCellHight(table, 2, 0, 600), " ", bgColor, 1280, 14, "仿宋");
        setCellText(xdoc, getCellHight(table, 2, 1, 600), " ", bgColor, 2400, 14, "仿宋");
    }

    /**
     * 生成标题
     *
     * @param xdoc
     * @param titleText
     */
    public void createTitle(CustomXWPFDocument xdoc, String titleText) {
        XWPFParagraph headLine2 = xdoc.createParagraph();
        headLine2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun runHeadLine2 = headLine2.createRun();
        runHeadLine2.setText(titleText);
        runHeadLine2.setFontSize(16);
        runHeadLine2.setFontFamily("华文仿宋");
        runHeadLine2.setBold(true);
        runHeadLine2.setColor("333333");
    }



    /**
     * 报表数据分析
     *
     * @param table
     * @param xdoc
     */
    public void createDataReportTable(XWPFTable table, CustomXWPFDocument xdoc, byte[] base64Info1) {
        setCellPic(xdoc, getCellHight(table, 0, 0, 1200), base64Info1);
    }


    // 设置表格高度
    private XWPFTableCell getCellHight(XWPFTable xTable, int rowNomber, int cellNumber, int hight) {

        CTTblBorders borders=xTable.getCTTbl().getTblPr().addNewTblBorders();
        CTBorder hBorder=borders.addNewInsideH();
        hBorder.setVal(STBorder.Enum.forString("none"));
        hBorder.setSz(new BigInteger("1"));
        hBorder.setColor("0000FF");
        hBorder.setSpace(BigInteger.ZERO);
        CTBorder vBorder=borders.addNewInsideV();
        vBorder.setVal(STBorder.Enum.forString("none"));
        vBorder.setSz(new BigInteger("1"));
        vBorder.setColor("00FF00");
        hBorder.setSpace(BigInteger.ZERO);
        CTBorder lBorder=borders.addNewLeft();
        lBorder.setVal(STBorder.Enum.forString("none"));
        lBorder.setSz(new BigInteger("1"));
        lBorder.setColor("3399FF");
        hBorder.setSpace(BigInteger.ZERO);
        CTBorder rBorder=borders.addNewRight();
        rBorder.setVal(STBorder.Enum.forString("none"));
        rBorder.setSz(new BigInteger("1"));
        rBorder.setColor("F2B11F");
        hBorder.setSpace(BigInteger.ZERO);
        CTBorder tBorder=borders.addNewTop();
        tBorder.setVal(STBorder.Enum.forString("none"));
        tBorder.setSz(new BigInteger("1"));
        tBorder.setColor("C3599D");
        hBorder.setSpace(BigInteger.ZERO);
        CTBorder bBorder=borders.addNewBottom();
        bBorder.setVal(STBorder.Enum.forString("none"));
        bBorder.setSz(new BigInteger("1"));
        bBorder.setColor("F7E415");
        XWPFTableRow row = null;
        row = xTable.getRow(rowNomber);
        row.setHeight(hight);
        XWPFTableCell cell = null;

        cell = row.getCell(cellNumber);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        //cell.setColor("CCCCCC");
        return cell;
    }

    /**
     * 创建图片
     */
    private void setCellPic(CustomXWPFDocument xdoc, XWPFTableCell cell, byte[] imageByte) {
        createPic(xdoc, cell.addParagraph(), imageByte);
    }

    private void setCellText(CustomXWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor, int width,
                             int fontSize, String textType) {
        setCellText(xDocument, cell, text, bgcolor, width, fontSize, textType, ParagraphAlignment.CENTER);
    }

    /**
     *
     * @param xDocument
     * @param cell
     * @param text
     * @param bgcolor
     * @param width
     */
    private void setCellText(CustomXWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor, int width,
                             int fontSize, String textType, ParagraphAlignment align) {
        setCellText(xDocument, cell, text, bgcolor, width, fontSize, textType, align, false);
    }

    private void setCellText(CustomXWPFDocument xDocument, XWPFTableCell cell, String text, String bgcolor, int width,
                             int fontSize, String textType, ParagraphAlignment align, boolean isBold) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));
        XWPFParagraph pIO = cell.addParagraph();
        if (null == align) {
            pIO.setAlignment(ParagraphAlignment.CENTER);
        } else {
            pIO.setAlignment(align);
        }
        cell.removeParagraph(0);

        if (text.contains("\n")) {
            String[] myStrings = text.split("\n");
            for (int i = 0; i < myStrings.length; i++) {
                String temp = myStrings[i];
                if (isBold) {
                    if (i == 0) {
                        setTextStyle(pIO, textType, bgcolor, fontSize, temp, true, true);
                    } else {
                        setTextStyle(pIO, textType, bgcolor, fontSize, "      " + temp, true, false);
                    }
                } else {
                    setTextStyle(pIO, textType, bgcolor, fontSize, temp, true, false);
                }
            }
        } else {
            setTextStyle(pIO, textType, bgcolor, fontSize, text, false, false);
        }
    }

    private void setTextStyle(XWPFParagraph pIO, String textType, String bgcolor, int fontSize, String text,
                              boolean isEntery, boolean isBold) {
        XWPFRun rIO = pIO.createRun();
        if (textType == null || textType.equals("")) {
            rIO.setFontFamily("微软雅黑");
        } else {
            rIO.setFontFamily(textType);
        }
        if (bgcolor == null || bgcolor.equals("")) {
            rIO.setColor("000000");
        } else {
            rIO.setColor(bgcolor);
        }
        rIO.setFontSize(fontSize);
        rIO.setText(text);
        if (isBold)
            rIO.setBold(true);
        if (isEntery)
            rIO.addBreak();
    }

    // 设置表格间的空行
    public void setEmptyRow(CustomXWPFDocument xdoc, XWPFRun r1) {
        XWPFParagraph p1 = xdoc.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        p1.setVerticalAlignment(TextAlignment.CENTER);
        r1 = p1.createRun();
    }

    // word跨列合并单元格
    public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    // word跨行并单元格
    public void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 解析base64，返回图片所在路径
     *
     * @param base64Info
     * @return
     */
    private byte[] decodeBase64(String base64Info) {
        if (StringUtils.isEmpty(base64Info)) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        if (!base64Info.contains("base64,"))
            return null;
        String[] arr = base64Info.split("base64,");
        // 数据中：data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABI4AAAEsCAYAAAClh/jbAAA
        // ... 在"base64,"之后的才是图片信息
        try {
            return decoder.decodeBuffer(arr[1]);
        } catch (IOException e) {
            return null;
        }
    }

}