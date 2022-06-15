import java.util.stream.*;

class QuadraticSum {
    public static long rangeQuadraticSum(int fromIncl, int toExcl) {
        return Stream.iterate(fromIncl, i -> i + 1)
            .limit(toExcl - fromIncl)
            .mapToLong(i -> i * i)
            .reduce(0L, Long::sum);
    }
}