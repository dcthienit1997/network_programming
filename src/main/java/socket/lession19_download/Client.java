package socket.lession19_download;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/21/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {

    DataInputStream netIn;
    DataOutputStream netOut;

    public Client(String ipAdd, int port) throws IOException {
        Socket socket = new Socket(ipAdd, port);
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    public void download() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command, sf, df;
        String line = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        command = stringTokenizer.nextToken();
        sf = stringTokenizer.nextToken();
        df = stringTokenizer.nextToken();

        netOut.writeUTF(command + " " + sf);
        int responseCode = netIn.readInt();
        if (responseCode == -1) {
            System.out.println("File not found");
            netIn.close();
            netOut.close();
        } else if (responseCode == 0) {
            paste(df);
            netIn.close();
            netOut.close();
            System.out.println("Downloaded");
        }
    }

    private void paste(String df) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(df));
        byte[] data = new byte[1024];

        while (netIn.read(data) != -1) {
            os.write(data);
        }
        os.flush();
        os.close();
    }

}
