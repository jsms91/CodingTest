package programers.java.level1;

//PCCP 기출문제 1번 - 동영상 재생기_리팩토링 전(내가 푼 방식)
public class P4_1 {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        int totalLen = secTime(video_len); //총 비디오 길이
        int nowPos = secTime(pos); //현재 구간
        int start = secTime(op_start); //오프닝 시작
        int end = secTime(op_end); //오프닝 끝

        //명령어 실행
        for(String command : commands) {
            nowPos = openning(start, end, nowPos); //처음에 오프닝 구간인지 계산
            nowPos = move(command,nowPos,totalLen); //명령어 대로 이동
            nowPos = openning(start, end, nowPos); //이동 후 오프닝 구간인지 계산
        }

        return strTime(nowPos);
    }

    //오프닝 구간인지 확인 후 계산(오프닝구간일 경우 end, 아닐 경우 현재 위치 그대로)
    private int openning(int start, int end, int nowPos) {
        return nowPos>=start && nowPos <= end ? end : nowPos;
    }

    //명령어 실행
    private static int move(String command, int now, int len) {
        if(command.equals("next")) {
            return now + 10 > len ? len : now +10;
        } else {
            return now - 10 < 0 ? 0 : now - 10;
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
    private String strTime(int secTime) {
        int m = secTime / 60;
        int s = secTime % 60;
//        String t1 = m < 10 ? 0 + String.valueOf(m) : String.valueOf(m);
//        String t2 = s < 10 ? 0 + String.valueOf(s) : String.valueOf(s);
//        return t1 + ":" + t2;
        return String.format("%02d:%02d",m,s);
    }
}
