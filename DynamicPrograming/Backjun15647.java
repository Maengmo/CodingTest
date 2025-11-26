package DynamicPrograming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Backjun15647 {
    static class Node {
        int to, w;
        Node(int t, int w) {
            this.to = t;
            this.w = w;
        }
    }

    static int N;
    static ArrayList<Node>[] graph;
    static long[] distSum;     // 각 노드까지 거리 합
    static int[] sub;          // 서브트리 크기
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        distSum = new long[N + 1];
        sub = new int[N + 1];

        visited = new boolean[N + 1];
        dfs1(1, 0);        // 1번 루트 기준 distSum[1], 서브트리 크기 구하기

        visited = new boolean[N + 1];
        dfs2(1);           // 재루팅 DFS로 distSum 전체 채우기

        for (int i = 1; i <= N; i++) sb.append(distSum[i]).append("\n");
        System.out.print(sb);
    }

    // -----------------------------
    // 1) dfs1: distSum[1] 계산 + 서브트리 크기 계산
    // -----------------------------
    static void dfs1(int u, long dist) {
        visited[u] = true;
        distSum[1] += dist;
        sub[u] = 1;

        for (Node nxt : graph[u]) {
            int v = nxt.to;
            int w = nxt.w;

            if (!visited[v]) {
                dfs1(v, dist + w);
                sub[u] += sub[v];
            }
        }
    }

    // -----------------------------
    // 2) dfs2: 재루팅으로 distSum[v] 계산
    // 공식:
    // distSum[v] = distSum[u] + (N - sub[v]) * w - sub[v] * w
    // -----------------------------
    static void dfs2(int u) {
        visited[u] = true;

        for (Node nxt : graph[u]) {
            int v = nxt.to;
            int w = nxt.w;

            if (!visited[v]) {
                distSum[v] = distSum[u] + (long)(N - sub[v]) * w - (long)sub[v] * w;
                dfs2(v);
            }
        }
    }
}
