package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Racing {
    private String input;
    private int racingCount;
    HashMap<String, String> cars;
    List<String> rank;

    public void inputRacingCar() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        input = Console.readLine();
    }

    public void splitRacingCar() {
        String[] split = input.split(",");
        for (String name : split) {
            cars.put(name, "");
        }
    }

    public void inputRacingCount() {
        System.out.println("시도할 회수는 몇회인가요?");
        racingCount = Integer.parseInt(Console.readLine());
    }

    public void eachRound() {
        for (String key : cars.keySet()) {
            if (enableForward()) {
                cars.put(key, cars.get(key) + "-");
            }
        }
    }

    public boolean enableForward() {
        int number = Randoms.pickNumberInRange(0, 9);
        if (number >= 4) {
            return true;
        }
        return false;
    }

    public void allRound() {
        while (racingCount > 0) {
            eachRound();
            racingCount--;
        }
    }

    public void sortMap() {
        rank = new ArrayList<>(cars.keySet());
        rank.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return cars.get(o2).length() - cars.get(o1).length();
            }
        });
    }

    public void printWinner() {
        for (String keySet : cars.keySet()) {
            if (cars.get(rank.get(0)).length() == cars.get(keySet).length()) {
                System.out.print(keySet);
                continue;
            }
            break;
        }
    }
}
