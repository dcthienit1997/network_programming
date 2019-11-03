package socket.lession21_net_find;

import java.io.*;
import java.net.Socket;

/**
 * @project network-programming
 * @user DinhChiThien on 10/28/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {
    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;

    public Client(String ipAdd, int port) throws IOException {
        socket = new Socket(ipAdd, port);
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    public void connect() {
        try {
            String response;
            response = netIn.readUTF();
            System.out.println(response);
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
                String command;
                command = br.readLine();
                netOut.writeUTF(command);
                netOut.flush();
                response = netIn.readUTF();
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
