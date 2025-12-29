package DynamicPrograming3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun1311 {
     static int N;
    static int[][] cost;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int size = 1 << N;
        dp = new int[size];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask < size; mask++) {
            int person = Integer.bitCount(mask);
            if (person >= N) continue;

            for (int job = 0; job < N; job++) {
                if ((mask & (1 << job)) == 0) {
                    int next = mask | (1 << job);
                    dp[next] = Math.min(dp[next],
                            dp[mask] + cost[person][job]);
                }
            }
        }

        System.out.println(dp[size - 1]);
    }
}
