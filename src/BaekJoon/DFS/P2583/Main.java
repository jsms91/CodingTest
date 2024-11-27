package BaekJoon.DFS.P2583;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    private static int M, N, K;
    private static boolean[][] check; //검사여부 체크
    private static boolean[][] map; //사각형 지도 배열
    private static final ArrayList<Integer> areas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        check = new boolean[M][N];
        map = new boolean[M][N];

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            mark(startX, startY, endX, endY);
        }

        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                if(!map[i][j] && !check[i][j]) { //빈 영역 and 방문하지 않은 경우
                    areas.add(DFS(i,j));
                }
            }
        }

        Collections.sort(areas);
        System.out.println(areas.size());
        for(int n : areas) {
            System.out.print(n + " ");
        }

    }

    //입력값을 배열에 표시(이 문제는 표시할 줄 아는게 중요)
    private static void mark(int startX, int startY, int endX, int endY) {
        for(int i=startY; i<endY; i++) { //Y와 X위치 변경으로 표시하는게 중요
            for(int j=startX; j<endX; j++) {
                map[i][j] = true;
            }
        }
    }
    
    //탐색을 통해서 구역별 정사각형 개수 구하기
    private static int DFS(int x, int y) {
        Stack<int[]> stack = new Stack<>();
        check[x][y] = true; //방문 체크
        int count = 0; //사각형 개수

        stack.push(new int[]{x,y});

        while(!stack.isEmpty()) {
            int[] current = stack.pop();
            count++;

            for(int i=0; i<4; i++) {
                int nX = current[0] + X[i];
                int nY = current[1] + Y[i];
                if(nX >=0 && nX < check.length && nY >=0 && nY < check[nX].length
                        && !map[nX][nY] && !check[nX][nY]) { //배열 범위 안이고 빈 정사각형이면서 탐색하지 않은 경우
                    check[nX][nY] = true;
                    stack.push(new int[] {nX,nY});
                }
            }
        }

        return count;
    }

}