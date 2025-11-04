package DynamicProgAndShortestBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Backjun14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] pos = new int[N]; // 각 원소가 LIS 배열 내 어디 위치했는지 저장

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> lis = new ArrayList<>();
        lis.add(arr[0]);
        pos[0] = 0;

        for (int i = 1; i < N; i++) {
            int num = arr[i];
            if (num > lis.get(lis.size() - 1)) {
                lis.add(num);
                pos[i] = lis.size() - 1;
            } else {
                int idx = Collections.binarySearch(lis, num);
                if (idx < 0) idx = -idx - 1;
                lis.set(idx, num);
                pos[i] = idx;
            }
        }

        int length = lis.size();
        sb.append(length).append("\n");

        Stack<Integer> stack = new Stack<>();
        int idx = length - 1;

        // 뒤에서부터 실제 LIS 복원
        for (int i = N - 1; i >= 0; i--) {
            if (pos[i] == idx) {
                stack.push(arr[i]);
                idx--;
            }
            if (idx < 0) break;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}
