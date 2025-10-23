package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backjun1644 {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1️⃣ 에라토스테네스의 체로 소수 구하기
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) primes.add(i);
        }

        // 2️⃣ 투 포인터로 연속된 소수의 합 계산
        int count = 0;
        int start = 0, end = 0, sum = 0;

        while (true) {
            if (sum >= N) {
                if (sum == N) count++;
                sum -= primes.get(start++);
            } else {
                if (end == primes.size()) break;
                sum += primes.get(end++);
            }
        }

        System.out.println(count);
    }
}
