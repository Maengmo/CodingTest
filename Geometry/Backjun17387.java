package Geometry;

import java.io.*;
import java.util.*;

public class Backjun17387 {
    static class Point {
        long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        
        st = new StringTokenizer(br.readLine());
        Point p3 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point p4 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        
        if (isIntersect(p1, p2, p3, p4)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    
    // CCW 알고리즘
    static int ccw(Point a, Point b, Point c) {
        long result = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        
        if (result > 0) return 1;      // 반시계 방향
        if (result < 0) return -1;     // 시계 방향
        return 0;                       // 일직선
    }
    
    // 두 선분의 교차 판정
    static boolean isIntersect(Point p1, Point p2, Point p3, Point p4) {
        int ccw123 = ccw(p1, p2, p3);
        int ccw124 = ccw(p1, p2, p4);
        int ccw341 = ccw(p3, p4, p1);
        int ccw342 = ccw(p3, p4, p2);
        
        int ccw1 = ccw123 * ccw124;
        int ccw2 = ccw341 * ccw342;
        
        // 일반적인 교차
        if (ccw1 < 0 && ccw2 < 0) {
            return true;
        }
        
        // 일직선상에 있는 경우
        if (ccw1 == 0 && ccw2 == 0) {
            // 선분이 겹치는지 확인
            if (Math.max(p1.x, p2.x) < Math.min(p3.x, p4.x) || 
                Math.max(p3.x, p4.x) < Math.min(p1.x, p2.x)) {
                return false;
            }
            if (Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y) || 
                Math.max(p3.y, p4.y) < Math.min(p1.y, p2.y)) {
                return false;
            }
            return true;
        }
        
        // 한쪽 선분의 끝점이 다른 선분 위에 있는 경우
        if (ccw1 == 0) {
            if (ccw123 == 0 && isInRange(p1, p2, p3)) return true;
            if (ccw124 == 0 && isInRange(p1, p2, p4)) return true;
        }
        
        if (ccw2 == 0) {
            if (ccw341 == 0 && isInRange(p3, p4, p1)) return true;
            if (ccw342 == 0 && isInRange(p3, p4, p2)) return true;
        }
        
        return false;
    }
    
    // 점 c가 선분 (a, b)의 범위 안에 있는지
    static boolean isInRange(Point a, Point b, Point c) {
        return Math.min(a.x, b.x) <= c.x && c.x <= Math.max(a.x, b.x) &&
               Math.min(a.y, b.y) <= c.y && c.y <= Math.max(a.y, b.y);
    }
}