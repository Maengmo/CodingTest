package GraphTraversal;

import java.io.*;
import java.util.*;

public class Backjun7562 {
    static final int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static final int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int l = Integer.parseInt(br.readLine().trim()); // 체스판 한 변 길이
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int tx = Integer.parseInt(st.nextToken());
            int ty = Integer.parseInt(st.nextToken());

            sb.append(bfs(l, sx, sy, tx, ty)).append('\n');
        }

        System.out.print(sb.toString());
    }

    static int bfs(int l, int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return 0;

        boolean[][] visited = new boolean[l][l];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= l || ny < 0 || ny >= l) continue;
                if (visited[nx][ny]) continue;

                if (nx == tx && ny == ty) return d + 1;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, d + 1});
            }
        }
        return -1;
    }
}
