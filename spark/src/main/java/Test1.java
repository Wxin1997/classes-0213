/**
 * @author wx
 * @create 2020-06-17 10:02
 */
public class Test1 {
    public static void main(String[] args) {
        String s = "123. 45. 678. 123. 891. 123";
        int[] arr = new int[s.length()];
        String index = "";//
        int num = 0;
        while (s.length() != 0) {

            int i = s.indexOf("123");
            s = s.substring(i + 3, s.length());
            index += " No." + i;
            num++;
        }
        System.out.println(index + " have 123");
        System.out.println("Total Num = " + num);
    }
}
