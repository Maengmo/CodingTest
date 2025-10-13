package GraphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun7569 {
    static int M, N, H;
    static int[][][] box;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        Queue<int[]> q = new LinkedList<>();

        // 입력
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        q.add(new int[]{h, n, m}); // 익은 토마토 좌표
                    }
                }
            }
        }

        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int z = cur[0], y = cur[1], x = cur[2];

            for (int dir = 0; dir < 6; dir++) {
                int nz = z + dz[dir];
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || nz < 0 || nz >= H) continue;
                if (box[nz][ny][nx] == 0) {
                    box[nz][ny][nx] = box[z][y][x] + 1;
                    q.add(new int[]{nz, ny, nx});
                }
            }
        }

        // 결과 계산
        int days = 0;
        boolean allRipe = true;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) {
                        allRipe = false;
                        break;
                    }
                    days = Math.max(days, box[h][n][m]);
                }
            }
        }

        if (!allRipe) System.out.println(-1);
        else System.out.println(days - 1);
    }
}
