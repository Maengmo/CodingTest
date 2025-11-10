package Tree;

import java.io.*;
import java.util.*;

public class Backjun1967 {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    static ArrayList<Edge>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // 첫 줄: n
        line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line.trim());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        int edgesToRead = n - 1;
        int readEdges = 0;
        while (readEdges < edgesToRead) {
            line = br.readLine();
            if (line == null) break;
            line = line.trim();
            if (line.isEmpty()) continue; // 빈 줄 스킵

            StringTokenizer st = new StringTokenizer(line);
            // Expect three integers per line: parent child weight
            if (st.countTokens() < 3) continue; // 안전하게 무시(혹은 에러 처리해도 됨)

            int parent = Integer.parseInt(st.nextToken());
            int child  = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[parent].add(new Edge(child, weight));
            adj[child].add(new Edge(parent, weight));
            readEdges++;
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int[] res1 = farthestFrom(1);       // {node, dist}
        int[] res2 = farthestFrom(res1[0]); // from that farthest node

        System.out.println(res2[1]);
    }

    // 반복 스택 기반 DFS: 시작점에서 가장 먼 노드와 거리 반환
    // 반환 배열: [0]=node, [1]=distance
    static int[] farthestFrom(int start) {
        boolean[] visited = new boolean[adj.length];
        Deque<int[]> stack = new ArrayDeque<>(); // 각 항목: {node, dist}
        stack.push(new int[]{start, 0});
        visited[start] = true;

        int farNode = start;
        int farDist = 0;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int node = cur[0];
            int dist = cur[1];

            if (dist > farDist) {
                farDist = dist;
                farNode = node;
            }

            for (Edge e : adj[node]) {
                if (!visited[e.to]) {
                    visited[e.to] = true;
                    stack.push(new int[]{e.to, dist + e.w});
                }
            }
        }

        return new int[]{farNode, farDist};
    }
}
