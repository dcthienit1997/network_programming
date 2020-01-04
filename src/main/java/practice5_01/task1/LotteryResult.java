package practice5_01.task1;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.rmi.registry.LocateRegistry;

/**
 * @project network-programming
 * @user DinhChiThien on 1/4/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class LotteryResult {

    public static void main(String[] args) throws IOException {
        String resultFilePath = "E:\\testNetworkProgramming\\pr5\\result.txt";
        LotteryResult lotteryResult = new LotteryResult();
//        lotteryResult.saveLotteryResult(resultFilePath);
        System.out.println(lotteryResult.getLotteryResult(resultFilePath, "TIENGIANG"));
    }

    public String getLotteryResult(String resultFilePath, String otherCity) throws IOException {
        File file = new File(resultFilePath);
        StringBuilder result = new StringBuilder("NOT FOUNDED !");
        if (file.exists()) {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            long pos = 0;
            int numberOfCity = (int) (raf.length() / 1800);
            System.out.println(numberOfCity);
            for (int i = 0; i < numberOfCity; i++) {
                pos = i * 1800;
                raf.seek(pos);
                String city = "";
                for (int j = 0; j < 100; j++) {
                    city += raf.readChar();
                }
                if (city.trim().equalsIgnoreCase(otherCity)) {
                    System.out.println("here");
                    result = new StringBuilder(city + "\n");
                    for (int k = 0; k < 8; k++) {
                        String line = "";
                        for (int l = 0; l < 100; l++) {
                            line += raf.readChar();
                        }
                        result.append(line).append("\n");
                    }
                    break;
                }
            }
        }
        return result.toString();
    }

    void saveLotteryResult(String resultFilePath) throws IOException {

        File file = new File(resultFilePath);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        while (true) {
            String content = console.readLine().trim();
            if (content.equalsIgnoreCase("quit")) {
                break;
            } else {
                while (content.length() < 100) {
                    content += " ";
                }
                long pos = raf.length();
                raf.seek(pos);
                raf.writeChars(content);
                System.out.println(pos);
                ;
            }
        }
        raf.close();
        console.close();
    }
}
