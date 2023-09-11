package utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class ReadXLSData {

    @DataProvider(name="openg2pdata")
    public String[][] getData(Method m) throws IOException {
        String excelSheetName = m.getName();
        File file = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\testdata\\testdata.xlsx");
        FileInputStream fileinputstream = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(fileinputstream);
        Sheet sheetName = wb.getSheet(excelSheetName);

    //Reading data from the cells and columns
        int totalRows = sheetName.getLastRowNum();
        System.out.println(totalRows);
        Row rowCells = sheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();
        System.out.println(totalCols);

    // Data formatting
        DataFormatter format = new DataFormatter();

        String testData[][] = new String[totalRows][totalCols];
        for(int i=1; i<=totalRows; i++) {
            for(int j=0; j<totalCols;j++) {
                testData[i-1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
                System.out.println(testData[i-1][j]);
            }
        }
        return testData;
    }
}
