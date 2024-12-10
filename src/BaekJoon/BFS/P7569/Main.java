package BaekJoon.BFS.P7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //상,하,좌,우
    private static final int[] X = {-1,1,0,0};
    private static final int[] Y = {0,0,-1,1};
    //위,아래
    private static final int[] F = {1,-1};
    private static int M;
    private static int N;
    private static int H;
    private static List<int[][]> boxs = new ArrayList<>();
    private static List<boolean[][]> checks = new ArrayList<>();
    private static int[][] box;
    private static boolean[][] check;
    private static Queue<Cooldinate> queue = new LinkedList<>();

    public static class Cooldinate {
        private int x;
        private int y;
        private int floor;
        private int day;

        public Cooldinate(int x, int y, int floor, int day) {
            this.x = x;
            this.y = y;
            this.floor = floor;
            this.day = day;
        }
        public Integer[] value() {
            Integer[] arr = {x,y,floor,day};
            return arr;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for(int i=0; i<H; i++) {
            box = new int[N][M];
            check = new boolean[N][M];
            for(int x=0; x<N; x++) {
                st = new StringTokenizer(br.readLine());
                for(int y=0; y<M; y++) {
                    box[x][y] = Integer.parseInt(st.nextToken());
                    if(box[x][y] == 1) {
                        queue.offer(new Cooldinate(x,y,i,0));
                    }
                }
            }
            boxs.add(box);
            checks.add(check);
        }

        System.out.println("===================");

        //문제풀이

        int day = BFS(); //익은 토마토 기준으로 상하좌우,위아래로 하루에 한칸씩 익힘
        boolean result = search(); //토마토 상태 확인
        System.out.println(result ? day : -1); //전부 익었으면(true) 익기까지 걸린 날짜, 아니면 -1 출력
    }
    private static boolean search() {
        for(int[][] box : boxs) {
            for(int i=0; i<box.length; i++) {
                for(int j=0; j<box[i].length; j++) {
                    if(box[i][j] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int BFS() {
        int maxDay = 0;

        while(!queue.isEmpty()) {
            Cooldinate cooldinate = queue.poll();
            Integer[] values = cooldinate.value();

            //지금 익은토마토의 위치와 좌표, 익은 날짜
            int currentX = values[0];
            int currentY = values[1];
            int currentFloor = values[2];
            int nowDay = values[3];

            maxDay = Math.max(maxDay,nowDay);

            //상,하,좌,우
            for(int i=0; i<4; i++) {
                int nextX = currentX + X[i];
                int nextY = currentY+ Y[i];
                int[][] nowBox = boxs.get(currentFloor);
                if(nextX>=0 && nextY>=0 && nextX<N && nextY<M) {
                    if(nowBox[nextX][nextY] == 0) {
                        nowBox[nextX][nextY] = 1; //익은 상태로 변경
                        queue.offer(new Cooldinate(nextX,nextY,currentFloor,nowDay+1));
                    }
                }
            }
            //위,아래
            for(int i=0; i<2; i++) {
                int nextFloor = currentFloor + F[i];
                if(nextFloor>=0 && nextFloor<H) {
                    int[][] nowBox = boxs.get(nextFloor);
                    if(nowBox[currentX][currentY] == 0) {
                        nowBox[currentX][currentY] = 1;
                        queue.add(new Cooldinate(currentX, currentY, nextFloor, nowDay +1));
                    }
                }
            }

        }
        return maxDay;
    }

}