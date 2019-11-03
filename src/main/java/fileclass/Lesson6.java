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
public class Lesson6 {
    public void deleteAll(String path, String ext1, String ext2, String ext3, String ext4) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not found!");
        } else {
            checkExtensionToDeleteFile(file, ext1, ext2, ext3, ext4);
        }
    }

    private void checkExtensionToDeleteFile(File file, String ext1, String ext2, String ext3, String ext4) {
        if (Arrays.asList(ext1, ext2, ext3, ext4).contains(getExtension(file.getName()))) {
            if(!file.delete()) {
                System.out.println("Can't delete file " + file.getPath());
            }
        }
        if (file.isDirectory()) {
            for (File subFile: Objects.requireNonNull(file.listFiles())) {
                checkExtensionToDeleteFile(subFile, ext1, ext2, ext3, ext4);
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
}
