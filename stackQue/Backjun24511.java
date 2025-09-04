package stackQue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backjun24511 {

     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 자료구조 개수

        int[] A = new int[N];
        int[] B = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] C = new int[M];
        for (int i = 0; i < M; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        // 큐 준비 (뒤에서 앞으로)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            if (A[i] == 0) {
                queue.offer(B[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        // 입력된 값들을 하나씩 처리
        for (int i = 0; i < M; i++) {
            int output;
            if (queue.isEmpty()) {
                output = C[i]; // 큐가 없으면 그대로 출력
            } else {
                output = queue.poll(); // 큐 앞에서 꺼냄
                queue.offer(C[i]);     // 새 값은 뒤로 넣음
            }
            sb.append(output).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
    
}
