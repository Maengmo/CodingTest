package Mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjun1774 {
       static class Edge implements Comparable<Edge> {
        int u, v;
        double w;
        Edge(int u, int v, double w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

    static class UnionFind {
        int[] p;
        UnionFind(int n) {
            p = new int[n+1];
            for (int i = 1; i <= n; i++) p[i] = i;
        }
        int find(int a) {
            if (p[a] == a) return a;
            return p[a] = find(p[a]);
        }
        boolean union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            p[b] = a;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        double[] x = new double[N+1];
        double[] y = new double[N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        UnionFind uf = new UnionFind(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            uf.union(a, b);
        }

        List<Edge> edges = new ArrayList<>((N*(N-1))/2);
        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                double dx = x[i] - x[j];
                double dy = y[i] - y[j];
                double dist = Math.hypot(dx, dy);
                edges.add(new Edge(i, j, dist));
            }
        }

        Collections.sort(edges);

        double total = 0.0;
        // We can keep track of how many unions we've done; when components==1 we can stop.
        // Compute initial components count (optional)
        int components = 0;
        boolean[] seenRoot = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            int r = uf.find(i);
            if (!seenRoot[r]) { seenRoot[r] = true; components++; }
        }

        for (Edge e : edges) {
            if (components == 1) break;
            if (uf.union(e.u, e.v)) {
                total += e.w;
                components--;
            }
        }

        System.out.printf("%.2f\n", total);
    }
}
