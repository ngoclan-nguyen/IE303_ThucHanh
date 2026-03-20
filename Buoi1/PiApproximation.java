public class PiApproximation {
    public static void main(String[] args) {
        int totalPoints = 1000000;
        int pointsInsideCircle = 0;

        for (int i = 0; i < totalPoints; i++) {
            double x = Math.random() * 2 - 1; 
            double y = Math.random() * 2 - 1; 

            if (x * x + y * y <= 1) {
                pointsInsideCircle++;
            }
        }
        
        double ratio = (double) pointsInsideCircle / totalPoints;
        double estimatePi = ratio * 4;

        System.out.println("Tong so diem da nem: " + totalPoints);
        System.out.println("So diem nam trong hinh tron: " + pointsInsideCircle);
        System.out.println("Uoc luong gia tri cua Pi: " + estimatePi);
        System.out.println("Gia tri Pi chuan: " + Math.PI);
    }
}