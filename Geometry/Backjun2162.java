package Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Backjun2162 {
    static class Point {
        long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class Line {
        Point p1, p2;
        
        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
    
    static int[] parent;
    static int[] size;
    
    // Union-Find: Find
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // Union-Find: Union
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x == y) return;
        
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        
        parent[y] = x;
        size[x] += size[y];
    }
    
    // CCW 계산
    static int ccw(Point p1, Point p2, Point p3) {
        long result = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        if (result > 0) return 1;
        if (result < 0) return -1;
        return 0;
    }
    
    // 선분 교차 판정
    static boolean isIntersect(Line l1, Line l2) {
        Point p1 = l1.p1;
        Point p2 = l1.p2;
        Point p3 = l2.p1;
        Point p4 = l2.p2;
        
        int ccw1 = ccw(p1, p2, p3);
        int ccw2 = ccw(p1, p2, p4);
        int ccw3 = ccw(p3, p4, p1);
        int ccw4 = ccw(p3, p4, p2);
        
        int ab = ccw1 * ccw2;
        int cd = ccw3 * ccw4;
        
        // 일반적인 교차
        if (ab < 0 && cd < 0) return true;
        
        // 일직선상에 있는 경우
        if (ab == 0 && cd == 0) {
            // 선분이 겹치는지 확인
            if (Math.min(p1.x, p2.x) <= Math.max(p3.x, p4.x) &&
                Math.min(p3.x, p4.x) <= Math.max(p1.x, p2.x) &&
                Math.min(p1.y, p2.y) <= Math.max(p3.y, p4.y) &&
                Math.min(p3.y, p4.y) <= Math.max(p1.y, p2.y)) {
                return true;
            }
            return false;
        }
        
        // 한 선분의 끝점이 다른 선분 위에 있는 경우
        if (ab <= 0 && cd <= 0) return true;
        
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Line[] lines = new Line[n];
        parent = new int[n];
        size = new int[n];
        
        // Union-Find 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        // 선분 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            
            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }
        
        // 모든 선분 쌍에 대해 교차 판정
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isIntersect(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }
        
        // 그룹 개수와 최대 그룹 크기 계산
        Set<Integer> groups = new HashSet<>();
        int maxSize = 0;
        
        for (int i = 0; i < n; i++) {
            int root = find(i);
            groups.add(root);
            maxSize = Math.max(maxSize, size[root]);
        }
        
        System.out.println(groups.size());
        System.out.println(maxSize);
    }
}
