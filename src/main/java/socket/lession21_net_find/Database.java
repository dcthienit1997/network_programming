package socket.lession21_net_find;

import java.util.ArrayList;

/**
 * @project network-programming
 * @user DinhChiThien on 10/28/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Database {
    Student student1 = new Student("A1", "Nguyễn A", 5.5, 11);
    Student student2 = new Student("A1", "Nguyễn B", 5.5, 12);
    Student student3 = new Student("A1", "Nguyễn C", 6.5, 13);
    Student student4 = new Student("A1", "Nguyễn D", 8.0, 15);
    Student student5 = new Student("A1", "Nguyễn E", 5.5, 16);
    Student student6 = new Student("A1", "Nguyễn AB", 5.5, 88);
    Student student7 = new Student("A1", "Nguyễn A B", 6.9, 98);
    Student student8 = new Student("A1", "Nguyễn ABC", 5.5, 108);
    Student student9 = new Student("A1", "Nguyễn An", 5.5, 8);
    Student student10 = new Student("A1", "Nguyễn Anh", 5.5, 18);
    Student student11 = new Student("A1", "Nguyễn Hoang", 1.2, 128);

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students.add(student8);
        students.add(student9);
        students.add(student10);
        students.add(student11);
        return students;
    }

    public static void main(String[] args) {
        ArrayList<Student> sts = new Database().getAllStudents();
        for (Student st: sts) {
            System.out.println(st.toString());
        }
    }



}
