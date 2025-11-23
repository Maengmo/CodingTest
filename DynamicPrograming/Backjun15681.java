package DynamicPrograming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjun15681 {
    
    static ArrayList<Integer>[] tree;
    static int[] subtreeSize;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        subtreeSize = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 트리 입력 (무방향)
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        // DFS로 서브트리 크기 계산
        dfs(R);

        StringBuilder sb = new StringBuilder();

        // 쿼리 처리
        for (int i = 0; i < Q; i++) {
            int u = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[u]).append("\n");
        }

        System.out.print(sb);
    }

    // DFS로 서브트리 크기 계산
    static int dfs(int node) {
        visited[node] = true;
        int count = 1; // 자기 자신 포함

        for (int next : tree[node]) {
            if (!visited[next]) {
                count += dfs(next);
            }
        }

        subtreeSize[node] = count;
        return count;
    }
}
