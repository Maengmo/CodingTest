package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Backjun3015 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 스택: (height, count)
        Deque<long[]> stack = new ArrayDeque<>();
        long answer = 0;

        for (int i = 0; i < N; i++) {
            long h = Long.parseLong(br.readLine());
            long cnt = 1;

            // 1) 현재 사람보다 키가 작은 사람들 pop
            while (!stack.isEmpty() && stack.peek()[0] < h) {
                answer += stack.peek()[1];
                stack.pop();
            }

            // 2) 현재 사람과 같은 키
            if (!stack.isEmpty() && stack.peek()[0] == h) {
                long sameCnt = stack.peek()[1];
                answer += sameCnt; // 같은 키끼리 모두 볼 수 있음
                stack.pop();
                cnt += sameCnt; // count 합치기

                // 같은 키 뒤에 더 큰 키가 있다면 1명 더 볼 수 있음
                if (!stack.isEmpty()) answer++;
            }
            // 3) 더 큰 키가 남아 있으면 그 1명과는 반드시 시야가 닿음
            else if (!stack.isEmpty()) {
                answer++;
            }

            // 현재 사람 push
            stack.push(new long[]{h, cnt});
        }

        System.out.println(answer);
    }
}
