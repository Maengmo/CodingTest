package DynamicPrograming3;

import java.io.*;
import java.util.StringTokenizer;

public class Backjun11723 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int set = 0;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd) {
                case "add":
                    set |= (1 << Integer.parseInt(st.nextToken()));
                    break;

                case "remove":
                    set &= ~(1 << Integer.parseInt(st.nextToken()));
                    break;

                case "check":
                    sb.append((set & (1 << Integer.parseInt(st.nextToken()))) != 0 ? 1 : 0)
                      .append('\n');
                    break;

                case "toggle":
                    set ^= (1 << Integer.parseInt(st.nextToken()));
                    break;

                case "all":
                    set = (1 << 21) - 2;
                    break;

                case "empty":
                    set = 0;
                    break;
            }
        }

        System.out.print(sb);
    }
}
