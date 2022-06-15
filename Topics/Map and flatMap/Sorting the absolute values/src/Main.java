import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    /**
     * Returns the sorted array of absolute numbers in ascending order.
     *
     * @param numbers the input array of String integer numbers
     * @return the sorted array of integer absolute numbers
     */
    public static int[] sortedAbsNumbers(String[] numbers) {
        // write your code here
        int[] absNumbers = Arrays.stream(numbers)
                .map(Integer::parseInt)
                .mapToInt(Math::abs)
                .sorted()
                .toArray();
        return absNumbers;
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Arrays.stream(sortedAbsNumbers(scanner.nextLine().split("\\s+")))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "))
        );
    }
}