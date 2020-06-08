package io;

import java.io.*;
import java.nio.file.Files;

/**
 * @project network-programming
 * @user DinhChiThien on 9/23/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession8 {
    public boolean fileCopy(String sFile, String destFile, boolean moved) {
        File source = new File(sFile);
        File destination = new File(destFile + "\\Copy" + source.getName());
        if (!source.exists() || !source.isFile()) {
            return false;
        } else {
            if (copy(source, destination)) {
                if (moved) {
                    if(source.delete()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String source = "";
        String destination = "D:\\data-camer-trip2";
        long beginTime = System.currentTimeMillis();
        System.out.println(new Lession8().fileCopy(source, destination, true));
        long endTime = System.currentTimeMillis();
        System.out.println("Time:  " + (endTime - beginTime) + " ms");
    }

    private boolean copy(File source, File destination) {
        try {
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(destination);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            byte[] data = new byte[1024];
            while (bis.read(data) != -1) {
                bos.write(data);
                bos.flush();
            }
            bos.close();
            bis.close();

            System.out.println("done");
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
