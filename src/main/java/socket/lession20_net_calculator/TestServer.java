package socket.lession20_net_calculator;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 10/25/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TestServer {
    public static void main(String[] args) throws IOException {
        int port = 7;
        new Server(port).openConnect();
    }
}
