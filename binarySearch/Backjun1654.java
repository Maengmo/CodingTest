package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun1654 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] cables = new long[K];
        long max = 0;

        for (int i = 0; i < K; i++) {
            cables[i] = Long.parseLong(br.readLine());
            if (cables[i] > max) {
                max = cables[i];
            }
        }

        long left = 1;       // 자를 수 있는 최소 길이
        long right = max;    // 자를 수 있는 최대 길이
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0;
            for (long cable : cables) {
                count += cable / mid;
            }

            if (count >= N) {
                answer = mid;   // mid 길이로도 가능 → 일단 저장
                left = mid + 1; // 더 긴 길이 도전
            } else {
                right = mid - 1; // 너무 길어서 부족 → 줄이기
            }
        }

        System.out.println(answer);
    }

}
