package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjun11779 {
     static class Node implements Comparable<Node> {
        int city, cost;

        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int n, m;
    static List<Node>[] graph;
    static int[] dist;
    static int[] prev;

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.city]) continue;

            for (Node next : graph[cur.city]) {
                int newCost = cur.cost + next.cost;
                if (newCost < dist[next.city]) {
                    dist[next.city] = newCost;
                    prev[next.city] = cur.city;
                    pq.offer(new Node(next.city, newCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        prev = new int[n + 1];

        dijkstra(start);

        // 🔹 경로 복원
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != 0; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        // ✅ 출력
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append('\n');              // 최소 비용
        sb.append(path.size()).append('\n');            // 경로 길이
        for (int city : path) sb.append(city).append(' ');

        System.out.println(sb);
    }
}
