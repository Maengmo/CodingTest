package Mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun6497 {
    static class Edge implements Comparable<Edge> {
        int u, v;
        int w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static class UnionFind {
        int[] p;
        int[] rank;
        UnionFind(int n) {
            p = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
        }
        int find(int x) {
            if (p[x] == x) return x;
            return p[x] = find(p[x]);
        }
        boolean union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            if (rank[a] < rank[b]) {
                p[a] = b;
            } else if (rank[a] > rank[b]) {
                p[b] = a;
            } else {
                p[b] = a;
                rank[a]++;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            StringTokenizer st = new StringTokenizer(line);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) break;

            Edge[] edges = new Edge[n];
            long total = 0L;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(x, y, z);
                total += z;
            }

            Arrays.sort(edges);

            UnionFind uf = new UnionFind(m);
            long mst = 0L;
            int used = 0;
            for (int i = 0; i < n; i++) {
                if (uf.union(edges[i].u, edges[i].v)) {
                    mst += edges[i].w;
                    used++;
                    if (used == m - 1) break;
                }
            }

            sb.append(total - mst).append('\n');
        }
        System.out.print(sb.toString());
    }
}
