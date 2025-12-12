package Geometry;

import java.util.Arrays;
import java.util.Scanner;

public class Backjun25308 {
    
    static double[] angle = new double[8];

    static {
        for (int i = 0; i < 8; i++) {
            angle[i] = Math.toRadians(45 * i);
        }
    }

    static boolean isConvex(int[] a, int[] idx) {
        double[][] p = new double[8][2];

        for (int i = 0; i < 8; i++) {
            p[i][0] = a[idx[i]] * Math.cos(angle[i]);
            p[i][1] = a[idx[i]] * Math.sin(angle[i]);
        }

        int sign = 0;

        for (int i = 0; i < 8; i++) {
            int a1 = (i + 7) % 8;
            int b = i;
            int c = (i + 1) % 8;

            double x1 = p[b][0] - p[a1][0];
            double y1 = p[b][1] - p[a1][1];
            double x2 = p[c][0] - p[b][0];
            double y2 = p[c][1] - p[b][1];

            double cross = x1*y2 - y1*x2;

            if (cross == 0) return false;

            int cur = (cross > 0 ? 1 : -1);
            if (sign == 0) sign = cur;
            else if (sign != cur) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] a = new int[8];
        for (int i = 0; i < 8; i++) a[i] = sc.nextInt();

        int[] idx = {0,1,2,3,4,5,6,7};

        int count = 0;

        do {
            if (isConvex(a, idx)) count++;
        } while (nextPermutation(idx));

        System.out.println(count);
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i == 0) return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) j--;

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }
}
