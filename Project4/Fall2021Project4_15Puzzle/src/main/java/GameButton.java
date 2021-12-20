import javafx.scene.control.Button;
import java.util.*;

public class GameButton extends Button {
    public int index;
    public boolean check;

    GameButton(int index) {
        this.index = index;
        this.check = false;
    }

    public static Integer[] genNum() {
        Integer[] intArray = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);
        return intArray;
    }

    public static boolean isSolvable(Integer[] list) {
        if(list.length != 16) {
            System.err.println("isSolvable function works only" +
                    "with a list having 0-16 as values");
        }
        int inversionSum = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 0) {
                inversionSum += ((i / 4) + 1);
                continue;
            }

            int count = 0;
            for (int j = i + 1; j < list.length; j++) {
                if (list[j] == 0) {
                    continue;
                } else if (list[i] > list[j]) {
                    count++;
                }
            }
            inversionSum += count;
        }
        return (inversionSum & 1) == 0;
    }

    public void assignNum(int num) {
        String str = ""+num;
        this.setText(str);
    }

}
