package fileclass;

import java.io.File;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 9/21/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession3 {
    public void dirTree(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("Folder not found!");
        } else {
            drawDir(file, 0, "");
        }
    }

    private void drawDir(File file, int count, String blank) {


        String el = blank + "|";
        for (int i = -1; i < count; i++) {
            if (file.isDirectory()) {
                blank += "  ";
            } else {
                blank += "";
            }
        }

        if (file.isDirectory()) {
            el += "+---" + file.getName();
            System.out.println(el);
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                drawDir(subFile, count + 1, blank);
            }
        } else {
            el += "  " + file.getName();
            System.out.println(el);
        }
    }

    public static void main(String[] args) {
        String path = "E:\\testNetworkProgramming\\week1-delete";
        new Lession3().dirTree(path);
    }
}
