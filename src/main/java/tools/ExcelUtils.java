package tools;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Chuckie on 2017/11/21.
 */
public class ExcelUtils {
    private static Sheet ExcelWSheet;
    private static Workbook ExcelWBook;
    private static Cell Cell;
    public static Object[][] getTableArray(String FilePath, String SheetName, int ColumnNum) throws Exception {
        String[][] excelTableArray = null;
        try {
            if (FilePath.endsWith(".xls")) {
                FileInputStream ExcelFile = new FileInputStream(FilePath);
                ExcelWBook = new HSSFWorkbook(ExcelFile);
            }else if (FilePath.endsWith(".xlsx")) {
                ExcelWBook = new XSSFWorkbook(FilePath);
            }
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int startRow = 1;
            int startCol = 1;
            int ri,cj;
            int totalRows = ExcelWSheet.getLastRowNum();
            int totalCols = ColumnNum;
            excelTableArray=new String[totalRows][totalCols];
            ri=0;
            for (int i=startRow;i<=totalRows;i++, ri++) {
                cj=0;
                for (int j=startCol;j<totalCols;j++, cj++){
                    excelTableArray[ri][cj]=getCellData(i,j);
//                    System.out.println(tabArray[ci][cj]);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        catch (IOException e){
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return(excelTableArray);
    }
    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try{
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            int dataType = Cell.getCellType();
            if  (dataType == 3) {
                return "";
            }
            else{
                String CellData = Cell.getStringCellValue();
                return CellData;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw (e);
        }
    }
}
