package divideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Backjun6549 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            long[] heights = new long[n];
            for (int i = 0; i < n; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }

            sb.append(getMaxArea(heights)).append("\n");
        }

        System.out.print(sb);
    }

    private static long getMaxArea(long[] heights) {
        Stack<Integer> stack = new Stack<>();
        long maxArea = 0;
        int n = heights.length;

        for (int i = 0; i < n; i++) {
            // 현재 높이가 스택 top보다 작으면 pop하면서 넓이 계산
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                long height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        // 남은 막대 처리
        while (!stack.isEmpty()) {
            long height = heights[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

}
