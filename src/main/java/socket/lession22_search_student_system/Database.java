package socket.lession22_search_student_system;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @project network-programming
 * @user DinhChiThien on 11/11/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Database {
    Student student1 = new Student("An", 45, 5.8);
    Student student2 = new Student("An", 40, 6.8);
    Student student3 = new Student("Binh", 55, 5.9);
    Student student4 = new Student("Bao", 35, 2.6);
    Student student5 = new Student("Bac", 45, 6.9);
    Student student6 = new Student("Cong", 45, 5.8);
    Student student7 = new Student("Peter", 22, 7.2);
    Student student8 = new Student("Banner", 32, 7.5);
    Student student9 = new Student("Chris", 23, 1.2);
    Student student10 = new Student("Thien", 12, 9.9);
    Student student11 = new Student("Thien", 12, 9.9);
    Student student12 = new Student("Binh", 15, 9.9);
    Student student13 = new Student("An", 42, 9.9);
    Student student14 = new Student("Hai", 42, 8.8);
    Student student15 = new Student("Dung", 18, 5.9);
    Student student16 = new Student("Son", 15, 6.9);
    Student student17 = new Student("Quan", 15, 7.0);
    Student student18 = new Student("Quy", 18, 8.2);
    Student student19 = new Student("Minh", 15, 4.1);
    Student student20 = new Student("Hung", 18, 3.7);

    User user1 = new User("admin", "1234");

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    public Database () {
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
        students.add(student12);
        students.add(student13);
        students.add(student14);
        students.add(student15);
        students.add(student16);
        students.add(student17);
        students.add(student18);
        students.add(student19);
        students.add(student20);
        users.add(user1);

    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean isLogin(String username, String password) {
        for (User user: users) {
            System.out.println(username);
            System.out.println(password);
            if (username.trim().equals(user.getUsername()) && password.trim().equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public ArrayList<Student> getStudentByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student: students) {
            if (student.getName().equalsIgnoreCase(name.trim())) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<Student> getStudentByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student: students) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<Student> getStudentByScore(double score) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student: students) {
            if (student.getScore() == score) {
                result.add(student);
            }
        }
        return result;
    }
}

