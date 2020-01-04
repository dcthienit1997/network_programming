package practice5_01.task2;

import practice5_01.task1.LotteryResult;

import java.net.*;

/**
 * @project network-programming
 * @user DinhChiThien on 1/4/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Server {

    static String ADDRESS = "127.0.0.1";
    static int PORT = 2000;
    String resultFilePath = "E:\\testNetworkProgramming\\pr5\\result.txt";
    LotteryResult lotteryResult;
    private InetAddress inetAddress;


    public Server() throws UnknownHostException {
        inetAddress = InetAddress.getByName(ADDRESS);
        lotteryResult = new LotteryResult();
    }

    public void start() {
        try {

            while (true) {

                byte[] buffer = new byte[1024 * 10];

                DatagramPacket packetRECEIVE = new DatagramPacket(buffer, buffer.length);
                DatagramSocket socketRECEIVE = new DatagramSocket(PORT);
                socketRECEIVE.receive(packetRECEIVE);
                socketRECEIVE.close();

                String content = getData(buffer).trim();
                String result = lotteryResult.getLotteryResult(resultFilePath, content);

                byte[] dataSEND = result.getBytes();
                DatagramPacket packetSEND = new DatagramPacket(dataSEND, dataSEND.length, inetAddress, PORT);
                DatagramSocket socketSEND = new DatagramSocket();
                socketSEND.send(packetSEND);
                socketSEND.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getData(byte[] buffer) {
        if (buffer != null) {
            String content = new String(buffer);
            return content;
        } else return null;
    }
}
