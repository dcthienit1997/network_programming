package practice4_tcp_dtb_atm;

import org.apache.xmlbeans.impl.regex.REUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import sun.org.mozilla.javascript.internal.ast.NewExpression;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 12/29/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {

    DataInputStream netIn;
    DataOutputStream netOut;
    DAO dao;

    public TheConnect(Socket socket) throws IOException {
        netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        dao = new DAO();
    }

    @Override
    public void run() {
        try {
            welcome();
            String line;
            while (true) {
                line = netIn.readUTF();
                System.out.println(line);
                if (line.equalsIgnoreCase("quit")) {
                    dao.close();
                    break;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                String command = stringTokenizer.nextToken();
                switch (command) {
                    case "getbalance":
                        getBalance(stringTokenizer.nextToken());
                        break;
                    case "withdraw":
                        withdraw(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                        break;
                    case "deposit":
                        deposit(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                        break;
                    case "transfer":
                        transfer(stringTokenizer.nextToken(), stringTokenizer.nextToken(), stringTokenizer.nextToken());
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void transfer(String thisName, String amount, String thatName) throws IOException, SQLException {
        try {
            boolean isTransfer = dao.transfer(thisName, Double.parseDouble(amount), thatName);
            if (isTransfer) netOut.writeUTF("Transfer complete!");
            else netOut.writeUTF("Sorry! Cannot transfer. Please, try again!");
            netOut.flush();
        } catch (SQLException e) {
            netOut.writeUTF(e.getMessage());
            netOut.flush();
        }
    }

    private void deposit(String name, String amount) throws IOException {
        try {
            int isDeposit = dao.deposit(name, Double.parseDouble(amount));
            if (isDeposit != 0) netOut.writeUTF("Deposit complete!");
            else netOut.writeUTF("Sorry! Cannot deposit. Please, try again!");
            netOut.flush();
        } catch (SQLException e) {
            netOut.writeUTF(e.getMessage());
            netOut.flush();
        }
    }

    private void withdraw(String name, String amount) throws IOException {
        try {
            int isWithdraw = dao.withdraw(name, Double.parseDouble(amount));
            if (isWithdraw != 0) netOut.writeUTF("Withdraw complete! ");
            else netOut.writeUTF("Sorry! Cannot withdraw. Please, try again!");
            netOut.flush();
        } catch (SQLException e) {
            netOut.writeUTF(e.getMessage());
            netOut.flush();
        }
    }

    private void getBalance(String name) throws IOException {
        try {
            double money = dao.getBalance(name);
            if (money == -1) netOut.writeUTF("User not exist in bank system");
            else netOut.writeUTF(String.valueOf(money));
            netOut.flush();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            netOut.writeUTF(e.getMessage());
            netOut.flush();
        }
    }

    private void welcome() throws IOException {
        String welcome = "Welcome to NLU Bank \n";
        netOut.writeUTF(welcome);
        netOut.flush();
    }
}
