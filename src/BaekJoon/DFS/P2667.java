package BaekJoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//단지번호 붙이기(실버1)
public class P2667 {

    static int N; //지도의 크기
    static int[][] Map; //지도
    static boolean[][] visit; //방문체크
    static int Home; //가구 수
    static ArrayList<Integer> sort = new ArrayList<>(); //가구 수 리스트
    static int count = 0; //단지 수
    //상,하,좌,우
    static final int[] X = {-1,1,0,0};
    static final int[] Y = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Map = new int[N][N];
        visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String[] num = br.readLine().split("");
            for(int j=0; j<N; j++) {
                Map[i][j] = Integer.parseInt(num[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(Map[i][j] == 1 && !visit[i][j]) { //집이 있고 방문하지 않은 경우
                    Home = 0;
                    count++;
                    DFS(i,j);
                    sort.add(Home);
                }
            }
        }
        
        Collections.sort(sort); //오름차순 정렬

        //정답
        System.out.println(count); //단지 수
        for(int h : sort){ //각 단지마다 가구 수
            System.out.println(h);
        }

    }

    private static void DFS(int x, int y) {
        if(x<0 || x>=N || y <0 || y >= N || visit[x][y]) { //범위를 벗어나면 탐색 종료
            return;
        }
        visit[x][y] = true; //방문했으니 체크

        //1번(상,하,좌,우를 배열로 통합하여 간결하지만 직관적이지 않음) 유지보수,확장성은 좋음
        if(Map[x][y] == 1) {
            Home++;
            //상,하,좌,우 탐색
            for(int i=0; i<X.length; i++) {
                DFS(x+X[i],y+Y[i]);
            }
        }

        //2번 직관적이지만 반복이 많아 비요율적이다.
        /*
        if(Map[x][y] == 1) {
            Home++;
            //상->하->좌->우
            DFS(x-1,y); DFS(x+1,y); DFS(x,y-1); DFS(x,y+1);
        }
         */
    }
}