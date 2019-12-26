package practice2.client;

import practice2.server.FileImpl;
import practice2.server.IFile;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 12/26/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {

    BufferedReader userIn;
    boolean isLogin;
    String uuid;
    BufferedInputStream in;
    BufferedOutputStream out;

    public Client() {
        userIn = new BufferedReader(new InputStreamReader(System.in));
        isLogin = false;
    }

    public static void main(String[] args) throws IOException, NotBoundException {
        Client client = new Client();
        client.start();
    }

    public void start() throws IOException, NotBoundException {
        Registry client = LocateRegistry.getRegistry("127.0.0.1", 12345);
        IFile file = (IFile) client.lookup("file");

        System.out.println("Welcome to NLU File Service...");
        String line;
        while (true) {
            try {
                line = userIn.readLine();

                // Exit if `QUIT`
                if (line.trim().equals("QUIT")) break;

                if (!isLogin) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    switch (stringTokenizer.nextToken()) {
                        case "TEN":
                            try {
                                String username = stringTokenizer.nextToken();
                                if (file.findUsername(username)) {
                                    System.out.println("Continue enter password for `" + username + "` account");
                                } else System.out.println("Username not exist, please enter again!");
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error!");
                            }
                            break;
                        case "MATKHAU":
                            try {
                                String password = stringTokenizer.nextToken();
                                uuid = file.checkPass(password);
                                if (uuid != null) {
                                    isLogin = true;
                                    System.out.println("Login is success!");
                                } else System.out.println("Password wrong. Please enter username again!");
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error!");
                            }
                            break;
                        default:
                            System.out.println("Please login!");
                            break;
                    }
                } else {
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    String command = stringTokenizer.nextToken();
                    String sf, df;
                    switch (command) {
                        case "GET":
                            try {
                                sf = stringTokenizer.nextToken();
                                df = stringTokenizer.nextToken();
                                file.openSource(uuid, sf);
                                out = new BufferedOutputStream(new FileOutputStream(new File(df)));
                                byte[] data;
                                while ((data = file.readData(uuid)) != null) {
                                    out.write(data, 0, data.length);
                                }
                                file.closeSource(uuid);
                                out.flush();
                                out.close();
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error!");
                            } catch (FileNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "SEND":
                            try {
                                sf = stringTokenizer.nextToken();
                                df = stringTokenizer.nextToken();
                                file.createDestinationFile(uuid, df);
                                in = new BufferedInputStream(new FileInputStream(sf));
                                byte[] data = new byte[1024 * 10];
                                int length;
                                while ((length = in.read(data)) != -1) {
                                    file.writeData(uuid, data, length);
                                }
                                in.close();
                                file.closeDestination(uuid);
                            } catch (NoSuchElementException e) {
                                System.out.println("Syntax error!");
                            } catch (FileNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        default:
                            System.out.println("Syntax error!");
                            break;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        userIn.close();
    }
}
