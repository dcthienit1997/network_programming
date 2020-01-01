package practice1_tcp_fileservice;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 12/23/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ClientTest {
    public static void main(String[] args) throws IOException {
        String address = "127.0.0.1";
        int port = 12345;
        Client client = new Client(address, port);
        client.connect();
    }
}
