package csdl.pratice1;

import com.sun.org.apache.regexp.internal.RE;

import java.io.*;
import java.net.Socket;

/**
 * @project network-programming
 * @user DinhChiThien on 12/2/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {
    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;

    public Client(String url, int port) throws IOException {
        socket = new Socket(url, port);
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    public void connect() {
        try {
            String response;
            response = netIn.readUTF();
            System.out.println(response);
            System.out.println("0000");
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
                String command = br.readLine();
                netOut.writeUTF(command);
                netOut.flush();
                response = netIn.readUTF();
                System.out.println(response);
                if (command.equalsIgnoreCase("exit")) {
                    netOut.close();
                    netIn.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
