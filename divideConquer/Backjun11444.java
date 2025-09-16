package divideConquer;

import java.util.Scanner;

public class Backjun11444 {
    
    static final long MOD = 1_000_000_007;

    // 2x2 행렬 곱
    static long[][] multiply(long[][] A, long[][] B) {
        long[][] result = new long[2][2];
        result[0][0] = (A[0][0] * B[0][0] % MOD + A[0][1] * B[1][0] % MOD) % MOD;
        result[0][1] = (A[0][0] * B[0][1] % MOD + A[0][1] * B[1][1] % MOD) % MOD;
        result[1][0] = (A[1][0] * B[0][0] % MOD + A[1][1] * B[1][0] % MOD) % MOD;
        result[1][1] = (A[1][0] * B[0][1] % MOD + A[1][1] * B[1][1] % MOD) % MOD;
        return result;
    }

    // 행렬 거듭제곱 (분할 정복)
    static long[][] power(long[][] M, long exp) {
        if (exp == 1) return M;
        if (exp % 2 == 0) {
            long[][] half = power(M, exp / 2);
            return multiply(half, half);
        } else {
            return multiply(M, power(M, exp - 1));
        }
    }

    static long fibonacci(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        long[][] base = { {1, 1}, {1, 0} };
        long[][] result = power(base, n - 1);  // F1 = 1, F0 = 0 기준
        return result[0][0] % MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        System.out.println(fibonacci(n));
    }

}
