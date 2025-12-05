package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Backjun1725 {
     public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] h = new int[N];

        for (int i = 0; i < N; i++) {
            h[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        long max = 0;

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && h[stack.peek()] > h[i]) {
                int height = h[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                max = Math.max(max, (long) height * width);
            }
            stack.push(i);
        }

        // 남은 막대 처리
        while (!stack.isEmpty()) {
            int height = h[stack.pop()];
            int width = stack.isEmpty() ? N : (N - stack.peek() - 1);
            max = Math.max(max, (long) height * width);
        }

        System.out.println(max);
    }
}
