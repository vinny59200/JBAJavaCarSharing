import java.util.*;
import java.util.stream.*;

public class Main {

    /**
     * Counts the number of unique words ignoring case sensitivity
     * for given lines with words.
     *
     * @param n     the n lines contain words
     * @param lines the list of lines, each line is a list of words
     * @return the number of unique words in lines ignoring case sensitivity
     */
    public static long count(int n, List<List<String>> lines) {
        // write your code here
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<List<String>> lines = Stream.generate(scanner::nextLine).limit(n)
                .map(s -> Arrays.asList(s.split("\\s+")))
                .collect(Collectors.toList());

        System.out.println(count(n, lines));
    }
}