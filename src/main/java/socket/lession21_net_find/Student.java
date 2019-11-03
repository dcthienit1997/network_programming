package socket.lession21_net_find;

/**
 * @project network-programming
 * @user DinhChiThien on 10/28/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Student {
    String id;
    String name;
    double score;
    int age;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", age=" + age +
                '}';
    }

    public Student(String id, String name, double score, int age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.age = age;
    }

    public boolean equalName(String subName) {
        return name.contains(subName);
    }

    public boolean isLessAge(int age) {
        return this.age <= age;
    }

    public boolean isLessScore(double score) {
        return this.score <= score;
    }
}
