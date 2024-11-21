package BaekJoon.DFS.P1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//유기농 배추(실버2)
public class P1012 {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    //정답 배열
    private static final ArrayList<Integer> ANSWER = new ArrayList<>();
    //입력
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T; //테스트 케이스의 개수 (1 ≤ M ≤ 50)
    private static int M; //배추밭 가로 길이 (1 ≤ N ≤ 50)
    private static int N; //배추밭 세로 길이 (1 ≤ M ≤ 50)
    private static int K; //배추가 심어진 위치 개수 (1 ≤ K ≤ 2500)
    private static int count; //인접한 배추들의 집단 개수
    private static int[][] farm; //배추가 심어진 밭의 배열
    private static boolean[][] visit; //배추 유무 확인시 중복 체크 방지를 위한 배열

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine()); //테스트 케이스 입력

        for(int i=0; i<T; i++) {

            count = 0; //마리수 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); //가로(y)
            N = Integer.parseInt(st.nextToken()); //세로(x)
            K = Integer.parseInt(st.nextToken()); //배추 수

            farm = new int[M][N]; //배추밭 배열의 크기
            visit = new boolean[M][N]; //배추밭 방문 체크 배열 크기(배추밭과 동일)

            //배추밭의 값 입력
            for(int j=0; j<K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                farm[x][y] = 1;
            }
            
            //배추흰지렁이 마리 수 구하기
            for(int left=0; left<M; left++) {
                for(int right=0; right<N; right++) {
                    if(farm[left][right] == 1 && !visit[left][right]) { //해당 위치에 배추가 있고, 방문한적 없는 경우
                        DFS1(left,right);
                        count++;
                    }
                }
            }

            ANSWER.add(count); //테스트 케이스 별 정답 넣기

        }

        //정답 출력
        for(int result : ANSWER) {
            System.out.println(result);
        }

    }
    private static void DFS1(int left, int right) {
        if(left < 0 || left >= M || right < 0 || right >= N) {return;} //배열 범위 벗어나면 종료
        if(visit[left][right]) {return;} //방문(검사)한 경우 종료
        visit[left][right] = true; //방문 체크
        if(farm[left][right] != 1) {return;} //배추 없으면 종료

        for(int i=0; i<X.length; i++) { //상,하,좌,우 검사(배추 있다.)
            DFS1(left+X[i],right+Y[i]); //탐색
        }
    }

    //2번째 방식 DFS실행전 배열 범위인 경우만 실행(배열범위 벗어나면 실행X) -> 무분별한 DFS실행 방지
    private static void DFS2(int left, int right) {
        if(visit[left][right]) {return;} //방문(검사)한 경우 종료
        visit[left][right] = true; //방문 체크
        if(farm[left][right] != 1) {return;} //배추 없으면 종료

        for(int i=0; i<X.length; i++) { //상,하,좌,우 검사(배추 있다.)
            //left,right를 사용하지 않은 이유
            //left, rigth에 값을 덮어버리게 되어 다음 반복문에서 기존 left, right 값이 변경되어 탐색 오류
            int nextX = left + X[i];
            int nextY = right + Y[i];
            if(nextX >=0 && nextX <M && nextY >=0 && nextY < N) { //배열 범위에서만 실행
                DFS2(nextX, nextY);
            }
        }
    }
}