package practice4_tcp_dtb_atm;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 12/29/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {

    static String HOST = "127.0.0.1";
    static int PORT = 12345;
    DataInputStream netIn;
    DataOutputStream netOut;
    BufferedReader console;

    public Client() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public void connect() {
        try {
            String welcome = netIn.readUTF();
            System.out.println(welcome);
            while (true) {

                String line;
                try {
                    line = console.readLine();
                    if (line.trim().equalsIgnoreCase("quit")) {
                        netOut.writeUTF(line);
                        netOut.flush();
                        break;
                    }
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    String command = stringTokenizer.nextToken();
                    switch (command) {
                        case "withdraw":
                            try {
                                String name = stringTokenizer.nextToken();
                                String amount = stringTokenizer.nextToken();
                                withdraw(name, amount);
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error");
                            }
                            break;
                        case "deposit":
                            try {
                                String name = stringTokenizer.nextToken();
                                String amount = stringTokenizer.nextToken();
                                deposit(name, amount);
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error");
                            }
                            break;
                        case "getBalance":
                            try {
                                String name = stringTokenizer.nextToken();
                                getBalance(name);
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error");
                            }
                            break;
                        case "transfer":
                            try {
                                String thisName = stringTokenizer.nextToken();
                                String amount = stringTokenizer.nextToken();
                                String thatName = stringTokenizer.nextToken();
                                transfer(thisName, amount, thatName);
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error");
                            }
                            break;
                        default:
                            System.out.println("Syntax error");
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deposit(String name, String amount) throws IOException {
        try {
            Double.parseDouble(amount);
            netOut.writeUTF("deposit " + name + " " + amount);
            netOut.flush();
            System.out.println(netIn.readUTF());
        } catch (NumberFormatException e) {
            System.out.println("Syntax error");
        }
    }

    private void withdraw(String name, String amount) throws IOException {
        try {
            Double.parseDouble(amount);
            netOut.writeUTF("withdraw " + name + " " + amount);
            netOut.flush();
            System.out.println(netIn.readUTF());
        } catch (NumberFormatException e) {
            System.out.println("Syntax error");
        }
    }

    private void getBalance(String name) throws IOException {
        netOut.writeUTF("getbalance " + name);
        netOut.flush();
        System.out.println(netIn.readUTF());
    }

    private void transfer(String thisName, String amount, String thatName) throws IOException {
        try {
            Double.parseDouble(amount);
            netOut.writeUTF("transfer " + thisName + " " + amount + " " + thatName);
            netOut.flush();
            System.out.println(netIn.readUTF());
        } catch (NumberFormatException e) {
            System.out.println("Syntax error");
        }
    }

    public void close() throws IOException {
        netIn.close();
        netOut.close();
        console.close();
    }
}