package DynamicPlanningAct2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun11049 {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 행렬 개수
        
        int[] r = new int[N + 1];
        int[] c = new int[N + 1];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i][j] = i번째부터 j번째 행렬까지 곱하는 최소 연산 수
        int[][] dp = new int[N][N];
        
        // 행렬의 개수 차이 (chain length)
        for (int len = 1; len < N; len++) {
            for (int i = 0; i + len < N; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + r[i] * c[k] * c[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        System.out.println(dp[0][N - 1]);
    }
}
