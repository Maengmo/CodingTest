package sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun25682 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 좌상단 W, 좌상단 B 기준 diff 배열
        int[][] diffW = new int[N][M];
        int[][] diffB = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char expectedW = ((i + j) % 2 == 0) ? 'W' : 'B';
                char expectedB = ((i + j) % 2 == 0) ? 'B' : 'W';
                diffW[i][j] = (board[i][j] == expectedW) ? 0 : 1;
                diffB[i][j] = (board[i][j] == expectedB) ? 0 : 1;
            }
        }

        // 누적합 계산
        int[][] psW = new int[N + 1][M + 1];
        int[][] psB = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                psW[i][j] = diffW[i-1][j-1] + psW[i-1][j] + psW[i][j-1] - psW[i-1][j-1];
                psB[i][j] = diffB[i-1][j-1] + psB[i-1][j] + psB[i][j-1] - psB[i-1][j-1];
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i <= N - K; i++) {
            for (int j = 0; j <= M - K; j++) {
                int costW = psW[i + K][j + K] - psW[i][j + K] - psW[i + K][j] + psW[i][j];
                int costB = psB[i + K][j + K] - psB[i][j + K] - psB[i + K][j] + psB[i][j];
                ans = Math.min(ans, Math.min(costW, costB));
            }
        }

        System.out.println(ans);
    }
    
}
