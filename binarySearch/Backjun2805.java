package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun2805 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long[] trees = new long[N];
        st = new StringTokenizer(br.readLine());
        long max = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            if (trees[i] > max) max = trees[i];
        }

        long start = 0, end = max, answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long total = 0;
            for (long tree : trees) {
                if (tree > mid) total += tree - mid;
            }

            if (total >= M) {  // 충분히 가져감
                answer = mid;
                start = mid + 1;
            } else {           // 부족함
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

}
