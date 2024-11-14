package Programers.Java.level1;

//(리팩토링 후)
public class P4_2 {
    private final int MOVE_STEP = 10;
    private int totalLen;
    private int nowPos;
    private int start;
    private int end;

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        totalLen = secTime(video_len); //총 비디오 길이
        nowPos = secTime(pos); //현재 구간
        start = secTime(op_start); //오프닝 시작
        end = secTime(op_end); //오프닝 끝

        //명령어 실행
        for(String command : commands) {
            nowPos = openning(); //처음에 오프닝 구간인지 계산
            nowPos = move(command); //명령어 대로 이동
            nowPos = openning(); //이동 후 오프닝 구간인지 계산
        }

        return strTime();
    }
    //오프닝 구간인지 확인 후 계산(오프닝구간일 경우 end, 아닐 경우 현재 위치 그대로)
    private int openning() {
        return nowPos>=start && nowPos <= end ? end : nowPos;
    }
    //명령어 실행
    private int move(String command) {
        if(command.equals("next")) {
            return nowPos + MOVE_STEP > totalLen ? totalLen : nowPos + MOVE_STEP;
        } else {
            return nowPos - MOVE_STEP < 0 ? 0 : nowPos - MOVE_STEP;
        }
    }
    //문자열인 시간을 초단위 정수로 변환
    private int secTime(String strtime) {
        String[] t = strtime.split(":");
        return Integer.parseInt(t[0].substring(0,1)) * 600
                + Integer.parseInt(t[0].substring(1,2))* 60
                + Integer.parseInt(t[1].substring(0,1)) * 10
                + Integer.parseInt(t[1].substring(1,2)) * 1;
    }
    //초단위 정수를 문자열로 변환
    private String strTime() {
        int m = nowPos / 60;
        int s = nowPos % 60;
//        String t1 = m < 10 ? 0 + String.valueOf(m) : String.valueOf(m);
//        String t2 = s < 10 ? 0 + String.valueOf(s) : String.valueOf(s);
//        return t1 + ":" + t2;
        return String.format("%02d:%02d",m,s);
    }
}