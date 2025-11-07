package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun9019 {
     static class Node {
        int num;
        String cmd;
        Node(int num, String cmd) {
            this.num = num;
            this.cmd = cmd;
        }
    }

    static String bfs(int A, int B) {
        boolean[] visited = new boolean[10000];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(A, ""));
        visited[A] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int num = cur.num;

            if (num == B) return cur.cmd;

            // D
            int d = (num * 2) % 10000;
            if (!visited[d]) {
                visited[d] = true;
                q.add(new Node(d, cur.cmd + "D"));
            }

            // S
            int s = (num == 0) ? 9999 : num - 1;
            if (!visited[s]) {
                visited[s] = true;
                q.add(new Node(s, cur.cmd + "S"));
            }

            // L
            int l = (num % 1000) * 10 + (num / 1000);
            if (!visited[l]) {
                visited[l] = true;
                q.add(new Node(l, cur.cmd + "L"));
            }

            // R
            int r = (num / 10) + (num % 10) * 1000;
            if (!visited[r]) {
                visited[r] = true;
                q.add(new Node(r, cur.cmd + "R"));
            }
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(bfs(A, B)).append('\n');
        }

        System.out.print(sb);
    }
}
