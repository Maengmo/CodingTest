package GraphTraversal;

import java.util.*;

public class Backjun2606 {
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 컴퓨터 수
        int m = sc.nextInt(); // 연결 쌍 수

        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 네트워크 연결 입력
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        // BFS 실행
        int infected = bfs(1);

        // 1번 제외해야 하므로 -1
        System.out.println(infected - 1);
    }

    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return count;
    }
}
