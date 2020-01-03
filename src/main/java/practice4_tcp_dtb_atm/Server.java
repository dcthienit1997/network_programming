package practice4_tcp_dtb_atm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @project network-programming
 * @user DinhChiThien on 12/29/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Server {

    static int PORT = 12345;
    ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void openConnect() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                TheConnect connect = new TheConnect(socket);
                connect.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
