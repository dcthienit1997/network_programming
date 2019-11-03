package fileclass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 9/18/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lesson7 {
    public void copyAll(String sDir, String dDir, String ext1, String ext2) throws IOException {
        File sFile = new File(sDir);
        File dFile = new File(dDir);

        if (!sFile.exists() || !sFile.isDirectory()) {
            System.out.println("Source folder not found!");
        } else if (dFile.exists() && !dFile.isDirectory()) {
            System.out.println("Destination path not folder!");
        } else {
            checkExtensionToCopyFile(sFile, dFile, ext1, ext2);
        }

    }

    private void checkExtensionToCopyFile(File sFile, File dFile, String ext1, String ext2) throws IOException {
        if (sFile.isDirectory()) {
            if (!dFile.exists()) {
                if (!dFile.mkdirs()) {
                    return;
                }
            }
            for (File subSourceFile : Objects.requireNonNull(sFile.listFiles())) {
                File subDestinationFile = new File(dFile, subSourceFile.getName());
                checkExtensionToCopyFile(subSourceFile, subDestinationFile, ext1, ext2);
            }
        } else {
            if (Arrays.asList(ext1, ext2).contains(getExtension(sFile.getName(), ""))) {
                Files.copy(sFile.toPath(), dFile.toPath());
            }
        }
    }

    private String getExtension(String nameFile, String extension) {
        int i;
        if ((i = nameFile.lastIndexOf(".")) != -1) {
            extension = nameFile.substring(i + 1);
        }
        return extension;
    }

    public static void main(String[] args) throws IOException {

        String sDir = "E:\\testNetworkProgramming\\week1-delete\\MaterialDesign";
        String dDir = "E:\\testNetworkProgramming\\week1-delete\\dFolder\\MaterialDesign";

        new Lesson7().copyAll(sDir, dDir, "au3", "pdf");

    }

}

