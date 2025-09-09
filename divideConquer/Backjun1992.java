package divideConquer;

import java.util.Scanner;

public class Backjun1992 {
    
    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        compress(0, 0, N);
        System.out.println(sb.toString());
    }

    // (x, y)부터 size × size 영역을 압축
    static void compress(int x, int y, int size) {
        if (checkSame(x, y, size)) {
            sb.append(map[x][y]); // 모두 같으면 0 또는 1 출력
            return;
        }

        int newSize = size / 2;
        sb.append("(");
        compress(x, y, newSize);                 // 왼쪽 위
        compress(x, y + newSize, newSize);       // 오른쪽 위
        compress(x + newSize, y, newSize);       // 왼쪽 아래
        compress(x + newSize, y + newSize, newSize); // 오른쪽 아래
        sb.append(")");
    }

    // 해당 영역이 모두 같은 값인지 검사
    static boolean checkSame(int x, int y, int size) {
        int first = map[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != first) return false;
            }
        }
        return true;
    }

}
