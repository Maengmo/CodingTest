package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Backjun15678 {
   public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        long[] K = new long[N + 1];

        // Ki 값들은 한 줄에 모두 들어온다!!
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            K[i] = Long.parseLong(st.nextToken());
        }

        long[] dp = new long[N + 1];
        Deque<Integer> dq = new ArrayDeque<>();

        long answer = Long.MIN_VALUE;

        for (int i = 1; i <= N; i++) {

            // remove indices out of range (i-D)
            while (!dq.isEmpty() && dq.peekFirst() < i - D)
                dq.pollFirst();

            long best = 0;
            if (!dq.isEmpty())
                best = dp[dq.peekFirst()];

            dp[i] = K[i] + Math.max(0, best);
            answer = Math.max(answer, dp[i]);

            // maintain deque for max dp
            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i])
                dq.pollLast();
            dq.addLast(i);
        }

        System.out.println(answer);
    }
}
