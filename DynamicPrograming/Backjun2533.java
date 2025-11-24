package DynamicPrograming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Backjun2533 {
   public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        // parent 배열로 트리 구조(루트:1) 정리 + postorder 리스트 생성
        int root = 1;
        int[] parent = new int[N + 1];
        Arrays.fill(parent, -1);
        parent[root] = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        ArrayList<Integer> order = new ArrayList<>(N); // postorder 저장용

        stack.push(root);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            order.add(u);
            for (int v : graph[u]) {
                if (parent[v] == -1) { // 아직 부모가 설정되지 않았다면 (미방문)
                    parent[v] = u;
                    stack.push(v);
                }
            }
        }

        // order 는 root-first preorder에 가깝게 쌓였으므로,
        // 후위 순회를 위해 뒤에서부터 처리하면 자식->부모 순서가 됩니다.
        int[][] dp = new int[N + 1][2]; // dp[i][0]: i NOT EA, dp[i][1]: i IS EA

        for (int i = order.size() - 1; i >= 0; i--) {
            int u = order.get(i);
            dp[u][1] = 1; // u를 얼리어답터로 선택
            dp[u][0] = 0; // u를 선택하지 않음

            for (int v : graph[u]) {
                if (v == parent[u]) continue; // 부모는 건너뜀 -> 자식만 처리
                dp[u][1] += Math.min(dp[v][0], dp[v][1]); // u 선택 -> 자식은 자유
                dp[u][0] += dp[v][1];                     // u 미선택 -> 자식은 반드시 선택
            }
        }

        int ans = Math.min(dp[root][0], dp[root][1]);
        System.out.println(ans);
    }
}
