/**
 * @author wx
 * @create 2020-06-17 9:34
 */
public class Student_Info_Test {
    public static void main(String[] args) {
        Student_Info s1 = new Student_Info("18601119", "王奕雯", "18电子商务1班");
        Student_Info s2 = new Student_Info("18601120", "张三", "18电子商务1班");
        System.out.println(s1);
        System.out.println("----------------");
        System.out.println(s2);
    }
}

class Student_Info {
    private String id; // 学号
    private String name; //姓名
    private String classes; //班级

    public Student_Info(String id, String name, String classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "学号：" + id + '\n' +
                "姓名：" + name + '\n' + "班级：" + classes;
    }
}
