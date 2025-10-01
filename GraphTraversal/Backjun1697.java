package GraphTraversal;

import java.util.*;
import java.io.*;

public class Backjun1697 {
    
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        System.out.println(bfs(N, K));
    }

    static int bfs(int N, int K) {
        boolean[] visited = new boolean[MAX + 1];
        Queue<int[]> queue = new LinkedList<>();

        // 시작점 추가: {현재 위치, 시간}
        queue.add(new int[]{N, 0});
        visited[N] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int time = current[1];

            // 목표 지점 도착
            if (pos == K) {
                return time;
            }

            // 이동할 수 있는 세 가지 경우
            int[] nextPos = {pos - 1, pos + 1, pos * 2};
            for (int next : nextPos) {
                if (next >= 0 && next <= MAX && !visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, time + 1});
                }
            }
        }
        return -1; // 도달 불가 (이론상 나오지 않음)
    }

}
