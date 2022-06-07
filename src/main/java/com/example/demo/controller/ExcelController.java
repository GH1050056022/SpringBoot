package com.example.demo.controller;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: ExcelController
 * @Author: feng.i.liu
 * @Description:
 * @Date: 6/7/2022 4:45 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/api/excel")
public class ExcelController {

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletRequest httpRequest, HttpServletResponse response) throws IOException {

    }
    private Workbook createExcel(Workbook workbook, String periodName, String companyCode, String companyName, String accountBook){
        // 单元格格式
        CellStyle borderAndMiddleAndBond = workbook.createCellStyle();

        CellStyle borderAndLeft = workbook.createCellStyle();

        CellStyle amount = workbook.createCellStyle();

        CellStyle title = workbook.createCellStyle();
        CellStyle dateStyle = workbook.createCellStyle();


        Font fontBond = workbook.createFont();
        fontBond.setBold(true);
        fontBond.setFontName("宋体");
        fontBond.setFontHeight((short) 240);

        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeight((short) 180);


        //上标题字体
        Font titleFont = workbook.createFont();
        titleFont.setFontName("微软雅黑");
        titleFont.setFontHeight((short) 180);
        titleFont.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());//白色

        //头部
        borderAndMiddleAndBond.setBorderBottom(BorderStyle.THIN); //下边框
        borderAndMiddleAndBond.setBorderLeft(BorderStyle.THIN);//左边框
        borderAndMiddleAndBond.setBorderTop(BorderStyle.THIN);//上边框
        borderAndMiddleAndBond.setBorderRight(BorderStyle.THIN);//右边框
        borderAndMiddleAndBond.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        borderAndMiddleAndBond.setAlignment(HorizontalAlignment.CENTER);// 水平
        borderAndMiddleAndBond.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        borderAndMiddleAndBond.setFont(fontBond);

        //左标题
        borderAndLeft.setBorderBottom(BorderStyle.THIN); //下边框
        borderAndLeft.setBorderLeft(BorderStyle.THIN);//左边框
        borderAndLeft.setBorderTop(BorderStyle.THIN);//上边框
        borderAndLeft.setBorderRight(BorderStyle.THIN);//右边框
        borderAndLeft.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        borderAndLeft.setAlignment(HorizontalAlignment.LEFT);// 水平
        borderAndLeft.setWrapText(true);// 指定当单元格内容显示不下时自动换行
        borderAndLeft.setFont(font);

        //上标题
        title.setBorderBottom(BorderStyle.THIN); //下边框
        title.setBorderLeft(BorderStyle.THIN);//左边框
        title.setBorderTop(BorderStyle.THIN);//上边框
        title.setBorderRight(BorderStyle.THIN);//右边框
        title.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        title.setAlignment(HorizontalAlignment.CENTER);// 水平
        title.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        title.setFont(titleFont);
        title.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());//灰度50%
        title.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //金额
        amount.setBorderBottom(BorderStyle.THIN); //下边框
        amount.setBorderLeft(BorderStyle.THIN);//左边框
        amount.setBorderTop(BorderStyle.THIN);//上边框
        amount.setBorderRight(BorderStyle.THIN);//右边框
        amount.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        amount.setAlignment(HorizontalAlignment.RIGHT);// 水平
        amount.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        amount.setFont(font);
        amount.setDataFormat( HSSFDataFormat.getBuiltinFormat("#,##0.00"));//财务计数法

        CreationHelper creationHelper = workbook.getCreationHelper();
        //日期
        dateStyle.setBorderBottom(BorderStyle.THIN); //下边框
        dateStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dateStyle.setBorderTop(BorderStyle.THIN);//上边框
        dateStyle.setBorderRight(BorderStyle.THIN);//右边框
        dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        dateStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
        dateStyle.setWrapText(false);// 指定当单元格内容显示不下时自动换行
        dateStyle.setFont(font);
        dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        // 创建第一个sheet
        Sheet sheet = workbook.createSheet("XXXXXXX");
        sheet.setColumnWidth(0, 60 * 256); // 项目
        sheet.setColumnWidth(1, 40 * 256); // 本月金额
        sheet.setColumnWidth(2, 40 * 256); // 本年累计

        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 2));
        //行
        Row row;
        //列
        Cell cell;
        //行
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(borderAndMiddleAndBond);
        cell.setCellValue("XXXX");
        cell = row.createCell(1);
        cell.setCellStyle(borderAndMiddleAndBond);
        cell = row.createCell(2);
        cell.setCellStyle(borderAndMiddleAndBond);
        return workbook;
    }

}
