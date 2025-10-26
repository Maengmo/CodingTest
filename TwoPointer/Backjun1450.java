package TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Backjun1450 {
    static int N;
    static long C;
    static int[] weight;
    static List<Long> leftSum = new ArrayList<>();
    static List<Long> rightSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 절반으로 나누기
        dfs(0, N / 2, 0, leftSum);
        dfs(N / 2, N, 0, rightSum);

        Collections.sort(rightSum);

        long count = 0;
        for (long s : leftSum) {
            if (s > C) continue;
            long remain = C - s;
            count += upperBound(rightSum, remain);
        }

        System.out.println(count);
    }

    // 부분합을 모두 구하는 DFS
    static void dfs(int start, int end, long sum, List<Long> list) {
        if (start == end) {
            list.add(sum);
            return;
        }
        dfs(start + 1, end, sum, list); // 현재 물건 안 넣음
        dfs(start + 1, end, sum + weight[start], list); // 현재 물건 넣음
    }

    // upperBound: remain 이하인 원소 개수 찾기
    static int upperBound(List<Long> list, long key) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) <= key) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
