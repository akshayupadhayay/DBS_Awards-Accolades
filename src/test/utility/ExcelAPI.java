package utility;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelAPI {
    public FileInputStream fis = null;
    public FileOutputStream fos = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    String xlsFilePath;

    public ExcelAPI(String xlsFilePath) throws IOException {
        this.xlsFilePath = xlsFilePath;
        fis = new FileInputStream(xlsFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum();
    }

    public int getColumnCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        return row.getLastCellNum();
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.BLANK || cell.getCellType() == CellType.NUMERIC) {
                return cell.getRawValue();
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Row: " + rowNum + "or Column: " + colNum + " does not exist in Excel";
        }
    }

    public boolean setCellData(String sheetName, int colNum, int rowNum, String value) {
        try {
            sheet = workbook.getSheet(sheetName);

            row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            cell = row.getCell(colNum);
            if(cell == null){
                cell = row.createCell(colNum);
            }
            cell.setCellValue(value);
            fos = new FileOutputStream(xlsFilePath);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        ExcelAPI apiObj = new ExcelAPI("src/excelExportAndFileIO/FBBusiness.xlsx");
        int colCount = apiObj.getColumnCount("tableOne");
        System.out.println("Column count:" + colCount);
        int rowCount = apiObj.getRowCount("tableOne");
        System.out.println("Row count:" + rowCount);

        // Get cell data
        System.out.println(apiObj.getCellData("tableOne", 1, 1));

        //Write cell data
        System.out.println(apiObj.setCellData("tableOne", 1, 1, "2000"));
    }
}