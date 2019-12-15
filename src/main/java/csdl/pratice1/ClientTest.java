package csdl.pratice1;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 12/2/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ClientTest {
    public static void main(String[] args) throws IOException {
        String url = "localhost";
        int port = 1234;
        Client client = new Client(url, port);
        client.connect();
    }
}
