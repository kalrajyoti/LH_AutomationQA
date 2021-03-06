package com.lh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 * This class contains the methods used to read and write to excel files
 * <p>
 * 
 * @author ravi.middha, amita.arya
 * @version 2.0
 * @since 2014-11-17
 */
public class ExcelFileUtility {
	/**
	 * Logs messages to the appenders
	 */
	private static Logger logger = LogManager.getLogger(com.lh.utils.ExcelFileUtility.class);

	/**
	 * This method reads test data from the data file specified in the config
	 * properties
	 * 
	 * @param workBookName
	 * @param sheetName
	 * @return HashMap with the test values read from the test excel file
	 */
	/*public HashMap<String, String> readExcelSheet(final String workBookName,
			final String sheetName) {
		logger.info("Opening the excel file to read data from: " + workBookName
				+ " workbook, " + sheetName + " worksheet");
		HashMap<String, String> dataMap = new HashMap<String, String>();
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File("../test-data/"
					+ workBookName + ".xls"));
			Sheet sheet = workbook.getSheet(sheetName);
			String mapKey = new String();
			String mapValue = new String();
			logger.info("reading the data from the excel data sheet");

			for (int row = 1; row <= sheet.getRows() - 1; row++) {
				mapKey = sheet.getCell(0, row).getContents().toString();
				mapValue = sheet.getCell(1, row).getContents().toString();
				dataMap.put(mapKey, mapValue);
			}

			workbook = null;
			sheet = null;
			logger.info("Closing the excel file and returning to the test...");

		} catch (IOException e) {
			logger.error("Unable to read from the excel file -  " + workBookName);
			e.printStackTrace();
		} catch (BiffException e) {
			logger.error("Unable to read from the excel file due to the Biffexception -  "
					+ workBookName);
			e.printStackTrace();
		} finally {
			logger.info("Inside the finally Method of the readExcelSheet method");
		}
		return dataMap;
	}
	*/
	
	public HashMap<String, String> readExcelSheet(final String workBookName, final String sheetName) {

		logger.info("Opening the excel file to read data from: " + workBookName + " workbook, " + sheetName	+ " worksheet");

		HashMap<String, String> dataMap = new HashMap<String, String>();

		try {
			
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + workBookName)));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			String mapKey = new String();
			String mapValue = new String();
			int rowCounter = 0;

			logger.info("reading the data from the excel data sheet");

			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {

				HSSFRow row = (HSSFRow) rows.next();
				rowCounter = 0;
				String cellText = null;

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();

					if (cell.getCellType() == Cell.CELL_TYPE_STRING ) {

						cellText = cell.getRichStringCellValue().getString();
						
					} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {

						cellText =  String.valueOf( (int) cell.getNumericCellValue());

					} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

						cellText = String.valueOf(cell.getBooleanCellValue());

					} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA ) {
						cellText = evaluator.evaluate(cell).getStringValue();
						
						
//						SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
//						if(cell.getDateCellValue()!= null){							 
//						cellText = sdf.format(cell.getDateCellValue()); 
//						}else{
//						
//						}
						
					} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {

						cellText = null;
						continue;
					}

					else {
						logger.info("Not a valid type");
					}

					if (rowCounter == 0) {

						mapKey = cellText;
						rowCounter= rowCounter + 1;
						

					} else if (rowCounter == 1) {

						mapValue = cellText;
						rowCounter= rowCounter + 1;
					}

					dataMap.put(mapKey, mapValue);

				}
			}
			
			workbook.close();
			workbook = null;
			sheet = null;
			
			logger.info("Closing the excel file and returning to the test...");

		} catch (IOException e) {
			
			logger.error("Unable to read from the excel file -  " + workBookName);
			
			e.printStackTrace();
			
		} finally {
			
			logger.info("Inside the finally Method of the readExcelSheet method");
			
		}
		
		return dataMap;
		
	}
	/*
	 * Edits existing excel LH with a string value in the position of 7th row.
	 */
	public void editExcel(final String workBookName, final String sheetName,final String cellValue) throws IOException{
		
		//File workB = new File(("C:/workspace/LiveHealthier/src/test/resources/LH.xls"));//)(this.getClass().getClassLoader().getResource("").getPath()+"../src/test/resources/"+ workBookName + ".xls");
		File workB = new File((System.getProperty("user.dir")+"/src/test/resources/LH.xls"));//)(this.getClass().getClassLoader().getResource("").getPath()+"../src/test/resources/"+ workBookName + ".xls");
		
		FileInputStream fsIP= new FileInputStream(workB);
		
		HSSFWorkbook workbook = new HSSFWorkbook(fsIP);

		HSSFSheet sheet = workbook.getSheet(sheetName); //Access the worksheet, so that we can update / modify it.
          
        Cell cell = null; // declare a Cell object
        
        cell = sheet.getRow(7).getCell(1);   // Access the second cell in second row to update the value

     //   cell.setCellType(Cell.CELL_TYPE_STRING);
          
        cell.setCellFormula(cellValue);
      //  cell.setCellValue(cellValue);  // Get current cell value value and overwrite the value
          
     //   cell.setCellType(Cell.CELL_TYPE_FORMULA);
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        fsIP.close(); //Close the InputStream
         
        FileOutputStream output_file =new FileOutputStream(workB);  //Open FileOutputStream to write updates
          
        workbook.write(output_file); //write changes
        workbook.close();
        output_file.close();  //close the stream 
        
       

	}
	
}