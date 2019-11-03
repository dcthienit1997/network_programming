package socket.lession19_download;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/24/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {

    DataInputStream netIn;
    DataOutputStream netOut;

    public TheConnect(Socket socket) throws IOException {
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        String line;
        while (true) {
            try {
                line = netIn.readUTF();
                System.out.println(line);
                String command, sf;
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                command = stringTokenizer.nextToken().toUpperCase();
                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                } else {
                    switch (command) {
                        case "GET":
                            sf = stringTokenizer.nextToken();
                            copy(sf);
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            netIn.close();
            netOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copy(String sf) throws IOException {
        File source = new File(sf);
        if (!source.exists()) {
            netOut.writeInt(-1);
        } else {
            netOut.writeInt(0);
            InputStream is = new BufferedInputStream(new FileInputStream(source));
            byte[] data = new byte[1024];
            while (is.read(data) != -1) {
                netOut.write(data);
            }
            netOut.flush();
            is.close();
            netOut.close();
        }
    }
}
