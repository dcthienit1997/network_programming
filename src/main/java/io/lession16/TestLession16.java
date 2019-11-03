package io.lession16;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @project network-programming
 * @user DinhChiThien on 10/19/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TestLession16 {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        // Prepare data students
        Student student1 = new Student("15130173", "Đinh Chí Thiên", "DH15DTB", 9.9);
        Student student2 = new Student("15130174", "Nguyễn Hoàng Long", "DH15DTA", 8.9);
        Student student3 = new Student("15130175", "Trần Mạnh Tuấn", "DH15DTA", 5);
        Student student4 = new Student("15130176", "Lê Hoàng Linh", "DH15DTC", 6);
        Student student5 = new Student("15130177", "Đỗ Minh Nhu", "DH15DTC", 9.7);
        Student student6 = new Student("15130178", "Nguyễn Cao Kỳ Duyên", "DH15DTB", 5.9);
        ArrayList<Student> students = new ArrayList<Student>();
        List<Student> studentsTXT = new ArrayList<Student>();
        List<Student> studentsCSV = new ArrayList<Student>();
        List<Student> studentsEXCEL = new ArrayList<Student>();

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        // file paths
        String txtFile = "E:\\testNetworkProgramming\\week5\\txtFile.txt";
        String csvFile = "E:\\testNetworkProgramming\\week5\\csvFile.csv";
        String excelFile = "E:\\testNetworkProgramming\\week5\\excelFile.xlsx";

        /*
         * Test functions
         */
        Lession16 lession16 = new Lession16();
        String charset = "UTF-8";
        // 1. export data from student list to txt file
        lession16.exportToTxt(txtFile, students, charset);
        // 2. import txt file to student list
        studentsTXT = lession16.importFromTxt(txtFile, charset);
        // 3. export data from student list to csv file
        lession16.exportToCsv(csvFile, students, charset);
        // 4. import csv file to student list
        studentsCSV = lession16.importFromCsv(csvFile, charset);
        // 5. export data from student list excel file (.xls or .xlsx)
        lession16.exportToExcel(excelFile, students);
        // 6. import excel file to student list
        studentsEXCEL = lession16.importFromExcel(excelFile);

        /*
         * Check results
         */
        // 1. print students from txt file
        System.out.println("Student list from txt file");
        for (Student student: studentsTXT) {
            System.out.println(student.toString());
            System.out.println();
        }
        // 2. print students from csv file
        System.out.println("Student list from csv file");
        for (Student student: studentsCSV) {
            System.out.println(student.toString());
            System.out.println();
        }
        // 3. print students from excel file
        System.out.println("Student list from excel file");
        for (Student student: studentsEXCEL) {
            System.out.println(student.toString());
            System.out.println();
        }

    }
}
