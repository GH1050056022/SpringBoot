package com.lucio.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {


    public static void setXlsxToZip(String fileName, ZipOutputStream zip, List<SXSSFWorkbook> list)
        throws Exception {
        zip.setComment("A test of Java Excel Zipping");
        ByteArrayOutputStream bos;
        for (int i = 0; i < list.size(); i++) {
            SXSSFWorkbook workbook = list.get(i);
            bos = new ByteArrayOutputStream();
            try {
                ZipEntry entry = new ZipEntry(fileName + "("+(i + 1) + ").xls");
                zip.putNextEntry(entry);
                workbook.write(bos);
                bos.writeTo(zip);
                // Add other entries as needed
            } catch (IOException e) {
                throw new Exception("excel压入zip失败");
            } finally {
                bos.close();
                //if(workbook != null)
                //workbook.close();
                zip.closeEntry();
            }
        }
    }

    public static void setXlsToZip(String fileName, ZipOutputStream zip, List<HSSFWorkbook> list)
        throws Exception {
        zip.setComment("A test of Java Excel Zipping");
        ByteArrayOutputStream bos;
        for (int i = 0; i < list.size(); i++) {
            HSSFWorkbook workbook = list.get(i);
            bos = new ByteArrayOutputStream();
            try {
                ZipEntry entry = new ZipEntry(fileName + "("+(i + 1) + ").xls");
                zip.putNextEntry(entry);
                workbook.write(bos);
                bos.writeTo(zip);
                // Add other entries as needed
            } catch (IOException e) {
                throw new Exception("excel压入zip失败");
            } finally {
                bos.close();
                workbook.close();
                zip.closeEntry();
            }
        }
    }

    public static void setTxtToZip(String fileName, ZipOutputStream zip, List<String> list) throws IOException {
        zip.setComment("A test of Java Txt Zipping");
        for (int i = 0; i < list.size(); i++) {
            String result = list.get(i);
            ZipEntry entry = new ZipEntry(fileName + "("+(i + 1) + ").xls");
            zip.putNextEntry(entry);
            byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
            zip.write(bytes);
            zip.closeEntry();
        }
    }
}

