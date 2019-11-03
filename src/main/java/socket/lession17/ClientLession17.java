package socket.lession17;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/19/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ClientLession17 {

    String ipAdd;
    int port;
    Socket socket;
    DataInputStream dataInputStreamNet;
    DataOutputStream dataOutputStreamNet;

    public ClientLession17() throws IOException {
        ipAdd = "127.0.0.1";
        port = 13;
        socket = new Socket(ipAdd, port);
        dataInputStreamNet = new DataInputStream(socket.getInputStream());
        dataOutputStreamNet = new DataOutputStream(socket.getOutputStream());
    }

    public static void main(String[] args) throws IOException {
        ClientLession17 client = new ClientLession17();
        String path = "E:\\testNetworkProgramming\\week6\\sourceDownload.pdf";
//        client.download(path);
        client.upload(path);
    }

    // copy from client to server
    private void upload(String path) throws IOException {

        System.out.println("Start upload function: " + System.nanoTime());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        String command = stringTokenizer.nextToken().toUpperCase();
        String source = stringTokenizer.nextToken();
        String destination = stringTokenizer.nextToken();

        // Create stream to write into socket
        dataOutputStreamNet.writeUTF(command + " " + destination);
        dataOutputStreamNet.flush();

        int responCode = dataInputStreamNet.readInt();
        System.out.println(responCode);

        if (responCode == 0) {
            File file = new File(source);
            if (!file.exists()) {
                return;
            } else {

                // This inputStream read from the specified at local
                InputStream is = new BufferedInputStream(new FileInputStream(file));

                // transfer...
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    dataOutputStreamNet.write(buffer);
                }
                dataOutputStreamNet.flush();
                is.close();
                socket.close();
            }
        }



    }

    // paste from server to client
    private void download(String path) throws IOException {

        File file = new File(path);
        InputStream inputStreamNet = new BufferedInputStream(socket.getInputStream());
        OutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        byte[] data = new byte[1024];
        while (inputStreamNet.read(data) != -1) {
            os.write(data);
        }
        os.flush();
        os.close();
        socket.close();
    }

}
