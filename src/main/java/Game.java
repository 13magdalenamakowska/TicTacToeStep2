import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        questionNewGame();

    }

    private static void questionNewGame() {
        getMessage("Jeśli chcesz przerwać grę kółko i krzyżyk wpisz N (No) i naciśnij enter.");

        Scanner scannerNewGame = new Scanner(System.in);
        String newGame = scannerNewGame.nextLine();

        if (newGame.equalsIgnoreCase("n")){
            getMessage("koniec gry");
        } else {
            startNewGame();
        }
    }

    private static void getMessage(String message) {
        System.out.println(message);
    }

    private static void startNewGame() {
        Scanner scannerNamePlayer = new Scanner(System.in);

        String nameFirstPlayer = setNamePlayer(scannerNamePlayer, "Gracz 1 - wpisz swoje imie i naciśnij enter.");
        String nameSecondPlayer = setNamePlayer(scannerNamePlayer, "Gracz 2 - Wpisz swoje imie i naciśnij enter.");
        System.out.println("Super! " + nameFirstPlayer + ", " + nameSecondPlayer + " zagrajcie w kółko i krzyżyk.");
        System.out.println();

        char[][] area = placeForSign();

        showBoard(area);

        playersMoveInSequence(nameFirstPlayer, nameSecondPlayer, area);
    }

    private static void playersMoveInSequence(String nameFirstPlayer, String nameSecondPlayer, char[][] area) {
        int i = 1;
        while (i <= 9) {

            Scanner scanner = new Scanner(System.in);

            char firstUser = 'x';
            char secondUser = 'o';

            if (firstUser == 'x') {
                char sign = 'x';
                int place =0;
                System.out.println(nameFirstPlayer + ", w którym miejscu chcesz wpisać znak x?");
                System.out.println();
                sign = playerChooseFieldInBoard(area, scanner, sign);
                showWherePutSign(area);
                firstUser = secondUser;
                if (checkResult(area, sign)) break;
            }
            if (secondUser == 'o') {
                char sign = 'o';
                int place=0;
                System.out.println(nameSecondPlayer + ", w którym miejscu chcesz wpisać znak - o?");
                System.out.println();
                sign = playerChooseFieldInBoard(area, scanner, sign);
                showWherePutSign(area);
                secondUser = firstUser;
                if (checkResult(area, sign)) break;
            }
        }
    }
    
    private static void showBoard(char[][] area) {
        System.out.println("1" + area[0][0] + "|" + " 2" + area[0][1] + "|" + " 3" + area[0][2]);
        System.out.println("---------");
        System.out.println("4" + area[1][0] + "|" + " 5" + area[1][1] + "|" + " 6" + area[1][2]);
        System.out.println("---------");
        System.out.println("7" + area[2][0] + "|" + " 8" + area[2][1] + "|" + " 9" + area[2][2]);
        System.out.println();
        getMessage("(Wybierz pole oznaczone cyfrą od 1 do 9. Wpisz wybraną cyfrę i naciśnij enter.) \n");
    }

    private static char[][] placeForSign() {
        char[][] area = new char[3][3];

        area [0][0] = ' ';
        area [0][1] = ' ';
        area [0][2] = ' ';
        area [1][0] = ' ';
        area [1][1] = ' ';
        area [1][2] = ' ';
        area [2][0] = ' ';
        area [2][1] = ' ';
        area [2][2] = ' ';
        return area;
    }

    private static String setNamePlayer(Scanner scanner2, String s) {
        System.out.println(s);
        System.out.println();

        String firstName;
        firstName = scanner2.nextLine();
        System.out.println();
        return firstName;
    }

    private static void showWherePutSign(char[][] area) {
        System.out.println(area[0][0] + "|" + area[0][1] + "|" + area[0][2]);
        System.out.println("------");
        System.out.println(area[1][0] + "|" + area[1][1] + "|" + area[1][2]);
        System.out.println("------");
        System.out.println(area[2][0] + "|" + area[2][1] + "|" + area[2][2]);
        System.out.println();
    }

    private static char playerChooseFieldInBoard(char[][] area, Scanner scanner, char sign) {
        int x = 0;
        int y = 0;
        int place =0;
        try {
            place = scanner.nextInt();
            System.out.println();
        } catch (InputMismatchException inputMismatchException) {
            getMessage("Niepoprawne współrzędne. Wpisz cyfrę z zakresu 0-9\n");
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            getMessage("Niepoprawne współrzędne. Wpisz cyfrę z zakresu 0-9\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sign = signPutToArea(area, sign, place);
        }
        return sign;
    }

    private static char signPutToArea(char[][] area, char sign, int place) {
        int x = 0;
        int y = 0;
        if (place == 1) {
            x = 0;
            y = 0;
        } else if (place == 2) {
            x = 0;
            y = 1;
        } else if (place == 3) {
            x = 0;
            y = 2;
        } else if (place == 4) {
            x = 1;
            y = 0;
        } else if (place == 5) {
            x = 1;
            y = 1;
        } else if (place == 6) {
            x = 1;
            y = 2;
        } else if (place == 7) {
            x = 2;
            y = 0;
        } else if (place == 8) {
            x = 2;
            y = 1;
        } else if (place == 9) {
            x = 2;
            y = 2;
        }
        if (area[x][y] != ' ') {
            getMessage("To pole jest już zajęte. Wpisz znak w inne miejsce \n");

        } else {
            area[x][y] = sign;
        }

        return sign;
    }

    private static boolean checkResult(char[][] area, char sign) {
        if ((area[0][0] == area[0][1] && area[0][1] == area[0][2] && area[0][2] != ' ') ||
                (area[1][0] == area[1][1] && area[1][1] == area[1][2] && area[1][2] != ' ') ||
                (area[2][0] == area[2][1] && area[2][1] == area[2][2] && area[2][2] != ' ') ||
                (area[0][0] == area[1][0] && area[1][0] == area[2][0] && area[2][0] != ' ') ||
                (area[0][1] == area[1][1] && area[1][1] == area[2][1] && area[2][1] != ' ') ||
                (area[0][2] == area[1][2] && area[1][2] == area[2][2] && area[2][2] != ' ') ||
                (area[0][0] == area[1][1] && area[1][1] == area[2][2] && area[2][2] != ' ') ||
                (area[0][2] == area[1][1] && area[1][1] == area[2][0] && area[2][0] != ' ')) {
            getMessage("Wygrał " + sign + "\n");
            questionNewGame();
            return true;
        }
        if (area[0][0] != ' ' && area[0][1] != ' ' && area[0][2] != ' ' &&
                area[1][0] != ' ' && area[1][1] != ' ' && area[1][2] != ' ' &&
                area[2][0] != ' ' && area[2][1] != ' ' && area[2][2] != ' ') {
            getMessage("Koniec gry. Remis. \n");
            questionNewGame();
            return true;
        }
        return false;
    }
}
