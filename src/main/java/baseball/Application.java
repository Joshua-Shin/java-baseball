package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Operator op = new Operator();
        op.playGame();
    }
}

class Computer {
    private List<Integer> number;

    public void setNumber() {
        List<Integer> numberList = new ArrayList<>();
        while (numberList.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!numberList.contains(randomNumber)) {
                numberList.add(randomNumber);
            }
        }
        number = numberList;
    }

    public List<Integer> getNumber() {
        return number;
    }
}

class Player {
    private List<Integer> number;

    public void inputNumber() {
        List<Integer> numberList = new ArrayList<>();
        int num = Integer.valueOf(Console.readLine());
        while (num > 0) {
            numberList.add(num % 10);
            num /= 10;
        }
        Collections.reverse(numberList);
        number = numberList;
    }

    public List<Integer> getNumber() {
        return number;
    }
}

class Operator {
    Computer computer;
    Player player;
    private int ballCount;
    private int strikeCount;
    private boolean correctAnswer;

    public Operator() {
        computer = new Computer();
        player = new Player();
        ballCount = 0;
        strikeCount = 0;
        correctAnswer = false;
    }

    public void playGame() {
        showStartStatement();
        computer.setNumber();
        while (!correctAnswer) {
            player.inputNumber();
            compareNumber();
            showResult();
        }
    }

    public void compareNumber() {
        int ball = 0;
        int strike = 0;
        List<Integer> computerNumber = computer.getNumber();
        List<Integer> playerNumber = player.getNumber();
        for (int i = 0; i < computerNumber.size(); i++) {
            int index = playerNumber.indexOf(computerNumber.get(i));
            if (index != -1) {
                if (index == i) {
                    strike++;
                } else {
                    ball++;
                }
            }
        }
        ballCount = ball;
        strikeCount = strike;
        if (strikeCount == 3) {
            correctAnswer = true;
        }
    }

    public void showResult() {
        if (ballCount == 0 && strikeCount == 0) {
            System.out.println("낫싱");
        } else if (strikeCount == 3) {
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        } else {
            System.out.println(ballCount + "볼 " + strikeCount + "스트라이크");
        }
    }

    public void showStartStatement() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }
}