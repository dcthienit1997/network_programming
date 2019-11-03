package io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @project network-programming
 * @user DinhChiThien on 9/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession12 {
    public static void main(String[] args) throws IOException {
        String destFile = "E:\\testNetworkProgramming\\week4\\students - Copy.txt";
        String destFile2 = "E:\\testNetworkProgramming\\week4\\studentsDOS.txt";
        ArrayList<Student> students = new ArrayList<Student>();
        Student student1 = new Student("123", "Nguyễn A", 5.5);
        Student student2 = new Student("456", "Trần Văn B", 7.8);
        Student student3 = new Student("789", "Phạm bla bla bla bla bla C", 10.0);
        Student student4 = new Student("101", "Đinh D", 1.9);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        new Lession12().writeStudents(destFile2, students);
        new Lession12().readStudents(destFile2);
    }

    private void writeStudents(String destFile, ArrayList<Student> students) throws IOException {
        if (students.isEmpty()) {
            return;
        } else {
            OutputStream os = new FileOutputStream(destFile);
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(students.size());
            for (Student student : students) {
                student.save(dos);
            }
            dos.flush();
            dos.close();
        }
    }

    private void readStudents(String sourceFile) throws IOException {
        File source = new File(sourceFile);
        if (!source.exists()) {
            return;
        } else {
            DataInputStream dis = new DataInputStream(new FileInputStream(source));
            ArrayList<Student> students = new ArrayList<Student>();
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                Student student = new Student();
                student.load(dis);
                students.add(student);
            }
            dis.close();

            for (Student student: students) {
                System.out.println(student.toString());
            }
        }
    }

}
