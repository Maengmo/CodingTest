package topologicalSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjun1766 {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            inDegree[B]++;
        }

        // 진입차수 0인 노드들을 우선순위 큐에 넣기
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) pq.add(i);
        }

        StringBuilder sb = new StringBuilder();

        // 위상 정렬
        while (!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");

            for (int next : graph.get(now)) {
                inDegree[next]--;
                if (inDegree[next] == 0) pq.add(next);
            }
        }

        System.out.println(sb.toString().trim());
    }
}
