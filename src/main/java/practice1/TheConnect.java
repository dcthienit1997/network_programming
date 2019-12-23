package practice1;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 12/22/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {
    DataInputStream netIn;
    DataOutputStream netOut;
    String serverDir;

    public TheConnect(Socket socket) throws IOException {
        netIn = new DataInputStream(socket.getInputStream());
        netOut = new DataOutputStream(socket.getOutputStream());
        serverDir = "C:\\dest";
    }

    @Override
    public void run() {
        try {
            String line;
            while (true) {
                try {
                    line = netIn.readUTF();
                    if (line.trim().equals("QUIT")) break;
                    ;
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    String command = stringTokenizer.nextToken();
                    switch (command) {
                        case "set_server_dir":
                            serServerDir(stringTokenizer.nextToken());
                            break;
                        case "send":
                            saveFileOnServer(stringTokenizer.nextToken());
                            break;
                        case "get":
                            findFileOnServer(stringTokenizer.nextToken());
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    private void findFileOnServer(String fileOnServer) throws IOException {
        File file = new File(serverDir + File.separator + fileOnServer);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            netOut.writeInt(1);
            download(file);
        } else {
            netOut.writeInt(0);
        }
    }

    private void download(File fileOnServer) throws IOException {
        netOut.writeLong(fileOnServer.length());
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileOnServer));
        int data;
        byte[] buffer = new byte[1024 * 10];
        while ((data = bis.read(buffer, 0, buffer.length)) != -1) {
            netOut.write(buffer, 0, data);
        }
        netOut.flush();
    }

    private void saveFileOnServer(String newFile) throws IOException {
        File file = new File(serverDir + File.separator + newFile);
        int byteRead, byteMustRead;
        byte[] buffer = new byte[1024 * 10];
        long remain = netIn.readLong();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        while (remain > 0) {
            byteMustRead = remain > buffer.length ? buffer.length : (int) remain;
            byteRead = netIn.read(buffer, 0, byteMustRead);
            bos.write(buffer, 0, byteRead);
            remain -= byteRead;
        }
        bos.flush();
        bos.close();
    }

    private void serServerDir(String newDirectory) {
        serverDir = newDirectory;
    }
}
