package DynamicPrograming;

import java.io.*;
import java.util.*;

public class Backjun2213 {
    static int n;
    static ArrayList<Integer>[] tree;
    static int[] weight;
    static int[][] dp;
    static boolean[] chosen;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());
        weight = new int[n + 1];
        dp = new int[n + 1][2];
        chosen = new boolean[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        // 루트는 1로 선택 (트리이므로 어떤 노드를 루트로 해도 결과 동일)
        dfs(1, 0);

        // 역추적: 루트의 더 큰 상태부터 시작
        boolean takeRoot = dp[1][1] > dp[1][0];
        reconstruct(1, 0, takeRoot);

        int result = Math.max(dp[1][0], dp[1][1]);
        StringBuilder sb = new StringBuilder();
        sb.append(result).append('\n');

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) if (chosen[i]) list.add(i);
        Collections.sort(list);
        for (int v : list) sb.append(v).append(' ');

        System.out.println(sb.toString().trim());
    }

    // DP 계산: dp[u][1] = u 선택, dp[u][0] = u 미선택
    static void dfs(int u, int parent) {
        dp[u][1] = weight[u];
        dp[u][0] = 0;

        for (int v : tree[u]) {
            if (v == parent) continue;
            dfs(v, u);
            dp[u][1] += dp[v][0]; // u 선택 -> 자식은 선택 불가
            dp[u][0] += Math.max(dp[v][0], dp[v][1]); // u 미선택 -> 자식 자유
        }
    }

    // 역추적하여 chosen 배열 채우기
    static void reconstruct(int u, int parent, boolean take) {
        if (take) chosen[u] = true;
        else chosen[u] = false;

        for (int v : tree[u]) {
            if (v == parent) continue;
            if (take) {
                // 부모가 선택되었으므로 자식은 선택 불가
                reconstruct(v, u, false);
            } else {
                // 부모가 선택되지 않았으므로 자식은 더 큰 상태로 결정
                boolean childTake = dp[v][1] > dp[v][0];
                reconstruct(v, u, childTake);
            }
        }
    }
}
