package socket.lession21_net_find;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 10/28/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TestClient {
    public static void main(String[] args) throws IOException {
        String ipAdd = "localhost";
        int port = 1234;
        new Client(ipAdd, port).connect();
    }
}
