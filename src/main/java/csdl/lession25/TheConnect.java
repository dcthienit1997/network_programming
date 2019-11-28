package csdl.lession25;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 11/26/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {

    BufferedReader br;
    PrintWriter pw;
    DAO dao;

    public TheConnect(Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        dao = new DAO();
    }

    @Override
    public void run() {
        try {
            welcome();
            while (true) {

                String line = br.readLine();

                // check `exit` command
                if (line.equalsIgnoreCase("exit")) break;

                StringTokenizer stringTokenizer = new StringTokenizer(line);
                String command = stringTokenizer.nextToken();
                StringBuilder value = new StringBuilder();
                while (stringTokenizer.hasMoreTokens()) value.append(stringTokenizer.nextToken()).append(" ");

                List<Student> students = new ArrayList<>();
                switch (command.toUpperCase()) {
                    case "FINDBYNAME":
                        students = findByName(value.toString().trim());
                        break;
                    case "FINDBYAGE":
                        students = findByAge(value.toString().trim());
                        break;
                    default:
                        pw.println(">>_  The " + command + " is not valid!");
                        pw.println(">>_  Please, enter again.");
                        pw.println("");
                        break;
                }
                if (students != null) {
                    showResult(students);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showResult(List<Student> students) {
        if (students.size() == 0) {
            pw.println(">>_  No founded student.");
        } else if (students.size() == 1) {
            pw.println(">>_  There is one founded student.");
            pw.println("");
            pw.println("               " + students.get(0).toString());
        } else {
            pw.println(">>_  There are " + students.size() + " founded students.");
            pw.println("");
            for (Student student : students) {
                pw.println("               " + student.toString());
            }
        }
        pw.println("");
    }

    private List<Student> findByAge(String value) throws SQLException {
        int age = Integer.parseInt(value);
        return dao.getStudents(age);
    }

    private void welcome() {
        pw.println(">>_  WELCOME TO STUDENT SYSTEM");
        pw.println("");
        pw.println("/*      > Direction:                                      /");
        pw.println("/*      Use command below to search student               /");
        pw.println("/*      >  findByName John    : search student by name    /");
        pw.println("/*      >  findByAge 18       : search student by age     /");
        pw.println("/*      >  findByScore John   : search student by score   /");
        pw.println("/*                                                        /");
        pw.println("");
    }

    private List<Student> findByName(String value) throws SQLException {
        return dao.getStudents(value);
    }
}
