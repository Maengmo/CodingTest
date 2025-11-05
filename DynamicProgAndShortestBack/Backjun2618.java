package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun2618 {
     static int N, W;
    static int[][] events;
    static int[][] dp;
    static int[][] choice;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        events = new int[W + 1][2];
        for (int i = 1; i <= W; i++) {
            st = new StringTokenizer(br.readLine());
            events[i][0] = Integer.parseInt(st.nextToken());
            events[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[W + 1][W + 1];
        choice = new int[W + 1][W + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        int answer = solve(0, 0);

        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");

        // 경로 복원
        int i = 0, j = 0;
        for (int k = 1; k <= W; k++) {
            if (choice[i][j] == 1) {
                sb.append("1\n");
                i = k;
            } else {
                sb.append("2\n");
                j = k;
            }
        }

        System.out.print(sb);
    }

    static int solve(int i, int j) {
        if (i == W || j == W) return 0; // 모든 사건 처리 완료
        if (dp[i][j] != -1) return dp[i][j];

        int next = Math.max(i, j) + 1;

        // 경찰차 1이 next 사건 처리
        int dist1 = distance(i, next, 1);
        int cost1 = dist1 + solve(next, j);

        // 경찰차 2가 next 사건 처리
        int dist2 = distance(j, next, 2);
        int cost2 = dist2 + solve(i, next);

        if (cost1 <= cost2) {
            dp[i][j] = cost1;
            choice[i][j] = 1;
        } else {
            dp[i][j] = cost2;
            choice[i][j] = 2;
        }

        return dp[i][j];
    }

    static int distance(int fromIdx, int toIdx, int car) {
        int fromX, fromY;
        if (fromIdx == 0) {
            if (car == 1) {
                fromX = 1;
                fromY = 1;
            } else {
                fromX = N;
                fromY = N;
            }
        } else {
            fromX = events[fromIdx][0];
            fromY = events[fromIdx][1];
        }

        int toX = events[toIdx][0];
        int toY = events[toIdx][1];

        return Math.abs(fromX - toX) + Math.abs(fromY - toY);
    }
}
