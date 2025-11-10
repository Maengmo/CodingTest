package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Backjun11780 {
     static final int INF = 1_000_000_000;
    static int n, m;
    static int[][] dist;
    static int[][] next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1][n + 1];
        next = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (c < dist[a][b]) {
                dist[a][b] = c;
                next[a][b] = b;
            }
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();

        // 1️⃣ 최소비용 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 2️⃣ 경로 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF || i == j) {
                    sb.append("0\n");
                    continue;
                }
                List<Integer> path = getPath(i, j);
                sb.append(path.size()).append(" ");
                for (int city : path) sb.append(city).append(" ");
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    static List<Integer> getPath(int start, int end) {
        List<Integer> path = new ArrayList<>();
        if (next[start][end] == 0) return path;

        int cur = start;
        path.add(cur);
        while (cur != end) {
            cur = next[cur][end];
            path.add(cur);
        }
        return path;
    }
}
