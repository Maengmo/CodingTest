package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Backjun31836 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        // F[1..N] (1-indexed)
        BigInteger[] F = new BigInteger[Math.max(3, N + 1)];
        F[0] = BigInteger.ZERO;
        if (N >= 1) F[1] = BigInteger.ONE;
        if (N >= 2) F[2] = BigInteger.ONE;
        for (int i = 3; i <= N; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }

        // 전체 합
        BigInteger total = BigInteger.ZERO;
        for (int i = 1; i <= N; i++) total = total.add(F[i]);

        // 포함할 인덱스 판단
        boolean[] include = new boolean[N + 1]; // true 면 분배 대상(=사용)
        Arrays.fill(include, false);
        if (N % 3 == 1) {
            // N % 3 == 1 인 경우 인덱스 1을 빼고 나머지 사용
            for (int i = 2; i <= N; i++) include[i] = true;
        } else {
            // 그 외는 모두 사용
            for (int i = 1; i <= N; i++) include[i] = true;
        }

        // 사용되는 합
        BigInteger usedSum = BigInteger.ZERO;
        for (int i = 1; i <= N; i++) if (include[i]) usedSum = usedSum.add(F[i]);

        // target = usedSum / 2 (짝수이어야 함 by construction)
        BigInteger target = usedSum.divide(BigInteger.valueOf(2));

        // Greedy: 큰 인덱스부터 target 맞추기
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        for (int i = N; i >= 1; i--) {
            if (!include[i]) continue; // 제외된 항목(예: N%3==1일때 i==1)
            if (F[i].compareTo(target) <= 0) {
                A.add(i);
                target = target.subtract(F[i]);
            } else {
                B.add(i);
            }
        }

        Collections.sort(A);
        Collections.sort(B);

        StringBuilder sb = new StringBuilder();
        sb.append(A.size()).append('\n');
        for (int i = 0; i < A.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(A.get(i));
        }
        sb.append('\n');
        sb.append(B.size()).append('\n');
        for (int i = 0; i < B.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(B.get(i));
        }
        sb.append('\n');

        System.out.print(sb.toString());
    }
}
