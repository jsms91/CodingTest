package BaekJoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//적록색약(골드5)
//적록색약과 아닌 경우를 DFS에서 구별하여 탐색하기 때문에 앞에 첫번째 풀이 방식에 비해 효율적이다.
//여기서 중요한 점은
//boolean[][] visit를 visitNormal,visitColorBlind로 같은 참조값을 가지도록 해서 변경시 같은 인스턴스를 참조하기 때문에 같이 변경되어진다는 개념을 알고 있어야 한다.
public class P10026_2 {
    //상,하,좌,우 탐색
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};

    private static int N; //크기가 N*N
    private static String[][] ColorArr; //색상 배열(적록색약x)
    private static boolean[][] visitNormal; //검색했는지 여부(적록색약 x)
    private static boolean[][] visitColorBlind; //검색했는지 여부(적록색약 O)
    private static int ans1 = 0; //적록색약아닌 정답
    private static int ans2 = 0; //적록색약인 정답

    private final static BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(BR.readLine());
        ColorArr = new String[N][N];
        visitNormal = new boolean[N][N];
        visitColorBlind = new boolean[N][N];

        for(int i=0; i<N; i++) {
            String[] str = BR.readLine().split("");
            for(int j=0; j< str.length; j++) {
                ColorArr[i][j] = str[j];
            }
        }

        for(int i=0; i<ColorArr.length; i++) {
            for(int j=0; j<ColorArr[i].length; j++) {
                if(!visitNormal[i][j]) { //적록색약 아닌 경우
                    DFS(i,j,ColorArr[i][j],false);
                    ans1++;
                }
                if(!visitColorBlind[i][j]) { //적록색약인 경우
                    DFS(i,j,ColorArr[i][j],true);
                    ans2++;
                }
            }
        }

        //정답
        System.out.println(ans1 + " " + ans2);

    }

    private static void DFS(int x, int y, String currentColor, boolean isColorBlind ) {
        //visit가 변경되도 visit가 참조하는 visitColorBlind,visitNormal의 해당 배열값도 같이 변경된다.
        //visit도 00x1을 참조, visitColorBlind도 00x1을 참조
        //visit = visitColorBlind 경우
        //visit = visitNormal의 경우
        //visit도 00x2을 참조, visitNormal의 00x2을 참조
        boolean[][] visit = isColorBlind ? visitColorBlind : visitNormal;

        if(visit[x][y]) { return; } //탐색했던 곳이면 종료

        //적록색약인데 현재 R이나G일 경우 인접 색상이 R,G가 아니면 종료
        if (isColorBlind && (currentColor.equals("R") || currentColor.equals("G"))) {
            if (!ColorArr[x][y].equals("R") && !ColorArr[x][y].equals("G")) return;
        }
        //나머지 경우(적록색약이지만  현재 R과G가 아닌 B인 경우와 적록색약 아닌 모든 색상의 경우) 현재와 인접한 색생이 다른 경우 종료
        else if (!currentColor.equals(ColorArr[x][y])) {
            return;
        }

        visit[x][y] = true;


        // 상, 하, 좌, 우 탐색
        for (int i = 0; i < 4; i++) {
            int nextX = x + X[i];
            int nextY = y + Y[i];
            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                DFS(nextX, nextY, currentColor, isColorBlind);
            }
        }

    }

}