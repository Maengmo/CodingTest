package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Backjun28065 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int val;
            if ((i & 1) == 1) { // odd position
                val = 1 + (i - 1) / 2;
            } else { // even position
                val = N - (i / 2) + 1;
            }
            if (i > 1) sb.append(' ');
            sb.append(val);
        }
        System.out.println(sb.toString());
    }
}
