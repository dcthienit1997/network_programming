package socket.lession21_net_find;

import javax.activity.ActivityCompletedException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/28/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ProcessingConnect extends Thread{

    DataInputStream netIn;
    DataOutputStream netOut;

    ArrayList<Student> database;
    String func;
    String name;
    double score;
    int age;
    String result;

    public ProcessingConnect(Socket socket) throws IOException {
        database = new Database().getAllStudents();
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run(){
        try {
            String command;
            netOut.writeUTF("Welcome to Student Information System !");
            netOut.flush();
            while (true) {
                command = netIn.readUTF();
                try {
                    analysisCommand(command);
                } catch (Exception e) {
                    netOut.writeUTF(e.getMessage());
                    netOut.flush();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void analysisCommand(String command) throws IOException {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(command, "\t");
            if (stringTokenizer.countTokens() > 2) {
                throw new ActivityCompletedException("The command is not right");
            }
            func = stringTokenizer.nextToken();
            String stringData = stringTokenizer.nextToken();

            switch (func) {
                case "findByName":
                    result = findByName(stringData);
                    break;
                case "findByLessScore":
                    result = findByLessScore(stringData);
                    break;
                case "findByLessAge":
                    result = findByLessAge(stringData);
                    break;
                default:
                    break;
            }
            netOut.writeUTF(result);
            netOut.flush();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No Such Element...!");
        } catch (IOException e) {
            throw new IOException("IO Exception...!");
        }
    }

    private String findByName(String stringData) {
        name = stringData;
        String result = "";
        for (Student student: database) {
            if (student.equalName(name)) {
                result += student.toString() + "\n";
            }
        }
        return result;
    }

    private String findByLessAge(String stringData) {
        try {
            age = Integer.parseInt(stringData);
            String result = "";
            for (Student student: database) {
                if (student.isLessAge(age)) result+= student.toString() + "\n";
            }
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Age is not right! Please enter a integer for age.");
        }
    }

    private String findByLessScore(String stringData) {
        try {
            String result = "";
            score = Double.parseDouble(stringData);
            for (Student student: database) {
                if (student.isLessScore(score)) result += student.toString() + "\n";
            }
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Score is not right! Please enter a integer of double for score.");
        }
    }

}
