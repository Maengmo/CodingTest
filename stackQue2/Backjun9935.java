package stackQue2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Backjun9935 {
     public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();
        int n = str.length();
        int m = bomb.length();

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < n; i++) {
            stack.append(str.charAt(i));

            if (stack.length() >= m) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (stack.charAt(stack.length() - m + j) != bomb.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    stack.delete(stack.length() - m, stack.length());
                }
            }
        }

        if (stack.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(stack.toString());
        }
    }
}
