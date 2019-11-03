package io.lession16;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @project network-programming
 * @user DinhChiThien on 10/11/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession16 {

    protected void exportToTxt(String textFile, ArrayList<Student> students, String charset) throws IOException {
        if (students.isEmpty()) {
            return;
        } else {

            File file = new File(textFile);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, Charset.forName(charset));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            Student student = new Student();
            bufferedWriter.write(student.getHeader("\t"));
            bufferedWriter.newLine();
            for (int i = 0; i < students.size(); i++) {
                students.get(i).write(bufferedWriter, "\t");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        }

    }

    protected List<Student> importFromTxt(String textFile, String charset) throws IOException {
        File file = new File(textFile);
        if (!file.exists()) {
            return null;
        } else {
            ArrayList<Student> students = new ArrayList<Student>();
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, charset);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            bufferedReader.readLine();
            String data = "";
            while ((data = bufferedReader.readLine()) != null) {
                Student student = new Student();
                student.read(data, "\t");
                students.add(student);
            }
            bufferedReader.close();
            return students;
        }

    }

    protected void exportToCsv(String csvFile, ArrayList<Student> students, String charset) throws IOException {
        File file = new File(csvFile);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Charset.forName(charset));
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        Student student = new Student();
        bufferedWriter.write(student.getHeader(","));
        bufferedWriter.newLine();
        for (int i = 0; i < students.size(); i++) {
            students.get(i).write(bufferedWriter, ",");
        }
        bufferedWriter.flush();
        bufferedWriter.close();

    }

    protected List<Student> importFromCsv(String csvFile, String charset) throws IOException {

        File file = new File(csvFile);
        if (!file.exists()) {
            return null;
        } else {
            List<Student> students = new ArrayList<Student>();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName(charset)));
            bufferedReader.readLine();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                Student student = new Student();
                student.read(data, ",");
                students.add(student);
            }
            bufferedReader.close();
            return students;
        }


    }

    protected void exportToExcel(String xlsxFile, ArrayList<Student> students) throws IOException {

        if (students.isEmpty()) {
            throw new IllegalArgumentException("The specified list is empty.");
        } else {

            // creating a Workbook
            Workbook workbook = new XSSFWorkbook();

            // creating a sheet
            Sheet sheet = workbook.createSheet();

            int rowCount = 0;

            // write head of table
            Row headRow = sheet.createRow(rowCount++);
            headRow.createCell(0).setCellValue("ID_ST");
            headRow.createCell(1).setCellValue("NAME_ST");
            headRow.createCell(2).setCellValue("CLASS_ST");
            headRow.createCell(3).setCellValue("SCORE_ST");

            // write value of table
            for (Student student : students) {
                Row row = sheet.createRow(rowCount++);
                student.writeCellValue(row);
            }

            // write workbook to file through outputStream
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(xlsxFile));
            workbook.write(outputStream);

        }


    }

    protected List<Student> importFromExcel(String xlsxFile) throws IOException, InvalidFormatException {

        // create a Workbook from an excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(xlsxFile));

        // getting the sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        ArrayList<Student> students = new ArrayList<Student>();

        for (Row row : sheet) {
            if (row.getRowNum() != 0) {
                Student student = new Student();
                student.readCellValue(row);
                students.add(student);
            }
        }

        return students;
    }
}
