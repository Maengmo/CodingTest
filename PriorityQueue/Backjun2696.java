package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjun2696 {
    
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int M = Integer.parseInt(br.readLine().trim());
            int[] nums = new int[M];

            int idx = 0;
            while (idx < M) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    nums[idx++] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> right = new PriorityQueue<>();

            List<Integer> medians = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                int x = nums[i];

                if (left.isEmpty() || x <= left.peek()) {
                    left.offer(x);
                } else {
                    right.offer(x);
                }

                // 균형 맞추기
                if (left.size() < right.size()) {
                    left.offer(right.poll());
                } else if (left.size() > right.size() + 1) {
                    right.offer(left.poll());
                }

                // 홀수 번째 입력 → 중앙값 추가
                if ((i + 1) % 2 == 1) {
                    medians.add(left.peek());
                }
            }

            // 출력
            sb.append(medians.size()).append("\n");
            for (int i = 0; i < medians.size(); i++) {
                sb.append(medians.get(i)).append(" ");
                if ((i + 1) % 10 == 0) sb.append("\n");
            }
            if (medians.size() % 10 != 0) sb.append("\n");
        }

        System.out.print(sb);
    }

}
