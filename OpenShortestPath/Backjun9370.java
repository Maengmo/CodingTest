package OpenShortestPath;

import java.io.*;
import java.util.*;

public class Backjun9370 {
   static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        FastScanner(InputStream is) { br = new BufferedReader(new InputStreamReader(is)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }

    static class Edge {
        int to;
        int w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    static class Node implements Comparable<Node> {
        int v;
        long dist;
        Node(int v, long dist) { this.v = v; this.dist = dist; }
        public int compareTo(Node o) { return Long.compare(this.dist, o.dist); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int n = fs.nextInt();
            int m = fs.nextInt();
            int t = fs.nextInt();

            int s = fs.nextInt();
            int g = fs.nextInt();
            int h = fs.nextInt();

            List<Edge>[] adj = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int a = fs.nextInt();
                int b = fs.nextInt();
                int d = fs.nextInt();
                int w = 2 * d;
                if ((a == g && b == h) || (a == h && b == g)) {
                    w = 2 * d - 1; // g-h 간선만 홀수
                }
                adj[a].add(new Edge(b, w));
                adj[b].add(new Edge(a, w));
            }

            // read t candidate destinations
            int[] candidates = new int[t];
            for (int i = 0; i < t; i++) candidates[i] = fs.nextInt();

            long[] dist = dijkstra(n, adj, s);

            List<Integer> ans = new ArrayList<>();
            for (int x : candidates) {
                if (dist[x] != Long.MAX_VALUE && (dist[x] % 2 == 1)) {
                    ans.add(x);
                }
            }
            Collections.sort(ans);
            for (int i = 0; i < ans.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(ans.get(i));
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    static long[] dijkstra(int n, List<Edge>[] adj, int start) {
        long INF = Long.MAX_VALUE;
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0L));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dist != dist[cur.v]) continue;
            for (Edge e : adj[cur.v]) {
                long nd = cur.dist + e.w;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new Node(e.to, nd));
                }
            }
        }
        return dist;
    }
}
