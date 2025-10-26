package DynamicPlanningAct2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun11066 {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] file = new int[K + 1];
            int[] sum = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                file[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + file[i];
            }

            // 길이 len의 구간 계산
            for (int len = 2; len <= K; len++) {
                for (int i = 1; i + len - 1 <= K; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][k] + dp[k + 1][j] + (sum[j] - sum[i - 1])
                        );
                    }
                }
            }

            sb.append(dp[1][K]).append("\n");
        }

        System.out.print(sb);
    }
}
