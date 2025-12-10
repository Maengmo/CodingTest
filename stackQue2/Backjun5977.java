package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Backjun5977 {
   public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] E = new long[N + 1];
        long total = 0;
        for (int i = 1; i <= N; i++) {
            E[i] = Long.parseLong(br.readLine());
            total += E[i];
        }

        long[] dp = new long[N + 1];
        dp[0] = 0;

        // Deque for sliding window minimum
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(0); // dp[0] = 0

        for (int j = 1; j <= N; j++) {
            // window: [j-(K+1) .. j-1]
            int left = j - (K + 1);
            while (!dq.isEmpty() && dq.peekFirst() < left)
                dq.pollFirst();

            long bestPrev = dp[dq.peekFirst()];
            dp[j] = bestPrev + E[j];

            // maintain increasing dp in deque
            while (!dq.isEmpty() && dp[dq.peekLast()] >= dp[j])
                dq.pollLast();
            dq.addLast(j);
        }

        // find minimal dp[j] for j in [N-K .. N]
        long minSkip = Long.MAX_VALUE;
        for (int j = Math.max(0, N - K); j <= N; j++) {
            minSkip = Math.min(minSkip, dp[j]);
        }

        long answer = total - minSkip;
        System.out.println(answer);
    }
}
