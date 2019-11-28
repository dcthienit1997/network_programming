package csdl.lession25;

import java.io.IOException;
import java.net.Socket;

/**
 * @project network-programming
 * @user DinhChiThien on 11/25/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {
    Socket socket;

    public Client(String url, int port) throws IOException {
        socket = new Socket(url, port);
    }

    public void connect() {

    }
}
