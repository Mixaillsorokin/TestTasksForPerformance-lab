package task4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task4 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Enter arguments");
            return;
        }

        String fileName = args[0];
        List<Integer> nums = readNumbersFromFile(fileName);

        if (nums.isEmpty()) {
            System.out.println("File NULL or incorrect format ");
            return;
        }

        int minMoves = minMovesToEqual(nums);
        System.out.println("Min : " + minMoves);
    }

    private static List<Integer> readNumbersFromFile(String fileName) {
        List<Integer> nums = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                nums.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return nums;
    }

    private static int minMovesToEqual(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }

        int minMoves = 0;
        int median = findMedian(nums);

        for (int num : nums) {
            minMoves += Math.abs(num - median);
        }

        return minMoves;
    }

    private static int findMedian(List<Integer> nums) {
        int n = nums.size();
        if (n % 2 == 1) {
            return nums.get(n / 2);
        } else {
            int index1 = n / 2 - 1;
            int index2 = n / 2;
            return (nums.get(index1) + nums.get(index2)) / 2;
        }
    }
}
