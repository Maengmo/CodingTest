package DynamicPlanningAct2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun1520 {
     static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 아직 방문하지 않음
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        // 도착 지점이면 경로 1개
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        // 이미 계산된 경우 그대로 반환
        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0; // 초기화

        // 상하좌우 탐색
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

            // 내리막길인 경우만 이동
            if (map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }
}
