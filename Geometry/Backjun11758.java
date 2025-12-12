package Geometry;

import java.util.Scanner;

public class Backjun11758 {
      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();

        long cross = (long)(x2 - x1) * (y3 - y1) - (long)(y2 - y1) * (x3 - x1);

        if (cross > 0) {
            System.out.println(1);   // 반시계
        } else if (cross < 0) {
            System.out.println(-1);  // 시계
        } else {
            System.out.println(0);   // 일직선
        }
    }
}
