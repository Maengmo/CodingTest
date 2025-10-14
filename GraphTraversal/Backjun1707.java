package GraphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun1707 {
     static int V, E;
    static ArrayList<Integer>[] graph;
    static int[] color; // 0: 미방문, 1: 빨강, -1: 파랑
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            // 그래프 초기화
            graph = new ArrayList[V + 1];
            color = new int[V + 1];
            isBipartite = true;

            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }

            // 간선 입력
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            // 모든 정점에 대해 BFS (그래프가 비연결일 수도 있음)
            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    if (!bfs(i)) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            sb.append(isBipartite ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }

    static boolean bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        color[start] = 1; // 시작 노드는 1번 색으로

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph[cur]) {
                // 인접 노드가 아직 색칠되지 않았다면
                if (color[next] == 0) {
                    color[next] = -color[cur]; // 반대 색으로 칠함
                    q.offer(next);
                }
                // 이미 색칠되어 있고, 같은 색이면 이분 그래프 아님
                else if (color[next] == color[cur]) {
                    return false;
                }
            }
        }
        return true;
    }
}
