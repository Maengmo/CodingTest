package GraphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun2206 {
     static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Node {
        int y, x, wall, dist;
        Node(int y, int x, int wall, int dist) {
            this.y = y;
            this.x = x;
            this.wall = wall; // 0: 안 부숨, 1: 부숨
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2]; // 0: 안부숨, 1: 부숨

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 도착점 도달
            if (cur.y == N - 1 && cur.x == M - 1) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

                // 이동할 수 있는 곳(0)
                if (map[ny][nx] == 0 && !visited[ny][nx][cur.wall]) {
                    visited[ny][nx][cur.wall] = true;
                    q.offer(new Node(ny, nx, cur.wall, cur.dist + 1));
                }

                // 벽(1)이고 아직 안 부쉈으면
                else if (map[ny][nx] == 1 && cur.wall == 0 && !visited[ny][nx][1]) {
                    visited[ny][nx][1] = true;
                    q.offer(new Node(ny, nx, 1, cur.dist + 1));
                }
            }
        }

        // 도달 불가
        return -1;
    }
}
