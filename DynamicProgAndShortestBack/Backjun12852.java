package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backjun12852 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        if (N == 1) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        int[] dp = new int[N + 1];        // dp[i] = min operations to make i -> 1
        int[] prev = new int[N + 1];      // prev[i] = previous number chosen from i

        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[1] = 0;
        prev[1] = 0;

        for (int i = 2; i <= N; i++) {
            // case: from i -> i-1
            dp[i] = dp[i - 1] + 1;
            prev[i] = i - 1;

            // case: from i -> i/2 (if divisible)
            if (i % 2 == 0) {
                if (dp[i / 2] + 1 < dp[i]) {
                    dp[i] = dp[i / 2] + 1;
                    prev[i] = i / 2;
                }
            }

            // case: from i -> i/3 (if divisible)
            if (i % 3 == 0) {
                if (dp[i / 3] + 1 < dp[i]) {
                    dp[i] = dp[i / 3] + 1;
                    prev[i] = i / 3;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[N]).append('\n');

        // 경로 복원: N -> ... -> 1
        List<Integer> path = new ArrayList<>();
        int cur = N;
        while (cur != 0) {
            path.add(cur);
            cur = prev[cur];
        }

        // 출력은 N 부터 1 이 순서대로여야 하므로 path 그대로 출력
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(path.get(i));
        }
        sb.append('\n');

        System.out.print(sb.toString());
    }
}
