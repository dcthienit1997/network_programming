package practice4_tcp_dtb_atm;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 12/29/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ClientTEST {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect();
        client.close();
    }
}
