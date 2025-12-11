package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Backjun33918 {
     static final long NEG = -(1L<<60);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] b = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) b[i] = Integer.parseInt(st.nextToken());

        // 그룹별 온도 리스트 (같은 residue)
        ArrayList<int[]> tempsByR = new ArrayList<>();
        for (int r = 0; r < C; r++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int k = 1 + r; k <= M; k += C) tmp.add(k);
            int[] arr = new int[tmp.size()];
            for (int i = 0; i < tmp.size(); i++) arr[i] = tmp.get(i);
            tempsByR.add(arr);
        }

        // dp for each residue group
        long[][] dp = new long[C][];
        long[][] next = new long[C][];
        for (int r = 0; r < C; r++) {
            int len = tempsByR.get(r).length;
            dp[r] = new long[len];
            next[r] = new long[len];
        }

        // time 0 초기화
        for (int r = 0; r < C; r++) {
            int[] temps = tempsByR.get(r);
            for (int i = 0; i < temps.length; i++) {
                dp[r][i] = (long) M - Math.abs(b[0] - temps[i]);
            }
        }

        int maxStep = D / C;

        for (int t = 1; t < N; t++) {
            for (int r = 0; r < C; r++) {
                int[] temps = tempsByR.get(r);
                int len = temps.length;
                long[] cur = dp[r];
                long[] nxt = next[r];
                Arrays.fill(nxt, NEG);

                // 슬라이딩 윈도우: 각 i에 대해 j 범위는 [max(0,i-maxStep) .. min(len-1, i+maxStep)]
                Deque<Integer> dq = new ArrayDeque<>();
                int rptr = -1;

                for (int i = 0; i < len; i++) {
                    int L = Math.max(0, i - maxStep);
                    int R = Math.min(len - 1, i + maxStep);

                    // 확장: rptr를 R까지 올리며 deque에 추가
                    while (rptr < R) {
                        rptr++;
                        while (!dq.isEmpty() && cur[dq.peekLast()] <= cur[rptr]) dq.pollLast();
                        dq.addLast(rptr);
                    }

                    // 왼쪽이 범위 밖이면 제거
                    while (!dq.isEmpty() && dq.peekFirst() < L) dq.pollFirst();

                    if (!dq.isEmpty()) {
                        long best = cur[dq.peekFirst()];
                        nxt[i] = best + (M - Math.abs(temps[i] - b[t]));
                    } else {
                        // 범위가 비어있을 순 없지만 안전장치
                        nxt[i] = NEG;
                    }
                }

                // swap
                dp[r] = nxt;
                next[r] = cur; // reuse array
            }
        }

        long ans = 0;
        for (int r = 0; r < C; r++) {
            for (long v : dp[r]) if (v > ans) ans = v;
        }
        System.out.println(ans);
    }
}
