package divideConquer;

import java.io.*;
import java.util.*;

public class Backjun2740 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 행렬 A 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // A의 행
        int M = Integer.parseInt(st.nextToken()); // A의 열
        int[][] A = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행렬 B 입력
        st = new StringTokenizer(br.readLine());
        int M2 = Integer.parseInt(st.nextToken()); // B의 행 (== A의 열)
        int K = Integer.parseInt(st.nextToken());  // B의 열
        int[][] B = new int[M2][K];

        for (int i = 0; i < M2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 결과 행렬 C (N x K)
        int[][] C = new int[N][K];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                int sum = 0;
                for (int t = 0; t < M; t++) {
                    sum += A[i][t] * B[t][j];
                }
                C[i][j] = sum;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                sb.append(C[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
