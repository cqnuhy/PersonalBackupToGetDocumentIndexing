package com.dl.test.query;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dl.utils.CommonUtil;

public class QueryFromExcel {

	public QueryFromExcel(String filePath) {
		XSSFWorkbook book;
		try {
			book = new XSSFWorkbook(new FileInputStream(new File(filePath)));
			Sheet sheet = book.getSheetAt(0);
			Row header = sheet.getRow(0);
			int countCols = header.getPhysicalNumberOfCells();
			int width = sheet.getColumnWidth(1);
			Cell skc = header.createCell((countCols-1)+1);
			skc.setCellValue("受控词");
			Cell fskc = header.createCell((countCols-1)+2);
			fskc.setCellValue("非受控词");
			sheet.setColumnWidth((countCols-1)+1, width);
			sheet.setColumnWidth((countCols-1)+2, width);
			for (int j =1; j <= 10; j++) {
				Row row = sheet.getRow(j);
				System.out.println("标引记录号"+row.getCell(0)+"数据...");
				String[] result = getAutoIndexValue(row.getCell(1).getStringCellValue());
				row.createCell((countCols-1)+1).setCellValue(result[0]);
				row.createCell((countCols-1)+2).setCellValue(result[1]);
			}
			FileOutputStream out = new FileOutputStream(new File(filePath));
			book.write(out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "unchecked"})
	private String[] getAutoIndexValue(String keyWord){
		Map<String,Object> map = new HashMap<String, Object>();
		String result = new com.dl.webservice.DlWebService().getAutoIndexing("",replaceBlank(keyWord),"HTTP","POST");
		map = CommonUtil.parseXMLByDOM4J(result,"HTTP","autoIndexing");
		String concepts = ((List<String>)map.get("IndexingConcepts")).toString();
		String words = ((List<String>)map.get("IndexingWords")).toString();
		return new String[]{concepts.substring(1, concepts.length()-1),words.substring(1,words.length()-1)};
	}
	
	private String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

}
