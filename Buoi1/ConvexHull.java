import java.util.*;

public class ConvexHull {
    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            if (this.x != other.x)
                return Integer.compare(this.x, other.x);
            return Integer.compare(this.y, other.y);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Point)) return false;
            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }

        public static long crossProduct(Point O, Point A, Point B) {
            return (long) (A.x - O.x) * (B.y - O.y) - (long) (A.y - O.y) * (B.x - O.x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int n = sc.nextInt();
        List<Point> points = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            points.add(new Point(sc.nextInt(), sc.nextInt()));
        }
        
        Set<Point> set = new HashSet<>(points);
        points = new ArrayList<>(set);
        Collections.sort(points);

        n = points.size();
        if (n <= 2) {
            for (Point p : points)
                System.out.println(p);
            return;
        }
        
        List<Point> hull = new ArrayList<>();

        for (Point p : points) {
            while (hull.size() >= 2 && Point.crossProduct(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0)
                hull.remove(hull.size() - 1);
            hull.add(p);
        }
        
        int t = hull.size() + 1;
        for (int i = n - 2; i >= 0; i--) {
            Point p = points.get(i);
            while (hull.size() >= t && Point.crossProduct(hull.get(hull.size() - 2), hull.get(hull.size() - 1), p) <= 0)
                hull.remove(hull.size() - 1);
            hull.add(p);
        }
        
        hull.remove(hull.size() - 1);
        
        if (hull.size() > 1)
            Collections.reverse(hull.subList(1, hull.size()));

        for (Point p : hull)
            System.out.println(p);
    }
}