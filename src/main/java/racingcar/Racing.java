package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class Racing {
    private int racingRound;
    LinkedHashMap<String, String> cars;
    List<String> rank;

    public void inputRacingCar() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        input = input.replaceAll(" ", "");
        checkNullInputCar(input);
        checkSpecialCharacter(input);
        splitRacingCar(input);
    }

    public void checkNullInputCar(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public void checkSpecialCharacter(String input) {
        if (!input.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|,]*")) {
            throw new IllegalArgumentException();
        }
    }

    public void splitRacingCar(String input) {
        cars = new LinkedHashMap<>();
        String[] split = input.split(",");
        for (String name : split) {
            checkDuplicateCar(name);
            cars.put(name, "");
        }
    }

    public void checkDuplicateCar(String name) {
        if (cars.containsKey(name)) {
            throw new IllegalArgumentException();
        }
    }

    public void inputRacingRound() {
        System.out.println("시도할 회수는 몇회인가요?");
        String round = Console.readLine();
        checkNullInputRound(round);
        checkIntegerRound(round);
    }

    public void checkNullInputRound(String round) {
        if (round.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public void checkIntegerRound(String round) {
        try {
            round = round.replaceAll(" ", "");
            racingRound = Integer.parseInt(round);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

    }

    public void eachRound() {
        for (String key : cars.keySet()) {
            if (enableForward()) {
                cars.put(key, cars.get(key) + "-");
            }
            System.out.println(key + " : " + cars.get(key));
        }
        System.out.println();
    }

    public boolean enableForward() {
        int number = Randoms.pickNumberInRange(0, 9);
        if (number >= 4) {
            return true;
        }
        return false;
    }

    public void allRound() {
        while (racingRound > 0) {
            eachRound();
            racingRound--;
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
        System.out.print("최종 우승자 : ");
        List<String> winner = new ArrayList<>();
        for (String r : rank) {
            if (cars.get(rank.get(0)).length() == cars.get(r).length()) {
                winner.add(r);
                continue;
            }
            break;
        }
        String win = String.join(", ", winner);
        System.out.println(win);
    }

    public void run() {
        inputRacingCar();
        inputRacingRound();
        allRound();
        sortMap();
        printWinner();
    }
}
