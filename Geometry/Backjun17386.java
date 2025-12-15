package Geometry;

import java.io.*;
import java.util.*;

public class Backjun17386 {
    static class Point {
        long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // L1의 두 점
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        
        // L2의 두 점
        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p4 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        
        // 선분 교차 판정
        if (isIntersect(p1, p2, p3, p4)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    
    // CCW 알고리즘: 세 점의 방향성 판단
    static long ccw(Point a, Point b, Point c) {
        // 외적을 이용한 CCW
        // (b-a) × (c-a) = (b.x-a.x)(c.y-a.y) - (b.y-a.y)(c.x-a.x)
        long result = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        
        if (result > 0) return 1;      // 반시계 방향
        if (result < 0) return -1;     // 시계 방향
        return 0;                       // 일직선
    }
    
    // 두 선분의 교차 판정
    static boolean isIntersect(Point p1, Point p2, Point p3, Point p4) {
        long ccw1 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        long ccw2 = ccw(p3, p4, p1) * ccw(p3, p4, p2);
        
        // 두 선분이 교차하려면 양쪽 CCW 곱이 모두 음수여야 함
        return ccw1 < 0 && ccw2 < 0;
    }
}