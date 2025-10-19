package OpenShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Backjun13549 {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);

        while (!dq.isEmpty()) {
            int now = dq.poll();

            if (now == K) break;

            // 순간이동 (0초)
            int teleport = now * 2;
            if (teleport <= MAX && dist[teleport] > dist[now]) {
                dist[teleport] = dist[now];
                dq.addFirst(teleport); // 0초 이동은 앞쪽
            }

            // 걷기 (1초)
            int[] move = {now - 1, now + 1};
            for (int next : move) {
                if (next >= 0 && next <= MAX && dist[next] > dist[now] + 1) {
                    dist[next] = dist[now] + 1;
                    dq.addLast(next); // 1초 이동은 뒤쪽
                }
            }
        }

        System.out.println(dist[K]);
    }
}
