import java.util.Scanner;
import static java.lang.System.*;

class Game {
    //    just lists different sizes of ships that are included in the game
    static int shipSizes[][] = {{1}, {2, 2}, {3, 3, 3}, {4, 4, 4, 4}, {5, 5, 5, 5, 5}};
    //    keeps count of the shots fired throughout one game
    static int numOfShots = 0;
    static double score = 0;
    private static String lastTxtInput = "";

    private static void printArray2D(String array2D[][]) {
        for (String[] ints : array2D) {
            for (int i = 0; i < ints.length; i++) {
                if (i == (ints.length - 1)) {
                    out.println(ints[i]);
                } else {
                    out.print(ints[i] + " ");
                }
            }
        }
    }

    private static int tempGrid[][] = {{1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 2, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 4, 4, 4, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 5, 0},
            {0, 0, 0, 0, 3, 0, 5, 0},
            {0, 0, 0, 3, 0, 0, 5, 0},
            {0, 0, 3, 0, 0, 0, 5, 0}};

    static String showGrid[][] = {{"   ", " 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 "},
            {" 1 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 2 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 3 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 4 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 5 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 6 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 7 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "},
            {" 8 ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? ", " ? "}};

    private void hitTarget(int array[]) {
        numOfShots++;
        // coordinates -> [0] = x [1] = y
        int showGridX = array[0] + 1;
        int showGridY = array[1] + 1;

        switch (tempGrid[array[1]][array[0]]) {
            case 1:
                showGrid[showGridY][showGridX] = "(1)";
                out.println("\nBOAT DESTROYED");
                printArray2D(showGrid);
                score += 5;
                break;
            case 2:
                showGrid[showGridY][showGridX] = "(2)";
                out.println("\nHIT BOAT OF SIZE 2");
                printArray2D(showGrid);
                score += (double) 5 / 2;
                break;
            case 3:
                showGrid[showGridY][showGridX] = "(3)";
                out.println("\nHIT BOAT OF SIZE 3");
                printArray2D(showGrid);
                score += (double) 5 / 3;
                break;
            case 4:
                showGrid[showGridY][showGridX] = "(4)";
                out.println("\nHIT BOAT OF SIZE 4");
                printArray2D(showGrid);
                score += (double) 5 / 4;
                break;
            case 5:
                showGrid[showGridY][showGridX] = "(5)";
                out.println("\nHIT BOAT OF SIZE 5");
                printArray2D(showGrid);
                score += 1;
                break;
            default:
                showGrid[showGridY][showGridX] = " X ";
                out.println("\nSHOT MISSED");
                printArray2D(showGrid);
        }
    }

    private int[] getCoordinatesXY() {
        // coordinates -> [0] = x [1] = y
        int array[] = new int[2];
        boolean secondLoop = false;
        do {
            if (secondLoop)
                out.println("\nWrong coordinates :(");
            secondLoop = true;
            Scanner in = new Scanner(System.in);
            out.print("\n(range from 1 to 8)\nInput X-coordinate: ");
            array[0] = in.nextInt() - 1;
            out.print("Input Y-coordinate: ");
            array[1] = in.nextInt() - 1;
            in.nextLine();  // Consume newline left-over
        } while (array[0] < 0 || array[0] > 7 || array[1] < 0 || array[1] > 7);
        return array;
    }

    private float getScore() {
        if (score >= 25) {
            return (float) (score / numOfShots);
        }
        return (float) (score / (14 + numOfShots));
    }

    private void gameOn() {
        Scanner in = new Scanner(System.in);
        do {
            if (numOfShots > 0)
                out.println("\nWould you like to make another shot? ([yes]/[no])");
            else
                out.println("GAME IS ON, Would you like to make a shot? ([yes]/[no])");
            lastTxtInput = in.nextLine();
            if (lastTxtInput.equals("no"))
                break;
            hitTarget(getCoordinatesXY());
        } while (lastTxtInput.equals("yes"));
        out.println("\nGame ended!\n" + numOfShots + " shots fired in total" + "\n---- SCORE " + getScore() + " ---- highest: 1.6");
        //  resets count
        numOfShots = 0;
        //  closing Scanner
        in.close();
    }

    Game() {
        //  starts automatically!
        gameOn();
    }
}