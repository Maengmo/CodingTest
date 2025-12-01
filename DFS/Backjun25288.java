package DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Backjun25288 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();  // 알파벳 집합

        StringBuilder sb = new StringBuilder(s.length() * N);

        for (int i = 0; i < N; i++) {
            sb.append(s);
        }

        System.out.println(sb.toString());
    }
}
