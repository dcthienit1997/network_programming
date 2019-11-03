package fileclass;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 9/18/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lesson5 {

    public void findAll(String path, String ext1, String ext2, String ext3, String ext4) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not found!");
        } else {
            checkExtensionFile(file, ext1, ext2, ext3, ext4);
        }
    }

    private void checkExtensionFile(File file, String ext1, String ext2, String ext3, String ext4) {

        if (Arrays.asList(ext1, ext2, ext3, ext4).contains(getExtension(file.getName()))) {
            System.out.println(file.getAbsolutePath());
        }
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                checkExtensionFile(subFile, ext1, ext2, ext3, ext4);
            }
        }

    }

    private String getExtension(String fileName) {
        String extension = "";
        int i;
        if ((i = fileName.lastIndexOf(".")) != -1) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public static void main(String[] args) {
        System.out.println("RUN 5");
    }
}
