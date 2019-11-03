package socket.lession18_upload;

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
    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;

    public Client (String ipAdd, int port) throws IOException {
        socket = new Socket(ipAdd, port);
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());

    }

    public void start() throws IOException {
        System.out.println("Please enter command: ");
        String command, source, destination;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        command = stringTokenizer.nextToken().toUpperCase();
        source = stringTokenizer.nextToken();
        destination = stringTokenizer.nextToken();

        // enter command from keyboard
        netOut.writeUTF(command + " " + destination);
        netOut.flush();

        int responseCode = netIn.readInt();
        if (responseCode == 0) {
            if (upload(source)) {
                System.out.println("|>>> File was uploaded to network.");
                System.out.println("|>>> Finish.");
                netOut.close();
                netIn.close();
            } else {
                System.out.println("|>>> File source not found !");
                System.out.println("|>>> Stop connect.");
                netOut.close();
                netIn.close();
            }
        }


    }

    private boolean upload(String source) throws IOException {
        File file = new File(source);
        if (!file.exists()) {
            return false;
        } else {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            int data;
            while ((data = is.read()) != -1) {
                netOut.write(data);
            }
            netOut.flush();
            is.close();
            return true;
        }
    }

}
