package 과제sjChO1125;

import java.util.HashMap;
import java.util.Map;

public class GameResults {

    private static Map<Integer, Long> elapsedTimeMap = new HashMap<>();
    private static Map<Integer, Boolean> arrivedMap = new HashMap<>();

    public static void setResult(int gameNumber, long elapsedTime, boolean arrived) {
        elapsedTimeMap.put(gameNumber, elapsedTime);
        arrivedMap.put(gameNumber, arrived);
    }

    public static Map<Integer, String> getResultMap(int totalGames) {
        Map<Integer, String> resultMap = new HashMap<>();

        for (int i = 1; i <= totalGames; i++) {
            Long elapsedTime = elapsedTimeMap.get(i);
            boolean arrived = arrivedMap.get(i);

            if (elapsedTime != null && arrived) {
                if (elapsedTime > 0) {
                    resultMap.put(i, elapsedTime + "초");
                } else {
                    resultMap.put(i, "도착하지 못했습니다.");
                }
            } else {
                resultMap.put(i, "도착하지 못했습니다.");
            }
        }

        return resultMap;
    }

    public static long getElapsedTime(int gameNumber) {
        return elapsedTimeMap.getOrDefault(gameNumber, 0L);
    }
}
