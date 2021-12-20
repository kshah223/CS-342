import javafx.application.Platform;

public class GameLogic extends JavaFXTemplate{

    public static int findRow(int index) { return (index / 4); }

    public static int findCol(int index) { return (index % 4); }

    public static int findEmptyCoordinates(Integer[] numbers) {
        for (int i = 0; i < 16; i++) {
            if (numbers[i] == 0) {
                System.out.println("Index is: " + i);
                emptyRow = findRow(i);
                emptyCol = findCol(i);
                return i;
            }
        }
        return -1;
    }

    public static boolean checkRight(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        return zeroCoord != 3 && zeroCoord != 7 && zeroCoord != 11 && zeroCoord != 15;
    }

    public static boolean checkLeft(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        return zeroCoord != 0 && zeroCoord != 4 && zeroCoord != 8 && zeroCoord != 12;
    }

    public static boolean checkUp(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        return zeroCoord != 0 && zeroCoord != 1 && zeroCoord != 2 && zeroCoord != 3;
    }

    public static boolean checkDown(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        return zeroCoord != 12 && zeroCoord != 13 && zeroCoord != 14 && zeroCoord != 15;
    }

    public static boolean moveToRight(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        if(checkRight(numbers, index)) {
            if(index < 16 && index > -1 && index-1 == zeroCoord) {
                moveZero(numbers, zeroCoord, (zeroCoord+1));
            }
        }
        if(isWon(numbers)) {
            primStage.setScene(winScene());
            primStage.show();
            return true;
        } else {
            return false;
        }
    }

    public static boolean moveToLeft(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        if(checkLeft(numbers, index)) {
            if(index < 16 && index > -1 && index+1 == zeroCoord) {
                moveZero(numbers, zeroCoord, (zeroCoord-1));
            }
        }

        if(isWon(numbers)) {
            primStage.setScene(winScene());
            primStage.show();
            return true;
        } else {
            return false;
        }
    }

    public static boolean moveToUp(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        if(checkUp(numbers, index)) {
            if(index < 16 && index > -1 && index+4 == zeroCoord) {
                moveZero(numbers, zeroCoord, (zeroCoord-4));
            }
        }
        if(isWon(numbers)) {
            primStage.setScene(winScene());
            primStage.show();
            return true;
        } else {
            return false;
        }
    }

    public static boolean moveToDown(Integer[] numbers, int index) {
        int zeroCoord = findEmptyCoordinates(numbers);
        if(checkDown(numbers, index)) {
            if(index < 16 && index > -1 && index-4 == zeroCoord) {
                moveZero(numbers, zeroCoord, (zeroCoord+4));
            }
        }
        if(isWon(numbers)) {
            primStage.setScene(winScene());
            primStage.show();
            return true;
        } else {
            return false;
        }
    }

    public static void moveZero(Integer[] numbers, int zeroIndex, int moveToIndex){
        String temp2 = arr[moveToIndex].getText();
        arr[moveToIndex].setText(arr[zeroIndex].getText());
        arr[zeroIndex].setText(temp2);
        int temp = numbers[moveToIndex];
        numbers[moveToIndex] = numbers[zeroIndex];
        numbers[zeroIndex] = temp;
    }

    public static void changeBoard(Integer[] newNumbers) {
        for (int j = 0; j < 16; j++) {
            String temp = intToString(newNumbers, j);
            numbers[j] = newNumbers[j];
            if (remake) {
                int finalJ = j;
                Platform.runLater(() -> {
                    if (newNumbers[finalJ] != 0) {
                        arr[finalJ].setText(temp);
                    } else {
                        arr[finalJ].setText("");
                    }
                });
            }
        }
        if(isWon(numbers) || isWon(newNumbers)) {
            primStage.setScene(winScene());
            primStage.show();
        }
    }

    public static String intToString(Integer[] newNumbers, int j) {
        return newNumbers[j].toString();
    }

    public static boolean makeMove(Integer[] numbers, int index) {
        return moveToUp(numbers, index) || moveToDown(numbers, index) || moveToLeft(numbers, index) || moveToRight(numbers, index);
    }

    public static boolean isWon(Integer[] puzzleArray) {
        final Integer[] win = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        for (int i = 0; i < 16; i++) {
            if (!puzzleArray[i].equals(win[i])) {
                return false;
            }
        }
        return true;
    }

}
