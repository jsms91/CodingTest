package BaekJoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//적록색약(골드5)
public class P10026 {
    //상,하,좌,우 탐색
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};

    private static int N; //크기가 N*N
    private static String[][] ColorArr1; //색상 배열(적록색약x)
    private static String[][] ColorArr2; //색상 배열(적록색약)
    private static boolean[][] visit; //검색했는지 여부(적록색약x)
    private static int ans1 = 0; //적록색약아닌 정답
    private static int ans2 = 0; //적록색약인 정답

    private final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(BR.readLine());
        ColorArr1 = new String[N][N];
        ColorArr2 = new String[N][N];
        visit = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String[] str = BR.readLine().split("");
            for(int j=0; j< str.length; j++) {
                ColorArr1[i][j] = str[j];
                String col = str[j].equals("G") ? "R" : str[j];
                ColorArr2[i][j] = col;
            }
        }
        //적록색약(O)
        for(int i=0; i<ColorArr1.length; i++) {
            for(int j=0; j<ColorArr1[i].length; j++) {
                if(!visit[i][j]) {
                    DFS(i,j,ColorArr1[i][j],ColorArr1);
                    ans1++;
                }
            }
        }
        //초기화
        visit = new boolean[N][N];
        //적록색약(X)
        for(int i=0; i<ColorArr2.length; i++) {
            for(int j=0; j<ColorArr2[i].length; j++) {
                if(!visit[i][j]) {
                    DFS(i,j,ColorArr2[i][j],ColorArr2);
                    ans2++;
                }
            }
        }
        //정답
        System.out.println(ans1 + " " + ans2);

    }

    private static void DFS(int x, int y, String color1, String[][] colorArr) {
        if(visit[x][y] || !color1.equals(colorArr[x][y])) { //이미 탐색 또는 찾는 색상이랑(color1) 다를 경우 종료
            return;
        }
        visit[x][y] = true;

        for(int i=0; i<X.length; i++) {
            int nextX = x + X[i];
            int nextY = y + Y[i];
            if(nextX>=0 && nextY>=0 && nextX<N && nextY<N) { //배열 범위안에서만 탐색
                DFS(nextX,nextY,color1,colorArr);
            }
        }
    }

}