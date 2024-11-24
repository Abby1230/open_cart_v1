package utilities; // Declares the package name for organizing related classes

import java.io.File; // For file creation and existence checks
import java.io.FileInputStream; // For reading data from Excel files
import java.io.FileOutputStream; // For writing data to Excel files
import java.io.IOException; // For handling input-output exceptions
import org.apache.poi.ss.usermodel.*; // For handling cells, rows, and styles in Excel
import org.apache.poi.xssf.usermodel.*; // For .xlsx file-specific handling (Excel 2007 and newer)

public class ExcelUtility { // Defines the ExcelUtility class for handling Excel files
	
    private FileInputStream fi; // To read data from Excel files
    
    private FileOutputStream fo; // To write data to Excel files
    
    private XSSFWorkbook workbook; // Represents the entire Excel workbook (file)
    
    private XSSFSheet sheet; // Represents a sheet within the workbook
    
    private XSSFRow row; // Represents a row in the sheet
    
    private XSSFCell cell; // Represents a cell in the row
    
    private CellStyle style; // For applying styles like color to cells
    
    private String path; // Stores the path of the Excel file

    public ExcelUtility(String path) { // Constructor initializes the file path
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException { // Gets row count in a sheet
        fi = new FileInputStream(path); // Opens the file for reading
        workbook = new XSSFWorkbook(fi); // Loads the workbook from the file
        sheet = workbook.getSheet(sheetName); // Gets the specified sheet
        int rowcount = sheet.getLastRowNum(); // Counts the rows (zero-based)
        workbook.close(); // Closes workbook to free resources
        fi.close(); // Closes file input stream
        return rowcount; // Returns the number of rows
    }

    public int getCellCount(String sheetName, int rownum) throws IOException { // Gets cell count in a row
        fi = new FileInputStream(path); // Opens the file for reading
        workbook = new XSSFWorkbook(fi); // Loads workbook
        sheet = workbook.getSheet(sheetName); // Gets the specified sheet
        row = sheet.getRow(rownum); // Gets the specified row
        int cellcount = row.getLastCellNum(); // Gets number of cells in the row
        workbook.close(); // Closes workbook
        fi.close(); // Closes file input stream
        return cellcount; // Returns cell count
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException { // Reads data from a cell
        fi = new FileInputStream(path); // Opens file for reading
        workbook = new XSSFWorkbook(fi); // Loads workbook
        sheet = workbook.getSheet(sheetName); // Gets the specified sheet
        row = sheet.getRow(rownum); // Gets the specified row
        cell = row.getCell(colnum); // Gets the specified cell
        DataFormatter formatter = new DataFormatter(); // Formatter to convert cell content to String
        String data;
        try {
            data = formatter.formatCellValue(cell); // Reads cell data as formatted text
        } catch (Exception e) {
            data = ""; // If cell is empty, returns an empty string
        }
        workbook.close(); // Closes workbook
        fi.close(); // Closes file input stream
        return data; // Returns cell data
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException { // Writes data to a cell
        File xlfile = new File(path); // Checks if file exists
        if (!xlfile.exists()) { // If file doesn’t exist, create it
            workbook = new XSSFWorkbook(); // Creates a new workbook
            fo = new FileOutputStream(path); // Creates file output stream
            workbook.write(fo); // Writes workbook to file
            fo.close(); // Closes file output stream
        }
        fi = new FileInputStream(path); // Opens file for reading
        workbook = new XSSFWorkbook(fi); // Loads workbook
        if (workbook.getSheetIndex(sheetName) == -1) { // If sheet doesn’t exist
            workbook.createSheet(sheetName); // Creates the sheet
        }
        sheet = workbook.getSheet(sheetName); // Gets the specified sheet
        if (sheet.getRow(rownum) == null) { // If row doesn’t exist
            sheet.createRow(rownum); // Creates the row
        }
        row = sheet.getRow(rownum); // Gets the specified row
        cell = row.createCell(colnum); // Creates the specified cell
        cell.setCellValue(data); // Sets the cell value
        fo = new FileOutputStream(path); // Opens file for writing
        workbook.write(fo); // Writes changes to workbook
        workbook.close(); // Closes workbook
        fi.close(); // Closes file input stream
        fo.close(); // Closes file output stream
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException { // Fills cell with green color
        fi = new FileInputStream(path); // Opens file for reading
        workbook = new XSSFWorkbook(fi); // Loads workbook
        sheet = workbook.getSheet(sheetName); // Gets specified sheet
        row = sheet.getRow(rownum); // Gets specified row
        cell = row.getCell(colnum); // Gets specified cell
        style = workbook.createCellStyle(); // Creates a new cell style
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex()); // Sets green color
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Sets solid fill pattern
        cell.setCellStyle(style); // Applies style to cell
        fo = new FileOutputStream(path); // Opens file for writing
        workbook.write(fo); // Writes changes to workbook
        workbook.close(); // Closes workbook
        fi.close(); // Closes file input stream
        fo.close(); // Closes file output stream
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException { // Fills cell with red color
        fi = new FileInputStream(path); // Opens file for reading
        workbook = new XSSFWorkbook(fi); // Loads workbook
        sheet = workbook.getSheet(sheetName); // Gets specified sheet
        row = sheet.getRow(rownum); // Gets specified row
        cell = row.getCell(colnum); // Gets specified cell
        style = workbook.createCellStyle(); // Creates a new cell style
        style.setFillForegroundColor(IndexedColors.RED.getIndex()); // Sets red color
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Sets solid fill pattern
        cell.setCellStyle(style); // Applies style to cell
        fo = new FileOutputStream(path); // Opens file for writing
        workbook.write(fo); // Writes changes to workbook
        workbook.close(); // Closes workbook
        fi.close(); // Closes file input stream
        fo.close(); // Closes file output stream
    }
}
