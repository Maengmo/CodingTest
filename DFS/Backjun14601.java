package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun14601 {
      static int N;
    static int[][] board;
    static int tileNum = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        N = 1 << K;

        board = new int[N][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int hx = Integer.parseInt(st.nextToken());
        int hy = Integer.parseInt(st.nextToken());

        // 좌표 변환: (1,1) = bottom-left
        // 내부 배열은 (0,0) = top-left 이므로 변환 필요
        int holeR = N - hy;
        int holeC = hx - 1;

        board[holeR][holeC] = -1;

        tile(0, 0, holeR, holeC, N);

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(board[r][c]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    static void tile(int r, int c, int hr, int hc, int size) {
        if (size == 1) return;

        int t = tileNum++;

        int half = size / 2;

        // 4 cuadrants
        int midR = r + half;
        int midC = c + half;

        // hole determines which quadrant already has missing tile
        // 1) top-left
        if (hr < midR && hc < midC) {
            // do nothing
        } else {
            board[midR - 1][midC - 1] = t;
        }

        // 2) top-right
        if (hr < midR && hc >= midC) {
            // nothing
        } else {
            board[midR - 1][midC] = t;
        }

        // 3) bottom-left
        if (hr >= midR && hc < midC) {
            // nothing
        } else {
            board[midR][midC - 1] = t;
        }

        // 4) bottom-right
        if (hr >= midR && hc >= midC) {
            // nothing
        } else {
            board[midR][midC] = t;
        }

        // recursively tile 4 regions

        // 1) top-left
        if (hr < midR && hc < midC)
            tile(r, c, hr, hc, half);
        else
            tile(r, c, midR - 1, midC - 1, half);

        // 2) top-right
        if (hr < midR && hc >= midC)
            tile(r, midC, hr, hc, half);
        else
            tile(r, midC, midR - 1, midC, half);

        // 3) bottom-left
        if (hr >= midR && hc < midC)
            tile(midR, c, hr, hc, half);
        else
            tile(midR, c, midR, midC - 1, half);

        // 4) bottom-right
        if (hr >= midR && hc >= midC)
            tile(midR, midC, hr, hc, half);
        else
            tile(midR, midC, midR, midC, half);
    }
}
