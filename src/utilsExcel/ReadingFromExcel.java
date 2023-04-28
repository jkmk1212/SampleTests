package utilsExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingFromExcel {

	public static String[][] getData(String filename, String sheetname) throws IOException {

		File file = new File(filename);
		FileInputStream ips = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(ips);
		Sheet sh = wb.getSheet(sheetname);
		int rownum = sh.getLastRowNum() + 1;
		int cellno = sh.getRow(0).getLastCellNum();
		String[][] data = new String[rownum][cellno];

		for (int i = 0; i < rownum; i++) {
			Row row = sh.getRow(i);
			for (int j = 0; j < cellno; j++) {

				Cell cell = row.getCell(j);
				String value = new DataFormatter().formatCellValue(cell);
				data[i][j] = value;
			}
		}
		return data;
	}

	public static void main(String[] args) throws IOException {

		String data[][] = ReadingFromExcel.getData("new.xlsx", "Sheet1");

		for (int i = 0; i < data.length; i++) {

			System.out.print(data[i][1]+"   ");
			System.out.print(data[i][2]);
			System.out.println("\n");
		}

	}
}
