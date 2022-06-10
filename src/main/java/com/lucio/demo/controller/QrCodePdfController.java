package com.lucio.demo.controller;

import com.lucio.demo.utils.QRCode.QRCodeUtils;
import com.lucio.demo.utils.XlsToPdf.Xls2Pdf;
import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Date 2021-07-07 16:28
 * @Version  1.0
 */
@RestController
@RequestMapping("/api/pdf/info")
public class QrCodePdfController {

    public static Logger log = Logger.getLogger(QrCodePdfController.class);

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletRequest httpRequest, HttpServletResponse response) throws IOException {
        String code = "1";
        String userAgent = httpRequest.getHeader("User-Agent");
        String fileName = "demo表.pdf";
        if (userAgent.contains("MSIE") || userAgent.contains("Chrome") || userAgent.contains("Trident")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }

        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        HSSFWorkbook workbook = createExcel();
        Xls2Pdf xls2Pdf = new Xls2Pdf(workbook, 0);
        if(code.equals("1")) {
            //生成pdf
            try {
                xls2Pdf.createPdf(response.getOutputStream());
            } catch (DocumentException e) {
                log.error("生成PDF失败");
            }
        }else{
            //生成excel
            workbook.write(response.getOutputStream());
        }
    }
    private HSSFWorkbook createExcel() {
        String qrcode = "我是二维码内容";
        String lawCode = "法体名称";
        String comCode = "公司名称";
        String code = "2021070600010002";
        String date = "2021-07-06 11:09:00";
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");

        HSSFWorkbook workbook = new HSSFWorkbook();

        // 单元格格式
        CellStyle borderAndMiddleAndBond = workbook.createCellStyle();

        CellStyle headerTitle = workbook.createCellStyle();

        CellStyle tableHeader = workbook.createCellStyle();

        CellStyle amount = workbook.createCellStyle();

        CellStyle title = workbook.createCellStyle();

        Font fontBond = workbook.createFont();
        fontBond.setBold(true);
        fontBond.setFontName("微软雅黑");
        fontBond.setFontHeight((short) 200);
        fontBond.setColor((short) 8);

        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeight((short) 140);
        font.setColor((short) 8);

        //上标题字体
        Font titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleFont.setFontHeight((short) 140);
        titleFont.setColor((short) 8);

        //头部
        borderAndMiddleAndBond.setBorderBottom(BorderStyle.NONE); //下边框
        borderAndMiddleAndBond.setBorderLeft(BorderStyle.NONE);//左边框
        borderAndMiddleAndBond.setBorderTop(BorderStyle.NONE);//上边框
        borderAndMiddleAndBond.setBorderRight(BorderStyle.NONE);//右边框
        borderAndMiddleAndBond.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        borderAndMiddleAndBond.setAlignment(HorizontalAlignment.CENTER);// 水平
        borderAndMiddleAndBond.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        borderAndMiddleAndBond.setFont(fontBond);

        headerTitle.setBorderBottom(BorderStyle.NONE); //下边框
        headerTitle.setBorderLeft(BorderStyle.NONE);//左边框
        headerTitle.setBorderTop(BorderStyle.NONE);//上边框
        headerTitle.setBorderRight(BorderStyle.NONE);//右边框
        headerTitle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        headerTitle.setAlignment(HorizontalAlignment.CENTER);// 水平
        headerTitle.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        headerTitle.setFont(titleFont);

        //表格头部
        tableHeader.setBorderBottom(BorderStyle.THIN); //下边框
        tableHeader.setBorderLeft(BorderStyle.THIN);//左边框
        tableHeader.setBorderTop(BorderStyle.THIN);//上边框
        tableHeader.setBorderRight(BorderStyle.THIN);//右边框
        tableHeader.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        tableHeader.setAlignment(HorizontalAlignment.CENTER);// 水平
        tableHeader.setWrapText(true);// 指定当单元格内容显示不下时自动换行
        tableHeader.setFont(fontBond);
        tableHeader.setFillForegroundColor(HSSFColor.HSSFColorPredefined.TAN.getIndex());//背景色
        tableHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //标题
        title.setBorderBottom(BorderStyle.THIN); //下边框
        title.setBorderLeft(BorderStyle.THIN);//左边框
        title.setBorderTop(BorderStyle.THIN);//上边框
        title.setBorderRight(BorderStyle.THIN);//右边框
        title.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        title.setAlignment(HorizontalAlignment.CENTER);// 水平
        title.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        title.setFont(font);

        //金额
        amount.setBorderBottom(BorderStyle.THIN); //下边框
        amount.setBorderLeft(BorderStyle.THIN);//左边框
        amount.setBorderTop(BorderStyle.THIN);//上边框
        amount.setBorderRight(BorderStyle.THIN);//右边框
        amount.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        amount.setAlignment(HorizontalAlignment.RIGHT);// 水平
        amount.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        amount.setFont(font);
        amount.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));//财务计数法

        // 创建第一个sheet-余额调节表汇总信息
        Sheet sheet = workbook.createSheet("demo表");
        sheet.setColumnWidth(1, 9 * 256); // 序号
        sheet.setColumnWidth(2, 15 * 256); // 单据编号
        sheet.setColumnWidth(3, 15 * 256); // 单据名称
        sheet.setColumnWidth(4, 10 * 256); // 提单人
        sheet.setColumnWidth(5, 25 * 256); // 备注

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
        //行
        Row row;
        //列
        Cell cell;
        //行
        row = sheet.createRow(1);
        cell = row.createCell(1);
        cell.setCellStyle(borderAndMiddleAndBond);
        cell.setCellValue("demo表");
        row = sheet.createRow(2);
        cell = row.createCell(1);
        cell.setCellStyle(borderAndMiddleAndBond);
        cell.setCellValue(lawCode + comCode);

        row = sheet.createRow(4);
        cell = row.createCell(5);
        cell.setCellStyle(headerTitle);
        cell.setCellValue("系统编号："+ code);
        row = sheet.createRow(6);
        cell = row.createCell(5);
        cell.setCellStyle(headerTitle);
        cell.setCellValue("生成时间：" + date);
        int i = 9;
        sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 5));
        row = sheet.createRow(i);
        cell = row.createCell(1);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("平台单据");
        cell = row.createCell(2);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        cell = row.createCell(3);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        cell = row.createCell(4);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        cell = row.createCell(5);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        i++;
        row = sheet.createRow(i);
        cell = row.createCell(1);
        cell.setCellStyle(title);
        cell.setCellValue("序号");
        cell = row.createCell(2);
        cell.setCellStyle(title);
        cell.setCellValue("单据编号");
        cell = row.createCell(3);
        cell.setCellStyle(title);
        cell.setCellValue("单据名称");
        cell = row.createCell(4);
        cell.setCellStyle(title);
        cell.setCellValue("提单人");
        cell = row.createCell(5);
        cell.setCellStyle(title);
        cell.setCellValue("备注");
        for (String s : list1) {
            i++;
            row = sheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellStyle(title);
            cell.setCellValue(s);
            cell = row.createCell(2);
            cell.setCellStyle(title);
            cell.setCellValue("单据编号");
            cell = row.createCell(3);
            cell.setCellStyle(title);
            cell.setCellValue("单据名称");
            cell = row.createCell(4);
            cell.setCellStyle(title);
            cell.setCellValue("提单人");
            cell = row.createCell(5);
            cell.setCellStyle(title);
            cell.setCellValue("备注");
        }

        i++;
        sheet.addMergedRegion(new CellRangeAddress(i, i, 1, 5));
        row = sheet.createRow(i);
        cell = row.createCell(1);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("纸质银行回单");
        cell = row.createCell(2);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        cell = row.createCell(3);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        cell = row.createCell(4);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        cell = row.createCell(5);
        cell.setCellStyle(tableHeader);
        cell.setCellValue("");
        i++;
        row = sheet.createRow(i);
        cell = row.createCell(1);
        cell.setCellStyle(title);
        cell.setCellValue("序号");
        cell = row.createCell(2);
        cell.setCellStyle(title);
        cell.setCellValue("单据编号");
        cell = row.createCell(3);
        cell.setCellStyle(title);
        cell.setCellValue("单据名称");
        cell = row.createCell(4);
        cell.setCellStyle(title);
        cell.setCellValue("提单人");
        cell = row.createCell(5);
        cell.setCellStyle(title);
        cell.setCellValue("备注");
        for (String s : list1) {
            i++;
            row = sheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellStyle(title);
            cell.setCellValue(s);
            cell = row.createCell(2);
            cell.setCellStyle(title);
            cell.setCellValue("单据编号");
            cell = row.createCell(3);
            cell.setCellStyle(title);
            cell.setCellValue("单据名称");
            cell = row.createCell(4);
            cell.setCellStyle(title);
            cell.setCellValue("提单人");
            cell = row.createCell(5);
            cell.setCellStyle(title);
            cell.setCellValue("备注");
        }

        BufferedImage image;
        try {
            image = QRCodeUtils.createImage(qrcode, null, true);
            //图片一导出到单元格B4中
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            //将图片读到BufferedImage
            // 将图片写入流中
            ImageIO.write(image, "jpg", byteArrayOut);
            //anchor主要用于设置图片的属性， 针对pdf图片处理 设置为 1，3，1，7；针对excel图片处理 设置为 1，3，2，7
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short) 1, 3, (short) 1, 7);
            //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
            HSSFPatriarch  patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();
            patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
        } catch (Exception e) {
            log.error("二维码生成失败");
        }

        return workbook;
    }
}
