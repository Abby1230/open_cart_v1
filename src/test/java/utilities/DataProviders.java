package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = "D:\\WorkSpace\\open_cart_v1\\testData\\OpenCartDocument.xlsx"; // path to the Excel file
        ExcelUtility xlutil = new ExcelUtility(path); // creating an object for ExcelUtility

        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);
        String[][] loginData = new String[totalRows][totalCols]; // two-dimensional array to store data

        // Loop through rows and columns to read data from Excel file
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);
            }
        }
        
        return loginData; // returning two-dimensional array
    }
}
