
package com.lucio.demo.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "/zip")
public class ZipController {

  @ApiOperation("zip文件加密导出")
  @PostMapping(value = "/addPassword")
  public void download(HttpServletResponse response)
      throws IOException {

    // 加密压缩的设置参数
    ZipParameters zipParameters = new ZipParameters();
    zipParameters.setEncryptFiles(true); // 开户加密
    zipParameters.setEncryptionMethod(EncryptionMethod.AES); // 加密方式

    // 指定输出文件、密码
    ZipFile zipFile = new ZipFile("compressed1.zip", "password".toCharArray());
    int count = 1;

    // 一次性添加要压缩的文件
    // 创建多个HSSFWorkbook对象
    /*List<HSSFWorkbook> list = Lists.newArrayList();
    HSSFWorkbook workbook1 = new HSSFWorkbook();
    workbook1.createSheet("Sheet1");
    list.add(workbook1);
    HSSFWorkbook workbook2 = new HSSFWorkbook();
    workbook2.createSheet("Sheet2");
    list.add(workbook2);

    for (HSSFWorkbook workbook : list) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      workbook.write(bos);
      byte[] bytes = bos.toByteArray();
      InputStream is = new ByteArrayInputStream(bytes);
      zipParameters.setFileNameInZip("workbook" + count + ".xls");
      zipFile.addStream(is, zipParameters);
      count++;
      bos.close();
      is.close();
    }*/
    // 一次性添加要压缩的文件txt
    List<String> txtList = Lists.newArrayList();
    txtList.add("1");
    txtList.add("2");
    txtList.add("3");
    for (String s : txtList) {
      byte[] bytes = s.getBytes();
      InputStream is = new ByteArrayInputStream(bytes);
      zipParameters.setFileNameInZip("新建文本文档" + count + ".txt");
      zipFile.addStream(is, zipParameters);
      count++;
    }// 将压缩文件作为响应返回给前端
    response.reset();
    response.setContentType("bin");
    String fileName = "testPwd.zip";
    response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    try (OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(zipFile.getFile())) {
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = in.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
    }
    zipFile.close();
    //删除生成的临时文件
    if(zipFile.getFile() != null){
      zipFile.getFile().delete();
    }


  }
}

