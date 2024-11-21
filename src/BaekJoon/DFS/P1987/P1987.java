package BaekJoon.DFS.P1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P1987 {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};

    private static int R; //세로 크기
    private static int C; //가로 크기
    private static char[][] board; //알파벳 보드
    private static int max = 0; //최장 경로 길이
    private static HashSet<Character> visit = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for(int i=0; i<R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visit.add(board[0][0]); //기준이 되는 촤즉 상단 문자를 먼저 넣기
        DFS(0,0,1); //탐색 시작
        System.out.println(max);

    }

    private static void DFS(int x, int y, int cnt) {

        //최장경로 길이 갱신
        max = Math.max(max,cnt);

        for(int i=0; i<4; i++) {
            //상,하,좌,우로 이동하여 탐색
            int nextX = x + X[i];
            int nextY = y + Y[i];

            if(nextX>=0 && nextY>=0 && nextX<R && nextY<C) { //배열 범위 안에서만 탐색
                char newChar = board[nextX][nextY]; //다음 알파벳
                if(!visit.contains(newChar)) { //지나오지 않은 알파벳
                    visit.add(newChar); //새로운 알파벳 추가
                    DFS(nextX,nextY,cnt + 1); //다음 탐색 시작
                    visit.remove(newChar); //다음 방향 탐색을 위해 추가한 알파벳 제거
                }
            }
        }

    }
}