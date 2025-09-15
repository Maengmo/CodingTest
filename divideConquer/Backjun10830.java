package divideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun10830 {
    
    static int N;
    static final int MOD = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken()); // 거듭제곱 지수

        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] result = matrixPower(A, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j] % MOD).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    // 행렬 거듭제곱
    static int[][] matrixPower(int[][] A, long exp) {
        if (exp == 1) {
            return A;
        }

        int[][] half = matrixPower(A, exp / 2);
        int[][] result = multiply(half, half);

        if (exp % 2 == 1) {
            result = multiply(result, A);
        }

        return result;
    }

    // 행렬 곱셈
    static int[][] multiply(int[][] A, int[][] B) {
        int[][] C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (A[i][k] * B[k][j]) % MOD;
                }
                C[i][j] = sum % MOD;
            }
        }
        return C;
    }

}
