package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @project network-programming
 * @user DinhChiThien on 10/11/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession15 {
    public static void main(String[] args) throws IOException {
        String destFile = "E:\\testNetworkProgramming\\week4\\studentsRAF.txt";
        Student student = new Student("999", "Chí Phèo", 100.100);
        new Lession15().editStudentRAF(destFile, 2, student);
    }

    private boolean editStudentRAF(String destFile, int i, Student student) throws IOException {
        File file = new File(destFile);
        if (!file.exists()) {
            return false;
        } else {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            int number = raf.readInt();
            if (i < 0 || i > number) {
                return false;
            } else {
                long sizePart = (raf.length()-4) / number;
                long position = 4 + sizePart*(i-1);
                raf.seek(position);
                student.saveRAF(raf);
                raf.close();
                return true;
            }

        }
    }
}
