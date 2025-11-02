package DynamicPlanningAct2;

import java.io.*;
import java.util.*;

public class Backjun11062 {
    static int[][] dp;
    static int[] cards;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            cards = new int[N + 1];
            dp = new int[N + 1][N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            for (int len = 1; len <= N; len++) {
                for (int l = 1; l + len - 1 <= N; l++) {
                    int r = l + len - 1;

                    int turn = (N - (r - l + 1)) % 2; // 0이면 근우 차례, 1이면 명우 차례

                    if (l == r) {
                        dp[l][r] = (turn == 0 ? cards[l] : 0);
                        continue;
                    }

                    if (turn == 0) { // 근우 차례 → 최댓값 선택
                        dp[l][r] = Math.max(cards[l] + dp[l + 1][r], cards[r] + dp[l][r - 1]);
                    } else { // 명우 차례 → 근우 점수를 최소화
                        dp[l][r] = Math.min(dp[l + 1][r], dp[l][r - 1]);
                    }
                }
            }

            sb.append(dp[1][N]).append('\n');
        }

        System.out.print(sb);
    }
}
