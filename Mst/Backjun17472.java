package Mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun17472 {
     static int N, M;
    static int[][] a;
    static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge o) { return Integer.compare(this.w, o.w); }
    }

    static int[][] labelGrid;
    static int islandCount = 0;

    static void bfsLabel(int sx, int sy, int id) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy});
        labelGrid[sx][sy] = id;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int k=0;k<4;k++){
                int nx = x+dx[k], ny = y+dy[k];
                if (nx<0||nx>=N||ny<0||ny>=M) continue;
                if (a[nx][ny] == 1 && labelGrid[nx][ny] == 0) {
                    labelGrid[nx][ny] = id;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    static class UF {
        int[] p;
        UF(int n){ p = new int[n+1]; for (int i=0;i<=n;i++) p[i]=i;}
        int find(int x){ return p[x]==x?x:(p[x]=find(p[x]));}
        boolean union(int x,int y){
            x=find(x); y=find(y);
            if (x==y) return false;
            p[y]=x; return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++){
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1) 섬 번호 붙이기
        labelGrid = new int[N][M];
        islandCount = 0;
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if (a[i][j] == 1 && labelGrid[i][j] == 0) {
                    islandCount++;
                    bfsLabel(i,j,islandCount);
                }
            }
        }

        if (islandCount <= 1) { // 이미 하나 이하면 0 길이로 연결됨
            System.out.println(0);
            return;
        }

        // 2) 가능한 다리 찾기
        final int INF = 1_000_000_000;
        // 최소 길이를 저장할 배열 (1..islandCount)
        int[][] minEdge = new int[islandCount+1][islandCount+1];
        for (int i=1;i<=islandCount;i++) Arrays.fill(minEdge[i], INF);

        // 행 단위 스캔 (가로 다리)
        for (int i=0;i<N;i++){
            int prev = 0; // 이전 섬 id
            int seaCnt = 0;
            for (int j=0;j<M;j++){
                int id = labelGrid[i][j];
                if (id == 0) {
                    if (prev != 0) seaCnt++;
                } else { // land
                    if (prev == 0) {
                        prev = id;
                        seaCnt = 0;
                    } else {
                        if (prev != id) {
                            if (seaCnt >= 2) {
                                int u = prev, v = id;
                                if (minEdge[u][v] > seaCnt) {
                                    minEdge[u][v] = minEdge[v][u] = seaCnt;
                                }
                            }
                            prev = id;
                            seaCnt = 0;
                        } else {
                            // same island, reset seaCnt
                            seaCnt = 0;
                            prev = id;
                        }
                    }
                }
            }
        }

        // 열 단위 스캔 (세로 다리)
        for (int j=0;j<M;j++){
            int prev = 0;
            int seaCnt = 0;
            for (int i=0;i<N;i++){
                int id = labelGrid[i][j];
                if (id == 0) {
                    if (prev != 0) seaCnt++;
                } else {
                    if (prev == 0) {
                        prev = id;
                        seaCnt = 0;
                    } else {
                        if (prev != id) {
                            if (seaCnt >= 2) {
                                int u = prev, v = id;
                                if (minEdge[u][v] > seaCnt) {
                                    minEdge[u][v] = minEdge[v][u] = seaCnt;
                                }
                            }
                            prev = id;
                            seaCnt = 0;
                        } else {
                            seaCnt = 0;
                            prev = id;
                        }
                    }
                }
            }
        }

        // 3) 간선 리스트로 변환
        List<Edge> edges = new ArrayList<>();
        for (int u=1; u<=islandCount; u++){
            for (int v=u+1; v<=islandCount; v++){
                if (minEdge[u][v] < INF) {
                    edges.add(new Edge(u,v,minEdge[u][v]));
                }
            }
        }

        // 4) Kruskal MST
        Collections.sort(edges);
        UF uf = new UF(islandCount);
        int used = 0;
        int total = 0;
        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                total += e.w;
                used++;
                if (used == islandCount - 1) break;
            }
        }

        if (used == islandCount - 1) {
            System.out.println(total);
        } else {
            System.out.println(-1);
        }
    }
}
