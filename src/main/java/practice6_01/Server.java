package practice6_01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @project network-programming
 * @user DinhChiThien on 1/5/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Server {

    static int PORT = 12345;
    ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void openConnect() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            TheConnect connect = new TheConnect(socket);
            connect.start();
        }
    }
}
