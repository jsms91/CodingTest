package BaekJoon.DFS;

import java.io.*;
import java.util.*;

//바이러스(실버3)
public class P2606 {
    private static boolean[] check; //검사 여부
    private static ArrayList<Integer>[] ComLink; //연결된 컴퓨터 배열
    private static int ComNum; //컴퓨터 수
    private static int LinkNum; //연결된 쌍의수
    private static int ans = 0; //정답
    private static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        ComNum = Integer.parseInt(BR.readLine()); // 컴퓨터의 수
        LinkNum = Integer.parseInt(BR.readLine()); //연결된 쌍의 수

        check = new boolean[ComNum+1];
        ComLink = new ArrayList[ComNum+1];

        for(int i=0; i<ComNum+1; i++) {
            ComLink[i] = new ArrayList<>();
        }

        for(int i=0; i<LinkNum; i++) {
            st = new StringTokenizer(BR.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            //반드시 양뱡향으로 해야한다.
            //예시 -> 2-1일 단방향으로는 탐색을 하지 않기 때문
            ComLink[num1].add(num2);
            ComLink[num2].add(num1);
        }

        DFS(1);

        for(int i=2; i<check.length; i++) {
            ans += check[i] ? 1 : 0;
        }

        System.out.println(ans);

    }

    private static void DFS(int start) {
        if(check[start]) { return; } //탐색했던 곳이면 종료
        check[start] = true; //탐색했다고 표시
        if(ComLink[start].size()==0) { return; } //연결된 컴퓨터가 없으면 탐색 종료

        for(int com : ComLink[start]) {
            DFS(com);
        }

    }
}