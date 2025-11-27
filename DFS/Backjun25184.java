package DFS;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Backjun25184 {
  public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int half = N / 2;
        if (N % 2 == 1) {
            // N이 홀수일 경우
            int mid = (N + 1) / 2;
            bw.write(mid + "");
            for (int i = N; i > mid; i--) {
                bw.write(" " + i);
                bw.write(" " + (i - mid));
            }
        } else {
            // N이 짝수일 경우
            for (int i = N; i > half; i--) {
                bw.write((i - half) + " " + i);
                if (i != half + 1) bw.write(" ");
            }
        }

        bw.flush();
        bw.close();
    }
}
