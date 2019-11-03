package socket.lession18_upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/21/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Server {

    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;

    public Server(ServerSocket serverSocket) throws IOException {
        System.out.println("Server is waiting connect...");
        socket = serverSocket.accept();
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
    }

    public void start() throws IOException {

        String command, destination;
        String line = netIn.readUTF();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        command = stringTokenizer.nextToken().toUpperCase();
        destination = stringTokenizer.nextToken();

        // check command
        switch (command) {
            case "COPY":
                saveFile(destination);
                break;
            default:
                break;
        }



    }

    private void saveFile(String destination) throws IOException {
        OutputStream os;
        try {
            os = new BufferedOutputStream(new FileOutputStream(destination));
            netOut.writeInt(0);
            netOut.flush();
            int data;
            while ((data = netIn.read()) != -1) {
                os.write(data);
            }
            os.flush();
            os.close();
            System.out.println("Copy done !");
        } catch (IOException e) {
            netOut.writeInt(-1);
            netOut.flush();
            e.printStackTrace();
        }
    }

}
