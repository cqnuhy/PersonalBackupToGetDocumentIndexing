package com.dl.test.query;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;

import com.dl.test.ReadExcelUtils;
import com.dl.utils.CommonUtil;

/**
 * All rights reserved.<br>
 * 
 * 文件名称：ReadBigExcel.java<br>
 * 摘要：读取大文件Excel<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：胡毅<br>
 * 完成日期：2016年5月12日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：胡毅<br>
 * 完成日期：2016年5月12日<br>
 */
public class ReadBigExcel {

	public ReadBigExcel(String filePath) throws IOException,
			OpenXML4JException, SAXException {

		String outPutFilePath = "C:\\Users\\Administrator\\Desktop\\output5w_out.xlsx";
		FileOutputStream out = new FileOutputStream(new File(outPutFilePath));

		ReadExcelUtils howto = new ReadExcelUtils();
		List<List<String>> list = howto.processSAXReadSheet(filePath, 0);

		// 读写具体excel时请保证单元格格式一致（例如，有空格的列必须都要有空格）
//		XSSFWorkbook temp = new XSSFWorkbook(new File(outPutFilePath));
		SXSSFWorkbook wb = new SXSSFWorkbook(1000); // keep 1000 rows in memory,
													// exceeding rows will be
													// flushed to disk
		Sheet sh = wb.createSheet("Sheel1");
		Row header = sh.createRow(0);
		CellStyle headStyle = wb.createCellStyle();
		Font headFont = wb.createFont();
        headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headStyle.setFont(headFont);
        for(int i=0;i<list.get(0).size();i++){
			Cell cell = header.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(list.get(0).get(i));
		}
		try {
			for (int rownum = 1; rownum < list.size(); rownum++) {
				List<String> rowList = list.get(rownum);
				System.out.println((rownum + 1) + "...");
				String[] result = GetWordDetailValue(rowList.get(0));
				// String[] result = getAutoIndexValue(value);
				Row row = sh.createRow(rownum);
				for (int cellnum = 0; cellnum < rowList.size(); cellnum++) {
					if(cellnum==0){
						Cell cell = row.createCell(cellnum);
						String shortWord = rowList.get(cellnum);
						//设置短语单元格
						cell.setCellValue(shortWord);
						// 设置是否受控词和分类号
						if (result[1].equals("true")) {
							row.createCell(cellnum+1).setCellValue("是");
							row.createCell(cellnum+2).setCellValue(result[0]);
						} else if (result[1].equals("false")) {
							row.createCell(cellnum+1).setCellValue("否");
							row.createCell(cellnum+2).setCellValue("");
						} else if (result[1].equals("error")) {
							row.createCell(cellnum+1).setCellValue("error");
							row.createCell(cellnum+2).setCellValue("error");
						} else {
							row.createCell(cellnum+1).setCellValue("NULL");
							row.createCell(cellnum+2).setCellValue("NULL");
						}
					}else{
						// 设置TF,MI,LE,RE
						row.createCell(cellnum+2).setCellValue(rowList.get(cellnum));
					}
				}
			}

			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String[] GetWordDetailValue(String keyWord) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "";
		String isResult = "";
		String words = "";
		try {
			result = new com.dl.webservice.DlWebService().getWordDetail(
					replaceBlank(keyWord), "HTTP", "POST");
			map = CommonUtil.parseXMLByDOM4J(result, "HTTP", "wordDetail");
			isResult = map.get("Result").toString();
			words = map.get("WordClc").toString();
			return new String[] { words, isResult };
		} catch (Exception e) {
			for (int i = 1; i <= 10; i++) {
				String log = "发起[" + keyWord + "]第" + i + "次数据请求...";
				System.out.println(log);
				try {
					CommonUtil.writeFile("error", log + "\r\n", "log", true);
				} catch (FileNotFoundException e1) {
					System.out.println("--" + log + "--错误日志写入失败");
				}
				result = new com.dl.webservice.DlWebService().getWordDetail(
						replaceBlank(keyWord), "HTTP", "POST");
				if (result.trim().length() > 1) {
					map = CommonUtil.parseXMLByDOM4J(result, "HTTP",
							"wordDetail");
					isResult = map.get("Result").toString();
					words = map.get("WordClc").toString();
					break;
				}
			}
			if (result.trim().length() > 1) {
				return new String[] { words, isResult };
			} else {
				try {
					CommonUtil.writeFile("error", "[" + keyWord
							+ "]10次重复请求数据失败", "log", true);
				} catch (FileNotFoundException e1) {
					System.out.println("--" + keyWord + "--错误日志写入失败");
				}
				return new String[] { "无请求结果", "error" };
			}
		}
	}

	// private String[] getAutoIndexValue(String keyWord){
	// Map<String,Object> map = new HashMap<String, Object>();
	// String result = new
	// com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(keyWord),"HTTP","POST");
	// map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
	// String concepts = ((List<String>)map.get("IndexingConcepts")).toString();
	// String words = ((List<String>)map.get("ClcNumbers")).toString();
	// String isResult = map.get("Result").toString();
	// return new String[]{concepts.substring(1,
	// concepts.length()-1),words.substring(1,words.length()-1),isResult};
	// }

	private String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public String getCellValue(Cell cell) {
		String value = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			// cell.getCellFormula();
			try {
				value = String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				// value = String.valueOf(cell.getRichStringCellValue());
				value = String.valueOf(cell.getCellFormula());
			}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			value = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = String.valueOf(cell.getRichStringCellValue());
			break;
		}
		return value;
	}
}
