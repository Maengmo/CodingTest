package topologicalSort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun3665 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] lastRank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                lastRank[i] = Integer.parseInt(st.nextToken());
            }

            // 인접행렬로 간선 존재 여부 저장 (i -> j 가 있으면 adj[i][j] = true)
            boolean[][] adj = new boolean[n + 1][n + 1];
            int[] indeg = new int[n + 1];

            // 작년 등수로 모든 간선 추가: 등수가 더 높은(작년 i등) 팀 -> 뒤에 있는 팀
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    int higher = lastRank[i];
                    int lower = lastRank[j];
                    if (!adj[higher][lower]) {
                        adj[higher][lower] = true;
                        indeg[lower]++;
                    }
                }
            }

            int m = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // a와 b의 간선 방향을 뒤집는다
                if (adj[a][b]) {
                    adj[a][b] = false;
                    adj[b][a] = true;
                    indeg[b]--;
                    indeg[a]++;
                } else if (adj[b][a]) {
                    adj[b][a] = false;
                    adj[a][b] = true;
                    indeg[a]--;
                    indeg[b]++;
                } else {
                    // 이 경우는 원래 그래프 구축상 발생하지 않음 (원래 한 쪽으로만 존재)
                }
            }

            // 위상 정렬 (Kahn)
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (indeg[i] == 0) q.add(i);
            }

            List<Integer> result = new ArrayList<>();
            boolean ambiguous = false;
            boolean impossible = false;

            for (int k = 0; k < n; k++) {
                if (q.isEmpty()) {
                    // 사이클 -> 불가능
                    impossible = true;
                    break;
                }
                if (q.size() > 1) {
                    // 선택 가능한 노드가 2개 이상이면 순서가 확정되지 않음
                    ambiguous = true;
                    // 하지만 계속 진행은 해봐야 함 — 문제 요구: "?" 출력 (결정 불가)
                    // 우리는 그래프를 계속 처리해서 사이클 여부도 판정
                }
                int cur = q.poll();
                result.add(cur);
                // cur에서 나가는 간선들 모두 제거
                for (int nxt = 1; nxt <= n; nxt++) {
                    if (adj[cur][nxt]) {
                        indeg[nxt]--;
                        if (indeg[nxt] == 0) q.add(nxt);
                    }
                }
            }

            if (impossible) {
                out.append("IMPOSSIBLE\n");
            } else if (ambiguous) {
                out.append("?\n");
            } else {
                for (int i = 0; i < result.size(); i++) {
                    if (i > 0) out.append(' ');
                    out.append(result.get(i));
                }
                out.append('\n');
            }
        }

        System.out.print(out.toString());
    }
}
