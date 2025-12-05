package stackQue2;

import java.io.*;
import java.util.*;

public class Backjun17299 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] freq = new int[1000001];
        int[] ans = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            freq[A[i]]++;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            // freq 비교로 오등큰수 판별
            while (!stack.isEmpty() && freq[A[stack.peek()]] < freq[A[i]]) {
                ans[stack.pop()] = A[i];
            }
            stack.push(i);
        }

        // 스택 남은 인덱스는 NGF = -1 (기본값 0 → 덮기 필요)
        while (!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }

        // 출력
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(' ');
        }

        System.out.println(sb.toString());
    }
}
