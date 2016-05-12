package com.dl.test.query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dl.utils.CommonUtil;

public class QueryFromExcel2 {


	public QueryFromExcel2(String filePath) {
		
		try {
			XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(new File(filePath)));
			Sheet sheet = book.getSheetAt(0);
			for (int j =1; j <sheet.getPhysicalNumberOfRows(); j++) {
				Row row = sheet.getRow(j);
				Cell cell = row.getCell(0);
				System.out.println("标引记录号"+(j+1)+"数据...");
				String value = null;
				try {
					value = cell.getStringCellValue();
				} catch (Exception e) {
					System.out.println("错误的数据格式："+cell.getCellType());
					System.out.println("重新设置格式...");
					value = this.getCellValue(cell);
				}
				String[] result = GetWordDetailValue(value);
//				String[] result = getAutoIndexValue(value);
				if(result[1].equals("true")){
					row.createCell(1).setCellValue("是");
					row.createCell(2).setCellValue(result[0]);
				}else if(result[1].equals("false")){
					row.createCell(1).setCellValue("否");
				}else if(result[1].equals("error")){
					row.createCell(1).setCellValue("error");
					row.createCell(1).setCellValue("error");
				}else{
					row.createCell(1).setCellValue("NULL");
					row.createCell(1).setCellValue("NULL");
				}
			}
			
			FileOutputStream out = new FileOutputStream(new File(filePath));
			SXSSFWorkbook wb = new SXSSFWorkbook(book, 10000);
			wb.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	private String[] GetWordDetailValue(String keyWord){
		Map<String,Object> map = new HashMap<String, Object>();
		String result = "";
		String isResult="";
		String words = "";
		try {
			result = new com.dl.webservice.DlWebService().getWordDetail(replaceBlank(keyWord),"HTTP","POST");
			map = CommonUtil.parseXMLByDOM4J(result,"HTTP","wordDetail");
			isResult = map.get("Result").toString();
			words = map.get("WordClc").toString();
			return new String[]{words,isResult};
		} catch (Exception e) {
			for(int i=1;i<=10;i++){
				String log = "发起["+keyWord+"]第"+i+"次数据请求...";
				System.out.println(log);
				try {
					CommonUtil.writeFile("error", log+"\r\n", "log", true);
				} catch (FileNotFoundException e1) {
					System.out.println("--"+log+"--错误日志写入失败");
				}
				result = new com.dl.webservice.DlWebService().getWordDetail(replaceBlank(keyWord),"HTTP","POST");
				if(result.trim().length()>1){
					map = CommonUtil.parseXMLByDOM4J(result,"HTTP","wordDetail");
					isResult = map.get("Result").toString();
					words = map.get("WordClc").toString();
					break;
				}
			}
			if(result.trim().length()>1){
				return new String[]{words,isResult};
			}else{
				try {
					CommonUtil.writeFile("error", "["+keyWord+"]10次重复请求数据失败" ,"log", true);
				} catch (FileNotFoundException e1) {
					System.out.println("--"+keyWord+"--错误日志写入失败");
				}
				return new String[]{"无请求结果","error"};
			}
		}
	}
//	private String[] getAutoIndexValue(String keyWord){
//		Map<String,Object> map = new HashMap<String, Object>();
//		String result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(keyWord),"HTTP","POST");
//		map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
//		String concepts = ((List<String>)map.get("IndexingConcepts")).toString();
//		String words = ((List<String>)map.get("ClcNumbers")).toString();
//		String isResult = map.get("Result").toString();
//		return new String[]{concepts.substring(1, concepts.length()-1),words.substring(1,words.length()-1),isResult};
//	}
	
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
//				value = String.valueOf(cell.getRichStringCellValue());
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
