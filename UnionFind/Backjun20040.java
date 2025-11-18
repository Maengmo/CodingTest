package UnionFind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun20040 {
    
    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 점 개수
        int m = Integer.parseInt(st.nextToken());   // 진행된 차례 수

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) parent[i] = i;

        int answer = 0;

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 사이클 발생 확인
            if (find(a) == find(b)) {
                answer = i; // 처음 사이클 생긴 차례
                break;
            } else {
                union(a, b);
            }
        }

        // 입력을 더 읽어야 하는 경우(이미 answer 발견했으면) 그냥 읽기만 한다.
        // 출력은 미리 결정됨.
        System.out.println(answer);
    }

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        // union by rank
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }
}
