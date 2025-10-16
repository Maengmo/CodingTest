package OpenShortestPath;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int vertex;
    int weight;
    Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Backjun1753 {
    static final int INF = Integer.MAX_VALUE;
    static List<List<Node>> graph = new ArrayList<>();
    static int[] dist;
    static int V, E, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int current = now.vertex;
            int weight = now.weight;

            if (dist[current] < weight) continue;

            for (Node next : graph.get(current)) {
                int cost = dist[current] + next.weight;
                if (cost < dist[next.vertex]) {
                    dist[next.vertex] = cost;
                    pq.offer(new Node(next.vertex, cost));
                }
            }
        }
    }
}