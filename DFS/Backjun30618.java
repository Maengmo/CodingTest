package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Backjun30618 {
      static class Node {
        long weight;
        int idx;
        Node(long w, int i) {
            weight = w;
            idx = i;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Node[] arr = new Node[N];

        // 각 위치 i(1-indexed)의 가중치 계산
        for (int i = 1; i <= N; i++) {
            long weight = (long)i * (N - i + 1);
            arr[i - 1] = new Node(weight, i - 1);
        }

        // 가중치 내림차순 정렬 (가중치 큰 장소에 큰 숫자 배치)
        Arrays.sort(arr, (a, b) -> Long.compare(b.weight, a.weight));

        int[] result = new int[N];

        int value = N; // 가장 큰 값부터 배치
        for (Node node : arr) {
            result[node.idx] = value--;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int x : result) sb.append(x).append(" ");
        System.out.println(sb);
    }
}
