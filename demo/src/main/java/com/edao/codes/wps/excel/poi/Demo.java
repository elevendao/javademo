package com.edao.codes.wps.excel.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {
	//excel2003
	public static String loadXls(InputStream is) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<?> rows = sheet.iterator();
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow)rows.next();
			Iterator<?> cells = row.cellIterator();
			while(cells.hasNext()) {
				HSSFCell cell = (HSSFCell)cells.next();
				String cellVal = "";
				if (cell != null)
					cellVal = cell.getStringCellValue();
				buffer.append(cellVal+",");
			}
		}
		
		String list = "";
		if (buffer.length() > 0)
			list = buffer.substring(0, buffer.length()-1);
		return list;
	}
	
	public static String loadXls(String strPath) throws Exception {
		InputStream is = new FileInputStream(strPath);
		return loadXls(is);
	}
	
	public static List<String> readXls(InputStream is) throws Exception {
		List<String> list = new ArrayList<String>();
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		Iterator<?> rows = sheet.iterator();
		while (rows.hasNext()) {
			HSSFRow row = (HSSFRow)rows.next();
			Iterator<?> cells = row.cellIterator();
			while(cells.hasNext()) {
				HSSFCell cell = (HSSFCell)cells.next();
				String cellVal = "";
				if (cell != null)
					cellVal = cell.getStringCellValue();
				list.add(cellVal);
			}
		}
		
		return list;
	}
	
	public static List<String> readXls(String path) throws Exception {
		InputStream is = new FileInputStream(path);
		
		return readXls(is);
	}
	
	//excel2007
	public static String loadXlsx(InputStream is) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		StringBuffer buffer = new StringBuffer();
		Iterator<?> rows = sheet.iterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator<?> cells = row.cellIterator();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				String cellVal = "";
				if (cell != null)
					cellVal = cell.getStringCellValue();
				buffer.append(cellVal+",");
			}
		}
		
		String list = "";
		if (buffer.length() > 0)
			list = buffer.substring(0, buffer.length()-1);	
		return list;
	}
	
	public static String loadXlsx(String strPath) throws Exception {
		InputStream is = new FileInputStream(strPath);
		return loadXlsx(is);
	}
	
	public static List<String> readXlsx(InputStream is) throws Exception {
		List<String> list = new ArrayList<String>();
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Iterator<?> rows = sheet.iterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator<?> cells = row.cellIterator();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				String cellVal = "";
				if (cell != null)
					cellVal = cell.getStringCellValue();
				list.add(cellVal);
			}
		}
		
		return list;
	}
	
	public static List<String> readXlsx(String strPath) throws Exception {
		InputStream is = new FileInputStream(strPath);
		return readXlsx(is);
	}

	public static void main(String[] args) throws Exception {
		String xlsxFile = "D:\\workbench\\appnames.xlsx";
//		String str = loadXlsx("D:\\workbench\\appnames.xlsx");
//		String str = loadXls("D:\\workbench\\appnames.xls");
//		System.out.println(str);
//		List list = readXls("D:\\workbench\\appnames.xls");
		List list = readXlsx(xlsxFile);
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
