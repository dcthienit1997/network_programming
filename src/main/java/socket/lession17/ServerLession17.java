package socket.lession17;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/19/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ServerLession17 {

    int port;
    Socket socket;
    DataInputStream dataInputStreamNet;
    DataOutputStream dataOutputStreamNet;

    public ServerLession17() throws IOException {
        port = 13;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is waiting to accept user...");
        socket = serverSocket.accept();
        dataInputStreamNet = new DataInputStream(socket.getInputStream());
        dataOutputStreamNet = new DataOutputStream(socket.getOutputStream());
        System.out.println("Server was accept connect");

    }

    public static void main(String[] args) throws IOException {
        ServerLession17 server = new ServerLession17();
        String path = "E:\\testNetworkProgramming\\week6\\destination.pdf";
        server.download(path);
//        server.upload(path);
    }

    private void upload(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return;
        } else {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            OutputStream outputStreamNet = new BufferedOutputStream(socket.getOutputStream());
            int data;
            while ((data = is.read()) != -1) {
                outputStreamNet.write(data);
            }
            outputStreamNet.flush();
            is.close();
            socket.close();
        }
    }

    private void download(String path) throws IOException {
        System.out.println("Start download function: " + System.nanoTime());
        String line = dataInputStreamNet.readUTF();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        String command = stringTokenizer.nextToken().toUpperCase();
        String destination = stringTokenizer.nextToken();

        switch (command) {
            case "COPY":
                copy(destination);
                break;
            default:
                break;
        }
        socket.close();
    }

    private void copy(String destination) throws IOException {
        File file = new File(destination);
        try {
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            dataOutputStreamNet.writeInt(0);
            dataOutputStreamNet.flush();
            int data;
            while ((data = dataInputStreamNet.read()) != -1) {
                os.write(data);
            }
            os.flush();
            os.close();
        } catch (Exception e) {
            dataOutputStreamNet.writeInt(-1);
            dataOutputStreamNet.flush();
            System.out.println(e);
        }

    }
}
