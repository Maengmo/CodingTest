package GraphTraversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Backjun2178 {
    static int N, M;
    static int[][] maze;
    static int[][] dist;
    static boolean[][] visited;
    
    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine(); // 개행 제거

        maze = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];

        // 미로 입력
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        // 도착 지점 출력
        System.out.println(dist[N - 1][M - 1]);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        dist[x][y] = 1; // 시작 칸도 포함

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크 + 이동 가능 여부
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && maze[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        dist[nx][ny] = dist[cx][cy] + 1;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
