import java.util.Scanner; 

public class CircleArea {
    public static double approximateAreaMonteCarlo(double r) {
        int totalPoints = 1000000;
        int pointInsideCircle = 0;
        for (int i = 0; i < totalPoints; i++) {
            double x = Math.random() * 2 * r - r;
            double y = Math.random() * 2 * r - r;

            if ((x * x + y * y) <= (r * r)) {
                pointInsideCircle++;
            }
        }
        double ratio = (double) pointInsideCircle / totalPoints;
        double squareArea = 4 * r * r;
        return ratio * squareArea;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ban kinh r: ");
        
        if (sc.hasNextDouble()) {
            double r = sc.nextDouble(); 
            if (r > 0) {
                System.out.println("Dien tich xap xi hinh tron la: " + approximateAreaMonteCarlo(r));
            } else {
                System.out.println("Loi: Ban kinh hinh tron phai la mot so lon hon 0!");
            }         
        } else {
            System.out.println("Loi: Vui long nhap mot so thuc hop le!");
        }
        
        sc.close();
    }
}