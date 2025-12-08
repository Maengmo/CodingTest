package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Backjun11003 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> dq = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());

            // 1) 새 값보다 큰 값은 뒤에서 제거 → 덱을 단조 증가로 유지
            while (!dq.isEmpty() && dq.peekLast().value > value) {
                dq.pollLast();
            }

            // 2) 덱에 현재 값 push
            dq.offerLast(new Node(i, value));

            // 3) 윈도우 범위를 벗어난 값 제거
            if (dq.peekFirst().index <= i - L) {
                dq.pollFirst();
            }

            // 4) 덱의 front가 현재 구간의 최솟값
            sb.append(dq.peekFirst().value).append(' ');
        }

        System.out.println(sb);
    }

    static class Node {
        int index;
        int value;

        Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
