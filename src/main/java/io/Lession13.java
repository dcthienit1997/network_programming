package io;

import org.omg.PortableInterceptor.DISCARDING;

import java.io.*;
import java.util.ArrayList;

/**
 * @project network-programming
 * @user DinhChiThien on 10/7/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession13 {
    public static void main(String[] args) throws IOException {
        String destFile = "E:\\testNetworkProgramming\\week4\\studentsRAF.txt";
        ArrayList<Student> students = new ArrayList<Student>();
        Student student1 = new Student("123", "Nguyễn A", 5.5);
        Student student2 = new Student("456", "Trần Văn B", 7.8);
        Student student3 = new Student("789", "Phạm bla bla bla C", 10.0);
        Student student4 = new Student("101", "Đinh D", 1.9);
        Student student5 = new Student("112", "Trương H", 5.9);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
//        new Lession13().saveStudentsRAF(destFile, students);
        new Lession13().loadStudentsRAF(destFile, 2);
    }

    private void saveStudentsRAF(String destFile, ArrayList<Student> students) throws IOException {
        if (students.isEmpty()) {
            return;
        } else {
            RandomAccessFile raf = new RandomAccessFile(destFile, "rw");
            raf.writeInt(students.size());
            raf.getFilePointer();
            for (Student student: students) {
                student.saveRAF(raf);
            }
            raf.close();
        }
    }

    private Student loadStudentsRAF(String destFile, int index) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(destFile, "rw");
        int numberOfStudents = raf.readInt();
        if (index > numberOfStudents || index < 0) {
            raf.close();
            System.out.println("Null");
            return null;
        }
        long lengthOfObject = (raf.length()-4) / numberOfStudents;
        long position = lengthOfObject*(index-1) + 4;
        Student student = new Student();
        raf.seek(position);
        student.loadRAF(raf);
        raf.close();

        System.out.println(student.toString());

        return student;
    }
}
