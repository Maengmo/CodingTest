package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjun1300 {
    
    static int N;
    static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Long.parseLong(br.readLine());

        long left = 1;
        long right = K;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (count(mid) >= K) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    // mid 이하의 수가 몇 개인지 세는 함수
    static long count(long mid) {
        long total = 0;
        for (int i = 1; i <= N; i++) {
            total += Math.min(mid / i, N);
        }
        return total;
    }

}
