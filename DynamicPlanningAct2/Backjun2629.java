package DynamicPlanningAct2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun2629 {
     static boolean[][] dp;
    static int[] weights;
    static int N;
    static final int MAX = 15000; // 절대값 최대 범위 (500 * 30)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        dp = new boolean[N + 1][MAX + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] marbles = new int[M];
        for (int i = 0; i < M; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int marble : marbles) {
            if (marble > MAX) sb.append("N ");
            else sb.append(dp[N][marble] ? "Y " : "N ");
        }

        System.out.println(sb.toString().trim());
    }

    static void dfs(int idx, int weight) {
        if (idx > N || dp[idx][weight]) return;

        dp[idx][weight] = true;

        if (idx == N) return;

        // 현재 추 사용 X
        dfs(idx + 1, weight);

        // 왼쪽에 추 올림
        dfs(idx + 1, weight + weights[idx + 1]);

        // 오른쪽에 추 올림 (절대값)
        dfs(idx + 1, Math.abs(weight - weights[idx + 1]));
    }
}
