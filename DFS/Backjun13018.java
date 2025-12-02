package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun13018 {
     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // k = n 인 경우는 불가능
        if (k == n) {
            System.out.println("Impossible");
            return;
        }

        int[] A = new int[n + 1];
        // 초기에는 A[i] = i
        for (int i = 1; i <= n; i++) {
            A[i] = i;
        }

        // 현재 gcd(i, A[i]) > 1 이 되는 개수는 n-1 (i=1만 제외)
        // 원하는 k가 있으므로, 줄여야 할 개수 = (n-1) - k
        int need = (n - 1) - k;

        int idx = 2;
        // 인접 swap을 통해 한 번 swap 하면 영향을 받는 i가 2개 → need 2씩 줄어듦
        while (need > 1) {
            // swap A[idx] <-> A[idx+1]
            int tmp = A[idx];
            A[idx] = A[idx + 1];
            A[idx + 1] = tmp;
            need -= 2;
            idx += 2;
        }
        if (need == 1) {
            // 남은 하나 줄이려면 1과 n swap
            int tmp = A[1];
            A[1] = A[n];
            A[n] = tmp;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(A[i]).append(' ');
        }
        System.out.println(sb.toString().trim());
    }
}
