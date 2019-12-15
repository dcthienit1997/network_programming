package csdl.pratice1;

import csdl.lession25.Student;
import csdl.pratice1.dtb.DAO;

import java.awt.image.RescaleOp;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 12/2/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {

    DataInputStream netIn;
    DataOutputStream netOut;
    DAO dao;

    public TheConnect(Socket socket) throws IOException {
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
        dao = new DAO();
    }

    @Override
    public void run() {
        try {
            welcome();
            while (true) {

                String line = netIn.readUTF();

                if (line.equalsIgnoreCase("exit")) {
                    netOut.writeUTF(">>_   SEE YOU AGAIN !!!" + "\n");
                    netOut.flush();
                    break;
                }

                StringTokenizer stringTokenizer = new StringTokenizer(line);
                int tokents = stringTokenizer.countTokens();
                if (tokents == 2) {
                    handle2Tokens(stringTokenizer);
                } else if (tokents == 3) {
                    handle3Tokens(stringTokenizer);
                } else if (tokents == 4) {
                    handle4Tokens(stringTokenizer);
                } else {
                    netOut.writeUTF(">>_ PLEASE, ENTER AGAIN" + "\n");
                    netOut.flush();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handle3Tokens(StringTokenizer stringTokenizer) throws SQLException, IOException {
        String name = stringTokenizer.nextToken();
        String command = stringTokenizer.nextToken();
        String amt = stringTokenizer.nextToken();
        switch (command.toUpperCase()) {
            case "DEPOSIT":
                deposit(name, amt);
                break;
            case "WITHDRAW":
                withdraw(name, amt);
                break;
            default:
                netOut.writeUTF(">>_   COMMAND IS NOT RIGHT !!!" + "\n" +
                        ">>_   PLEASE, ENTER AGAIN !!!" + "\n");
                netOut.flush();
                break;
        }
    }

    private void withdraw(String name, String amt) throws SQLException, IOException {
        double amount = Double.parseDouble(amt);
        int responseCode = dao.withdraw(name, amount);
        if (responseCode != 0) {
            netOut.writeUTF(">>_   SUCCESSFULL !!!" + "\n");
        } else {
            netOut.writeUTF(">>_   FAIL !!!" + "\n");
        }
        netOut.flush();
        // check return `index`
    }

    private void deposit(String name, String amt) throws SQLException, IOException {
        double amount = Double.parseDouble(amt);
        int responseCode = dao.deposit(name, amount);
        // check return `index`
        if (responseCode != 0) {
            netOut.writeUTF(">>_   SUCCESSFULL !!!" + "\n");
        } else {
            netOut.writeUTF(">>_   FAIL !!!" + "\n");
        }
        netOut.flush();
    }

    private void handle4Tokens(StringTokenizer stringTokenizer) throws SQLException, IOException {
        String thisName = stringTokenizer.nextToken();
        String command = stringTokenizer.nextToken();
        String thatName = stringTokenizer.nextToken();
        String amt = stringTokenizer.nextToken();
        double amount = Double.parseDouble(amt);
        int responseCode = dao.transfer(thisName, thatName, amount);
        if (responseCode == 1) {
            netOut.writeUTF(">>_   SUCCESSFUL !!!" + "\n");
        } else {
            netOut.writeUTF(">>_   FAIL !!!" + "\n");
        }
        netOut.flush();
    }

    private void handle2Tokens(StringTokenizer stringTokenizer) throws SQLException, IOException {
        String name = stringTokenizer.nextToken();
        String command = stringTokenizer.nextToken();
        if (command.equalsIgnoreCase("getBalance")) {
            double balance = dao.checkBalanceOfCustomer(name);
            netOut.writeUTF(">>_   Balance of your bank account:  " + balance + "\n");
            netOut.flush();
        }

    }

    private void welcome() throws IOException {
        netOut.writeUTF(">>_  WELCOME TO BANK SYSTEM" + "\n" +
                "" + "\n" +
                "/*      > Direction:                                                                             /" + "\n" +
                "/*      Use command below to interacted to system:                                               /" + "\n" +
                "/*      >  your_name getBalance                 : check balance in your bank account             /" + "\n" +
                "/*      >  your_name deposit amt                : deposit money into your bank account           /" + "\n" +
                "/*      >  your_name1 transfer your_name2 amt   : transfer money from your_name1 to your_name2   /" + "\n" +
                "/*      >  your_name withdraw amt               : withdraw your money                            /" + "\n" +
                "/*                                                                                               /" + "\n" +
                "" + "\n");
        netOut.flush();
    }
}
