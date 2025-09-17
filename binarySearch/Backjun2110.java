package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun2110 {
    
    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int left = 1; // 최소 거리
        int right = houses[N - 1] - houses[0]; // 최대 거리
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canInstall(mid)) { // 설치 가능하다면
                answer = mid;
                left = mid + 1; // 더 크게 시도
            } else {
                right = mid - 1; // 줄여야 함
            }
        }

        System.out.println(answer);
    }

    // 최소 거리 dist 이상으로 공유기를 C개 설치할 수 있는지 체크
    private static boolean canInstall(int dist) {
        int count = 1; // 첫 집에는 무조건 설치
        int last = houses[0];

        for (int i = 1; i < N; i++) {
            if (houses[i] - last >= dist) {
                count++;
                last = houses[i];
            }
        }
        return count >= C;
    }

}
