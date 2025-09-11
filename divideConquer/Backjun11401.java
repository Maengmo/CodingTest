package divideConquer;

import java.util.Scanner;

public class Backjun11401 {
    
    static final int MOD = 1_000_000_007;
    static long[] fact, invFact;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        fact = new long[N + 1];
        invFact = new long[N + 1];

        // 1. 팩토리얼 미리 계산
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        // 2. 역팩토리얼 계산
        invFact[N] = pow(fact[N], MOD - 2);  // 페르마 소정리
        for (int i = N - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        // 3. 이항계수 계산
        long result = fact[N] * invFact[K] % MOD * invFact[N - K] % MOD;
        System.out.println(result);
    }

    // 분할정복 거듭제곱
    static long pow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

}
