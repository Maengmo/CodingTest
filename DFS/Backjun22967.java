package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Backjun22967 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        // 트리 간선 수 = N - 1
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new HashSet<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        long possibleTotal = (long) N * (N - 1) / 2;
        long current = N - 1;
        long maxAddable = N - 1;
        List<int[]> addEdges = new ArrayList<>();

        if (current + maxAddable >= possibleTotal) {
            // 완전 그래프 만들기 (지름 = 1)
            for (int i = 1; i <= N; i++) {
                for (int j = i + 1; j <= N; j++) {
                    if (!adj.get(i).contains(j)) {
                        addEdges.add(new int[]{i, j});
                    }
                }
            }
        } else {
            // star 구조 (지름 = 2)
            int center = 1;  // 임의로 1번을 중심으로 잡음
            for (int i = 1; i <= N; i++) {
                if (i == center) continue;
                if (!adj.get(center).contains(i)) {
                    addEdges.add(new int[]{center, i});
                }
            }
        }

        // 출력
        System.out.println(addEdges.size());
        // 출력할 지름 R (1 or 2)
        int R = (current + maxAddable >= possibleTotal) ? 1 : 2;
        System.out.println(R);
        for (int[] e : addEdges) {
            System.out.println(e[0] + " " + e[1]);
        }
    }
}
