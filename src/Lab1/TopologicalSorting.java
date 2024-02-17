package Lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSorting {
    int n;
    List<Integer>[] g;
    boolean[] used;
    ArrayList<Integer> ans;

    public TopologicalSorting(int n) {
        this.n = n;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<>();
        used = new boolean[n];
        ans = new ArrayList<>();
    }

    public TopologicalSorting(List<Integer>[] graph) {
        g = graph;
        this.n = g.length;
        used = new boolean[this.n];
        ans = new ArrayList<>();
    }

    void dfs(int v) {
        used[v] = true;
        for (int i = 0; i < g[v].size(); ++i) {
            int to = g[v].get(i);
            if (!used[to])
                dfs(to);
        }
        ans.add(v);
    }

    List<Integer> topological_sort() {
        for (int i = 0; i < n; ++i)
            if (!used[i])
                dfs(i);
        Collections.reverse((ans));
        return ans;
    }
}

