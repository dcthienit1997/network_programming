package io;

import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @project network-programming
 * @user DinhChiThien on 9/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession14 {
    public static void main(String[] args) throws IOException {
        String sFile = "E:\\testNetworkProgramming\\week3\\files\\destination.txt";
        String sFolder = "C:\\Users\\thuho\\Desktop\\DinhChiThien_15130173_io2";
        String sZipFile = "E:\\testNetworkProgramming\\week3\\files.zip";
//        new Lession14().packSingleFile(sFile);
        new Lession14().packFolder(sFolder);
//        new Lession14().unpackFolder(sZipFile);
    }

    private void packSingleFile(String sFile) throws IOException {
        File source = new File(sFile);
        if(!source.exists()) {
            return;
        } else {
            InputStream is = new FileInputStream(source);
            OutputStream os = new FileOutputStream(source.getAbsoluteFile() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(os);
            // add a new Zip entry to the ZipOutputStream
            ZipEntry ze = new ZipEntry(source.getName());
            zos.putNextEntry(ze);

            byte[] data = new byte[10];
            int length;
            while ((length = is.read(data)) != -1) {
                zos.write(data, 0, length);
            }

            // close the zip entry
            zos.closeEntry();
            // close resources
            zos.close();
            os.close();
            is.close();
        }
    }

    private void packFolder(String sFolder) throws IOException {
        File source = new File(sFolder);
        if (!source.exists() || source.isFile()) {
            return;
        } else {
            OutputStream os = new FileOutputStream(source.getAbsolutePath() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(os);
            File[] files = source.listFiles();
            byte[] data = new byte[1024];
            int length;
            for (File subFile: Objects.requireNonNull(files)) {
                InputStream is = new FileInputStream(subFile);
                ZipEntry ze = new ZipEntry(subFile.getName());
                zos.putNextEntry(ze);
                while ((length = is.read(data)) != -1) {
                    zos.write(data, 0, length);
                    zos.flush();
                }
                zos.closeEntry();
                is.close();
            }
            zos.close();
            os.close();
        }
    }

    private void unpackFolder(String sFile) throws IOException {
        File source = new File(sFile);
        if (!source.exists()) {
            return;
        } else {
            File dirDest = new File(source.getParent());
            ZipInputStream zis = new ZipInputStream(new FileInputStream(source));
            ZipEntry ze = zis.getNextEntry();
            byte[] data = new byte[1024];
            int length;
            while (ze != null) {
                File subFile = new File(dirDest, ze.getName());
                FileOutputStream fos = new FileOutputStream(subFile);
                while ((length = zis.read(data)) != -1) {
                    fos.write(data, 0 , length);
                }
                fos.flush();
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        }
    }

}
