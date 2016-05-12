package com.dl.test;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
* Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：WriteExcel.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2016年5月12日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2016年5月12日<br>
 */
public class WriteExcel {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Throwable {
		
		 ReadExcelUtils howto = new ReadExcelUtils();
	     String fileName = "output5w.xlsx";
	     List<List<String>> list = howto.processSAXReadSheet(fileName,0);
	     System.out.println(list.size());
		
		SXSSFWorkbook wb = new SXSSFWorkbook(1000); // keep 100 rows in memory,
													// exceeding rows will be
													// flushed to disk
		Sheet sh = wb.createSheet("Sheel1");
		for (int rownum = 0; rownum < list.size(); rownum++) {
			List<String> rowList = list.get(rownum);
			Row row = sh.createRow(rownum);
			for (int cellnum = 0; cellnum < rowList.size(); cellnum++) {
				Cell cell = row.createCell(cellnum);
				String address = rowList.get(cellnum);
//				String address = new CellReference(cell).formatAsString();
				cell.setCellValue(address);
			}
		}

//		// Rows with rownum < 900 are flushed and not accessible
//		for (int rownum = 0; rownum < 900; rownum++) {
//			System.out.println(sh.getRow(rownum));
//			Assert.assertNull(sh.getRow(rownum));
//		}
//
//		// ther last 100 rows are still in memory
//		for (int rownum = 900; rownum < 1000; rownum++) {
//			Assert.assertNotNull(sh.getRow(rownum));
//		}

		FileOutputStream out = new FileOutputStream("sxssf2.xlsx");
		wb.write(out);
		out.close();

		// dispose of temporary files backing this workbook on disk
		wb.dispose();
	}

}
