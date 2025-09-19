package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjun12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> lis = new ArrayList<>();
        lis.add(A[0]);

        for (int i = 1; i < N; i++) {
            int num = A[i];
            if (num > lis.get(lis.size() - 1)) {
                lis.add(num);
            } else {
                int idx = Collections.binarySearch(lis, num);
                if (idx < 0) idx = -(idx + 1); // 삽입 위치
                lis.set(idx, num);
            }
        }

        System.out.println(lis.size());
    }
}
