package divideConquer;

import java.util.Scanner;

public class Backjun1780 {
    
    static int N;
    static int[][] paper;
    static int[] count = new int[3]; // count[0]: -1, count[1]: 0, count[2]: 1

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                paper[i][j] = sc.nextInt();
            }
        }

        divide(0, 0, N);

        System.out.println(count[0]); // -1
        System.out.println(count[1]); // 0
        System.out.println(count[2]); // 1
    }

    static void divide(int x, int y, int size) {
        if (checkSame(x, y, size)) {
            count[paper[x][y] + 1]++; // -1→0번 인덱스, 0→1번, 1→2번
            return;
        }

        int newSize = size / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divide(x + i * newSize, y + j * newSize, newSize);
            }
        }
    }

    static boolean checkSame(int x, int y, int size) {
        int first = paper[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (paper[i][j] != first) return false;
            }
        }
        return true;
    }
}
