package io;

import java.io.*;
import java.nio.Buffer;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @project network-programming
 * @user DinhChiThien on 9/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession10 {

    public static void main(String[] args) throws IOException {
        String sFile = "E:\\testNetworkProgramming\\week3\\test1.txt";
        String sFolder = "E:\\testNetworkProgramming\\week3\\files";
        new Lession10().joinFile(sFolder);
        new Lession10().splitFileBySize(sFile, 1024*100);
        new Lession10().splitFileByNumber(sFile, 7);
        new Lession10().joinFile(sFolder);
    }

    void packFile(String sFo) throws IOException {
        File in = new File(sFo);
        if (!in.exists()) {
            return;
        } else {
            OutputStream os = new FileOutputStream(new File("aaaa.zip"));
            ZipOutputStream zos = new ZipOutputStream(os);

            File[] file = in.listFiles();
            for (File file2 : file) {
                InputStream is = new FileInputStream(file2);
                ZipEntry zipEntry = new ZipEntry(file2.getName());
                zos.putNextEntry(zipEntry);
                byte[] data = new byte[1024];
                int len = 0;
                while ((len = is.read(data)) != -1) {
                    zos.write(data, 0, len);
                }
                is.close();
            }
            zos.closeEntry();
            zos.close();
        }
    }

    // join all files in the specific folder to the other file
    // default: knew file type
    private void joinFile(String sFolder) throws IOException {
        File source = new File(sFolder);
        if (!source.exists() || source.isFile()) {
            return;
        } else {
            File destination = new File(sFolder + "\\destination.txt");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination));
            File[] subFiles = Objects.requireNonNull(source.listFiles());
            for (File subFile : subFiles) {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(subFile));
                transfer(bis, bos, subFile.length());
                bis.close();
            }
            bos.close();
        }
    }

    // split the file to multi files, every file same size, except the last one
    private void splitFileBySize(String sFile, long sizeSplit) throws IOException {
        File source = new File(sFile);
        if (!source.exists() || source.isDirectory()) {
            return;
        } else {
            int numberPart = (int) (source.length() / sizeSplit);
            long remainSize = source.length() %  sizeSplit;
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            for (int i = 1; i <= numberPart; i++) {
                File subDestination = new File(setName(source, i));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(subDestination));
                transfer(bis, bos, sizeSplit);
                bos.close();
            }
            if (remainSize > 0) {
                File subDestination = new File(setName(source, numberPart+1));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(subDestination));
                transfer(bis, bos, remainSize);
                bos.close();
            }
            bis.close();
        }
    }

    // split the file to n files
    private void splitFileByNumber(String sFile, int number) throws IOException {
        File source = new File(sFile);
        if (!source.exists() || source.isDirectory()) {
            return;
        } else {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
            long sizePart = source.length() / number;
            long remain = source.length() % number;
            for (int i = 0; i < number; i++) {
                File subDestination = new File(setName(source, i));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(subDestination));
                if (remain > 0) {
                    transfer(bis, bos, remain);
                    remain -= remain;
                }
                transfer(bis, bos, sizePart);
                bos.close();
            }
            bis.close();
        }
    }

    // transfer data function
    private void transfer(BufferedInputStream bis, BufferedOutputStream bos, long sizeSplit) throws IOException {
        byte[] bufferSize = new byte[1024];
        int byteRead, byteMustRead;
        long remain = sizeSplit;
        while (remain > 0) {
            byteMustRead = bufferSize.length < remain ? bufferSize.length : (int) remain;
            byteRead = bis.read(bufferSize, 0, byteMustRead);
            bos.write(bufferSize, 0, byteRead);
            remain -= byteRead;
        }
    }

    // set the new name for the new file
    private String setName(File source, int i) {
        String path = source.getParent();
        String newName = "subFile" + i + "-"+ source.getName();
        return path + "\\" + newName;
    }
}
