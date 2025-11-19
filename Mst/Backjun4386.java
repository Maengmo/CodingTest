package Mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun4386 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        double[] x = new double[n];
        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Double.parseDouble(st.nextToken());
            y[i] = Double.parseDouble(st.nextToken());
        }

        // Prim's algorithm
        boolean[] visited = new boolean[n];
        double[] minEdge = new double[n];
        final double INF = 1e18;
        for (int i = 0; i < n; i++) minEdge[i] = INF;

        // 시작 정점 0
        minEdge[0] = 0.0;
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            double min = INF;
            // 방문하지 않은 정점 중 최소 간선 선택
            for (int v = 0; v < n; v++) {
                if (!visited[v] && minEdge[v] < min) {
                    min = minEdge[v];
                    u = v;
                }
            }

            if (u == -1) break; // 연결되지 않은 경우(문제 조건상 연결 보장)
            visited[u] = true;
            result += min; // 첫 선택은 min==0 이므로 영향 없음

            // 선택된 정점 u를 통해 다른 정점의 최소 간선 갱신
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    double dx = x[u] - x[v];
                    double dy = y[u] - y[v];
                    double dist = Math.sqrt(dx*dx + dy*dy);
                    if (dist < minEdge[v]) minEdge[v] = dist;
                }
            }
        }

        System.out.printf("%.2f\n", result);
    }
}
