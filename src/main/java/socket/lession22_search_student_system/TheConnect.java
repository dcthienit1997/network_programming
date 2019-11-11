package socket.lession22_search_student_system;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 11/11/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {

    BufferedReader br;
    PrintWriter pw;
    boolean isLogin;
    Database database;
//    ArrayList<Student> studentDatabase;
//    ArrayList<User> userDatabase;

    public TheConnect(Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        database = new Database();
//        studentDatabase = database.students;
//        userDatabase = database.users;
        isLogin = false;
    }

    @Override
    public void run() {
        try {
            // welcome
            pw.println("/******************************************************/");
            pw.println("/                                                      /");
            pw.println("/*    Welcome to Search Student System by Network!     /");
            pw.println("/*    Please enter USERNAME and PASSWORD step by step  /");
            pw.println("/                                                      /");
            pw.println("/******************************************************/");

            String line;
            while (true) {
                if (!isLogin) {
                    line = br.readLine();
                    if (line.toUpperCase().equals("EXIT")) {
                        pw.println("You're logout system.");
                        break;
                    }
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    if (stringTokenizer.countTokens() != 2) {
                        pw.println("/***    Enter USERNAME, please ! **/");
                        pw.println("/**********************************/");
                    } else {
                        String token1 = stringTokenizer.nextToken();

                        switch (token1.toUpperCase()) {
                            case "USER":
                                isLogin = handleLogin(stringTokenizer.nextToken());
                                break;
                            default:
                                pw.println("/###" + token1 + " is not command");
                                break;
                        }
                    }
                } else {

                    String commandSystem = br.readLine();

                    if (commandSystem.toUpperCase().equals("EXIT")) {
                        pw.println("You're logout system.");
                        break;
                    }

                    StringTokenizer stringTokenizer = new StringTokenizer(commandSystem);
                    if (stringTokenizer.countTokens() != 2) {
                        pw.println("/### " + "Please enter legal command ###/");
                        pw.println("/#######################################/");
                    } else {
                        String command = stringTokenizer.nextToken();
                        ArrayList<Student> result;
                        switch (command) {
                            case "findByName":
                                result = database.getStudentByName(stringTokenizer.nextToken());
                                displayResult(result);
                                break;
                            case "findByAge":
                                result = database.getStudentByAge(Integer.valueOf(stringTokenizer.nextToken()));
                                displayResult(result);
                                break;
                            case "findByScore":
                                result = database.getStudentByScore(Double.valueOf(stringTokenizer.nextToken()));
                                displayResult(result);
                                break;
                            default:
                                pw.println("/####" + command + " is not command !  ###/");
                                break;
                        }
                    }
                }
            }

            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayResult(ArrayList<Student> result) {
        if (result.size() == 0) {
            pw.println(">   No student was founded.");
            pw.println(">___.");
            pw.println("");
        } else if (result.size() == 1) {
            pw.println(">   There is 1 student: ");
            pw.println(">   " + result.get(0));
            pw.println(">___.");
            pw.println("");
        } else {
            pw.println(">   There are " + result.size() + " student: ");
            for (Student student: result) {
                pw.println(">   " + student.toString());
            }
            pw.println(">___.");
            pw.println("");
        }
    }

    private boolean handleLogin(String nextToken) throws IOException {
        pw.println("/*** 354                                **/");
        pw.println("/*      Enter PASSWORD for that username  /");
        pw.println("/*                                        /");
        pw.println("/*****************************************/");

        String pwLine = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(pwLine);
        if (stringTokenizer.countTokens() != 2) {
            return false;
        } else {
            String token1 = stringTokenizer.nextToken();
            boolean right = false;
            switch (token1.toUpperCase()) {
                case "PASS":
                    right = login(nextToken, stringTokenizer.nextToken());
                    break;
                default:
                    pw.println("/###" + token1 + " is not command");
                    break;
            }
            return right;
        }
    }

    private boolean login(String nextToken, String nextToken1) {
        // login
        boolean isSuccess = database.isLogin(nextToken, nextToken1);
        if (isSuccess) {
            pw.println("/*** 204                          **/");
            pw.println("/*      Login is SUCCESSFUL         /");
            pw.println("/*                                  /");
            pw.println("/***********************************/");
            pw.println("/*********************************************************/");
            pw.println("/*                                                        /");
            pw.println("/*      You're login Search Student System                /");
            pw.println("/*      > Direction:                                      /");
            pw.println("/*      Use command below to search student               /");
            pw.println("/*      >  findByName John    : search student by name    /");
            pw.println("/*      >  findByAge 18       : search student by age     /");
            pw.println("/*      >  findByScore John   : search student by score   /");
            pw.println("/*                                                        /");
            pw.println("/*********************************************************/");

        } else {
            pw.println("/*** 202                                 **/");
            pw.println("/*     Username or password is not exist   /");
            pw.println("/*     Try again, please!                  /");
            pw.println("/*                                         /");
            pw.println("/******************************************/");
        }
        return isSuccess;
    }
}
