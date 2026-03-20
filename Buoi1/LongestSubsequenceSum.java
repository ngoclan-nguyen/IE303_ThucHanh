import java.util.*;

public class LongestSubsequenceSum {
    static List<Integer> numbers = new ArrayList<>();
    static List<Integer> subsequence = new ArrayList<>();
    static List<Integer> bestSubsequence = new ArrayList<>();

    static void findMaxSubsequence(Integer currentSum, Integer currentIndex) {
        if (currentSum == 0) {
            if (subsequence.size() > bestSubsequence.size()) {
                bestSubsequence = new ArrayList<>(subsequence);
            }
            return;
        }

        for (int idx = currentIndex; idx < numbers.size(); idx++) {
            Integer number = numbers.get(idx);
            
            if (currentSum - number < 0) {
                continue;
            }
            
            subsequence.add(number);
            findMaxSubsequence(currentSum - number, idx + 1);
            subsequence.remove(subsequence.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("[,\\s]+");

        System.out.println("Input:");

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int k = sc.nextInt();

        for (int i = 0; i < n; i++) {
            numbers.add(sc.nextInt());
        }
        
        findMaxSubsequence(k, 0);

        System.out.println("Output:");

        for (int i = 0; i < bestSubsequence.size(); i++) {
            System.out.print(bestSubsequence.get(i));
            if (i < bestSubsequence.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}