package Lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] ns = new int[]{100, 500, 1_000, 5_000, 10_000, 50_000, 100_000};
        for (int n : ns) {
            System.out.println("Граф с количеством вершин: " + n);
            performanceTest(n);
            System.out.println();
        }
//        System.out.println(correctTest());
    }

    private static List<Integer>[] graphGenerate(int n) {
        Random random = new Random();
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int u = random.nextInt(i + 1);
            for (int j = 0; j < 100; j++) {
                int v = random.nextInt(n - i) + i;
                g[u].add(v);
            }
        }
        return g;
    }

    private static void performanceTest(int n) {
        List<Integer>[] g = graphGenerate(n);
        TopologicalSorting ts = new TopologicalSorting(g);

        int avg_n = 100;
        long[] times = new long[avg_n];
        double[] mems = new double[avg_n];
        Runtime runtime = Runtime.getRuntime();
        for (int k = 0; k < avg_n; k++) {
            long time_start = System.currentTimeMillis();
            ts.topological_sort();
            long time_end = System.currentTimeMillis();
            double mem = (runtime.totalMemory() - runtime.freeMemory()) / 1048576.;
            times[k] = time_end - time_start;
            mems[k] = mem;
        }

        double time_avg = Arrays.stream(times).average().orElse(Double.NaN);
        double mem_avg = Arrays.stream(mems).average().orElse(Double.NaN);

        System.out.println("Время: " + time_avg);
        System.out.println("Память: " + mem_avg);
    }

    private static boolean correctTest() {
        int n = 5;
        TopologicalSorting ts = new TopologicalSorting(n);
        List<Integer>[] g = ts.g;
        g[0].addAll(Arrays.asList(1, 3));
        g[1].addAll(Arrays.asList(2, 3, 4));
        g[2].addAll(Arrays.asList(4));
//        g[3].addAll(Collections.emptyList());
        g[4].addAll(Arrays.asList(3));

        List<Integer> ans = ts.topological_sort();
        System.out.println(ans);
        return ans.equals(Arrays.asList(0, 1, 2, 4, 3));
    }
}
