package com.triveni.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/GenerateReport")
public class GenerateReport extends HttpServlet {

	private static final long serialVersionUID = 1L;

	XSSFWorkbook workbook;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			FileInputStream file = new FileInputStream(new File("C:\\programs\\excel\\source.xlsx"));

			// Get the xlsx workbook
			workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Get iterator to all cells of current row
			Iterator<Row> rowIterator = sheet.rowIterator();

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t\t");
						break;

					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t\t");
						break;

					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue() + "\t\t");
						break;
					}

				}
				System.out.println("");
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		response.setContentType("application/vnd.ms-excel");
		workbook.write(response.getOutputStream());
		response.getOutputStream().close();

	}
}