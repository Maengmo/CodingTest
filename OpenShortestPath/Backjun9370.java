package OpenShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjun9370 {
    static class Node implements Comparable<Node> {
        int v, w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    static int n, m, t, s, g, h;
    static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            adj = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                int weight = 2 * d;
                if ((a == g && b == h) || (a == h && b == g))
                    weight = 2 * d - 1; // g-h 간선만 홀수

                adj[a].add(new Node(b, weight));
                adj[b].add(new Node(a, weight));
            }

            int[] dist = dijkstra(s);

            List<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int x = Integer.parseInt(br.readLine());
                if (dist[x] % 2 == 1) { // 홀수라면 g-h를 지난 경로
                    candidates.add(x);
                }
            }

            Collections.sort(candidates);
            for (int x : candidates) sb.append(x).append(' ');
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.w > dist[cur.v]) continue;

            for (Node nxt : adj[cur.v]) {
                int nd = cur.w + nxt.w;
                if (nd < dist[nxt.v]) {
                    dist[nxt.v] = nd;
                    pq.add(new Node(nxt.v, nd));
                }
            }
        }
        return dist;
    }
}
