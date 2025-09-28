package GraphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Backjun24480 {
    
    static ArrayList<Integer>[] graph;
    static int[] visited;
    static int order = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 수
        int M = Integer.parseInt(st.nextToken()); // 간선 수
        int R = Integer.parseInt(st.nextToken()); // 시작 정점

        graph = new ArrayList[N + 1];
        visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        // 내림차순 정렬
        for (int i = 1; i <= N; i++) {
            graph[i].sort(Collections.reverseOrder());
        }

        // DFS 시작
        dfs(R);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int node) {
        visited[node] = order++; // 방문 순서 기록

        for (int next : graph[node]) {
            if (visited[next] == 0) {
                dfs(next);
            }
        }
    }

}
