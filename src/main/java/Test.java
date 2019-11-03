import fileclass.*;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        String path = "E:\\testNetworkProgramming\\week1-delete\\MaterialDesign";
        String path2 = "";


        Lesson1 lesson1 = new Lesson1();
        System.out.println(lesson1.delete(path));
        System.out.println(lesson1.deleteExtend(path));

        String pattern = "501";
        Lesson2 lesson2 = new Lesson2();
        System.out.println(lesson2.findFirst(path, pattern));

        Lession3 lession3 = new Lession3();
        lession3.dirTree(path);

        Lesson5 lesson5 = new Lesson5();
        lesson5.findAll(path, "docx", "xlsx", "pdf", "txt");

        Lesson6 lesson6 = new Lesson6();
        lesson6.deleteAll(path, "docx", "xlsx", "pdf", "txt");

        Lesson7 lesson7 = new Lesson7();
        lesson7.copyAll(path, path2, "pdf", "au3");
    }
}
