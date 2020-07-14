import java.util.*;
public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); int num = Integer.valueOf(scanner.nextLine());
        Point[] points = new Point[num];
        List maxPoints = new ArrayList();
        String[] line; for (int i = 0; i < num; i++) {
            line = scanner.nextLine().trim().split(" ");
            points[i] = new Point(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
        }
        Arrays.sort(points);
        maxPoints.add(points[num - 1]);
        int maxY = points[num - 1].y; for (int i = num - 2; i >= 0; i--) { if (points[i].y >= maxY) {
            maxPoints.add(points[i]);
            maxY = points[i].y;
        }
        }
        Collections.reverse(maxPoints);
        maxPoints.forEach(System.out::println);
    }
    static class Point implements Comparable<Point> {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x; this.y = y;
        }
        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.x, o.x);
        }
        @Override
        public String toString() {
            return x + " " + y;
        }


    }
}
