package practice6_01;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 1/5/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {

    BufferedReader netIn;
    PrintWriter netOut;
    boolean isLogin;
    Dao dao;
    OAuth oAuth;

    public TheConnect(Socket socket) throws IOException {
        netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        dao = new Dao();
        oAuth = new OAuth();
    }

    @Override
    public void run() {
        try {
            welcome();
            String line;
            while (true) {
                line = netIn.readLine();
                if (line.trim().toUpperCase().equals("QUIT")) {
                    oAuth.logout();
                    isLogin = false;
                    break;
                }
                if (!isLogin) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
                    switch (stringTokenizer.nextToken()) {
                        case "TEN":
                            try {
                                String username = stringTokenizer.nextToken();
                                if (oAuth.findUsername(username)) {
                                    netOut.println("Enter password...");
                                } else netOut.println("Username not exist!");
                            } catch (NoSuchElementException e) {
                                netOut.println(e.getMessage());
                            }
                            break;
                        case "MATKHAU":
                            try {
                                String password = stringTokenizer.nextToken();
                                if (oAuth.checkPass(password)) {
                                    isLogin = true;
                                    netOut.println("Login success");
                                } else {
                                    netOut.println("Password wrong. Please enter username again.");
                                }
                            } catch (NoSuchElementException e) {
                                netOut.println(e.getMessage());
                            }
                            break;
                        default:
                            netOut.println("Syntax error");
                            break;
                    }
                } else {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
                    String command = stringTokenizer.nextToken();
                    switch (command) {
                        case "GUI":
                            try {
                                String money = stringTokenizer.nextToken();
                                deposit(money);
                            } catch (NoSuchElementException e) {
                                netOut.println(e.getMessage());
                            }
                            break;
                        case "LAY":
                            try {
                                String money = stringTokenizer.nextToken();
                                withdraw(money);
                            } catch (NoSuchElementException e) {
                                netOut.println(e.getMessage());
                            }
                            break;
                        case "CHUYEN":
                            try {
                                String otherAccount = stringTokenizer.nextToken();
                                String money = stringTokenizer.nextToken();
                                transfer(otherAccount, money);
                            } catch (NoSuchElementException e) {
                                netOut.println(e.getMessage());
                            }
                            break;
                        default:
                            netOut.println("Syntax error");
                            break;
                    }
                }
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transfer(String otherAccount, String money) {
        try {
            double amount = Double.parseDouble(money);
            if (oAuth.transfer(otherAccount, amount)) netOut.println("Transfer success");
            else netOut.println("Failed");
        } catch (NumberFormatException e) {
            netOut.println(e.getMessage());
        }
    }

    private void withdraw(String money) {
        try {
            double amount = Double.parseDouble(money);
            if (oAuth.withdraw(amount)) netOut.println("Withdraw success");
            else netOut.println("Your account very low");
        } catch (NumberFormatException e) {
            netOut.println("Money illegal");
        }
    }

    private void deposit(String money) {
        try {
            double amount = Double.parseDouble(money);
            oAuth.deposit(amount);
            netOut.println("Deposited");
        } catch (NumberFormatException e) {
            netOut.println("Money illegal");
        }
    }

    private void welcome() {
        String welcome = "Welcome to NLU e-Bank...";
        netOut.println(welcome);
    }

    public void close() throws IOException {
        netOut.close();
        netIn.close();
    }
}
