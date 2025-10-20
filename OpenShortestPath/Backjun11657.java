package OpenShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Backjun11657 {
    
     static class Edge {
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(A, B, C));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        boolean negativeCycle = false;

        // N-1번 반복
        for (int i = 1; i <= N; i++) {
            for (Edge e : edges) {
                if (dist[e.from] == INF) continue; // 아직 도달하지 못한 노드
                if (dist[e.to] > dist[e.from] + e.cost) {
                    dist[e.to] = dist[e.from] + e.cost;
                    if (i == N) { // N번째 반복에도 갱신됨 = 음수 사이클
                        negativeCycle = true;
                    }
                }
            }
        }

        if (negativeCycle) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= N; i++) {
                if (dist[i] == INF) sb.append(-1).append("\n");
                else sb.append(dist[i]).append("\n");
            }
            System.out.print(sb);
        }
    }

}
