package programers.java.level0;
//level0[PEEC 기출문제] 7번/버스
public class P1 {

    public int solution(int seat, String[][] passengers) {
        int num_passenger = 0;
        for(int i=0; i<passengers.length; i++){
            num_passenger += func4(passengers[i]); //승차하는 사람 수 -> 빈칸
            num_passenger -= func3(passengers[i]); //하차하는 사람 수 -> 빈칸
        }
        int answer = func1(seat - num_passenger); //남은 좌석(총좌석 - 차지하는좌석) -> 빈칸ㅔ
        return answer;
    }

    public int func1(int num){
        if(0 > num){
            return 0;
        }
        else{
            return num;
        }
    }
    public int func2(int num){
        if(num > 0){
            return 0;
        }
        else{
            return num;
        }
    }

    public int func3(String[] station){
        int num = 0;
        for(int i=0; i<station.length; i++){
            if(station[i].equals("Off")){
                num += 1;
            }
        }
        return num;
    }

    public int func4(String[] station){
        int num = 0;
        for(int i=0; i<station.length; i++){
            if(station[i].equals("On")){
                num += 1;
            }
        }
        return num;
    }
}