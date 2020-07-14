/**
 * @author wx
 * @create 2020-06-17 9:09
 */
class EmpInfo {
    String name;
    String designation;
    String department;

    public EmpInfo(String eName, String eDesign, String eDept) {
        name = eName;
        designation = eDesign;
        department = eDept;
    }

    void print() {
        System.out.println(name + " is a " + designation + " at " + department + ".\n");
    }
}

public class ClassAndObject {

    public static void main(String args[]) {
        EmpInfo[] emp = new EmpInfo[2];
        emp[0] = new EmpInfo("Robert Java", "Manager", "Coffee shop");
        emp[1] = new EmpInfo("Tom Java", "Worker", "Coffee shop");

        for (int i = 0; i < emp.length; i++) {
            emp[i].print();
        }

    }
}