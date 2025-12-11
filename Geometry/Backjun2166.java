package Geometry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun2166 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] x = new long[N];
        long[] y = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        long sum = 0;
        for (int i = 0; i < N; i++) {
            int j = (i + 1) % N;
            sum += x[i] * y[j] - x[j] * y[i];
        }

        double area = Math.abs(sum) / 2.0;

        // 소수 첫째 자리까지 출력
        System.out.printf("%.1f\n", area);
    }
}
