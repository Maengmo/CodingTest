package GraphTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun7576 {
    
     static int M, N;
    static int[][] box;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];

        Queue<int[]> q = new LinkedList<>();

        // 입력 및 익은 토마토 초기 큐 삽입
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        // BFS 실행
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 내 && 익지 않은 토마토
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && box[nx][ny] == 0) {
                    box[nx][ny] = box[x][y] + 1; // 날짜 갱신
                    q.add(new int[]{nx, ny});
                }
            }
        }

        int days = 0;
        boolean allRipe = true;

        // 결과 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    allRipe = false;
                    break;
                }
                days = Math.max(days, box[i][j]);
            }
        }

        // 출력
        if (!allRipe) System.out.println(-1);
        else System.out.println(days - 1);
    }

}
