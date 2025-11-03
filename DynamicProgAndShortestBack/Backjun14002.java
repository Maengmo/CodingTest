package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjun14002 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];    // LIS 길이 저장
        int[] prev = new int[N];  // 경로 추적용 (이전 인덱스)
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLen = 1;
        int maxIdx = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        // LIS 경로 복원
        List<Integer> lis = new ArrayList<>();
        for (int i = maxIdx; i != -1; i = prev[i]) {
            lis.add(A[i]);
        }
        Collections.reverse(lis);

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(maxLen).append("\n");
        for (int i = 0; i < lis.size(); i++) {
            sb.append(lis.get(i)).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
