package GraphTraversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Backjun1012 {
    static int M, N, K;
    static int[][] field;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 개수

        while (T-- > 0) {
            M = sc.nextInt();  // 가로 길이
            N = sc.nextInt();  // 세로 길이
            K = sc.nextInt();  // 배추 개수

            field = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                field[y][x] = 1;  // 배추 표시 (주의: y가 행, x가 열)
            }

            int worms = 0; // 지렁이 수

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (field[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        worms++;
                    }
                }
            }

            System.out.println(worms);
        }
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (field[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
