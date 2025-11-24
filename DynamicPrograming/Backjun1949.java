package DynamicPrograming;

import java.io.*;
import java.util.*;

public class Backjun1949 {

    static int N;
    static int[] population;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        population = new int[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        visited[node] = true;

        dp[node][1] = population[node]; // node를 선택한 경우

        for (int next : tree[node]) {
            if (!visited[next]) {
                dfs(next);
                dp[node][0] += Math.max(dp[next][0], dp[next][1]); // 선택 안함
                dp[node][1] += dp[next][0]; // 선택함 -> 자식 선택 불가
            }
        }
    }
}
