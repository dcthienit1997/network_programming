package practice1_tcp_fileservice;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 12/22/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {

    Socket socket;
    DataInputStream netIn;
    DataOutputStream netOut;
    BufferedReader userIn;
    String clientDir;

    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
        netIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        netOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        userIn = new BufferedReader(new InputStreamReader(System.in));
        clientDir = "C:\\source";
    }

    public void connect() throws IOException {
        while (true) {
            String line;
            try {
                line = userIn.readLine().trim();
                if (line.equals("QUIT")) {
                    netOut.writeUTF(line);
                    netOut.flush();
                    break;
                }
                analysis(line);
            } catch (IOException e) {
                // Bắt ngoại lệ người dùng
                e.printStackTrace();
            }
        }
        netIn.close();
        netOut.flush();
        netOut.close();
        userIn.close();
        socket.close();
    }

    private void analysis(String line) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        String command;
        int count = stringTokenizer.countTokens();
        if (count == 2) {
            command = stringTokenizer.nextToken();
            switch (command) {
                case "SET_SERVER_DIR":
                    setServerDir(stringTokenizer.nextToken());
                    break;
                case "SET_CLIENT_DIR":
                    setClientDir(stringTokenizer.nextToken());
                    break;
                default:
                    break;
            }
        } else if (count == 3) {
            command = stringTokenizer.nextToken();
            switch (command) {
                case "SEND":
                    sendToServer(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                    break;
                case "GET":
                    getFromServer(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Lệnh không đúng cú pháp !" + "\n" +
                    "Vui lòng nhập lại" + "\n");
        }
    }

    private void setClientDir(String clientDirOther) {
        clientDir = clientDirOther;
    }

    private void setServerDir(String serverDirOther) throws IOException {
        netOut.writeUTF("set_server_dir" + " " + serverDirOther);
    }

    private void getFromServer(String fileOnServer, String newFile) throws IOException {
        netOut.writeUTF("get" + " " + fileOnServer);
        netOut.flush();
        int response = netIn.readInt();
        if (response == 1) {
            File file = new File(clientDir + File.separator + newFile);
            download(file);
        }
    }

    private void download(File newFile) throws IOException {
        long length = netIn.readLong();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile));
        byte[] buffer = new byte[1024 * 10];
        int data;
        long remain = length;
        while (remain > 0) {
            data = netIn.read(buffer, 0, buffer.length);
            bos.write(buffer, 0, data);
            remain -= data;
        }
        bos.flush();
        bos.close();
    }

    private void sendToServer(String fileOnClient, String newFile) throws IOException {
        File file = new File(clientDir + File.separator + fileOnClient);
        if (file.exists()) {
            netOut.writeUTF("send" + " " + newFile);
            upload(file);
        }
    }

    private void upload(File fileOnClient) throws IOException {
        netOut.writeLong(fileOnClient.length());
        byte[] buffer = new byte[1024 * 10];
        int data;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileOnClient));
        while ((data = bis.read(buffer, 0, buffer.length)) != -1) {
            netOut.write(buffer, 0, data);
        }
        netOut.flush();
    }


}
