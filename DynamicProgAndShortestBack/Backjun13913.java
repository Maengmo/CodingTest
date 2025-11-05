package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun13913 {
     static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 방문 시간(거리)
        int[] time = new int[MAX + 1];
        // 이전 위치 기록 (경로 복원용)
        int[] prev = new int[MAX + 1];
        Arrays.fill(time, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        time[N] = 0;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == K) break; // 목표 도착 시 중단

            int[] nexts = {cur - 1, cur + 1, cur * 2};
            for (int next : nexts) {
                if (next < 0 || next > MAX) continue;
                if (time[next] != -1) continue; // 이미 방문
                time[next] = time[cur] + 1;
                prev[next] = cur;
                q.offer(next);
            }
        }

        // 출력
        System.out.println(time[K]);

        // 경로 복원 (역순으로 따라간 후 뒤집기)
        List<Integer> path = new ArrayList<>();
        for (int i = K; i != -1; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        for (int p : path) sb.append(p).append(" ");
        System.out.println(sb.toString().trim());
    }
}
