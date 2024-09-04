import java.util.Scanner;

// 亂加倍註冊是
public class Test {

    public static boolean checkWin(int n, int[] data, int[][] map) {
        boolean flag;

        for (int i = 0; i < 2 * n + 2; i++) {
            flag = true;
            int h = 0;
            while (h < data.length && data[h] != 0) {
                if (data[h] == map[i][h]) {
                    break;
                }
                h++;
            }
            if (h == data.length || data[h] != map[i][h]) {
                flag = false;
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public static void buildWinMap(int n, int[] data, int[][] map) {
        int[] temp = new int[50];
        int countT = 0;
        int countM = 0;

        // Vertical
        for (int i = 0; i < n * n; i++) {
            if (i % n == 0 && i != 0) {
                for (int k = 0; k < countT; k++) {
                    map[countM][k] = temp[k];
                }
                countM++;
                countT = 0;
                temp[countT] = data[i];
                countT++;
            } else if (i == n * n - 1) {
                temp[countT] = data[i];
                countT++;
                for (int k = 0; k < countT; k++) {
                    map[countM][k] = temp[k];
                }
                countM++;
            } else {
                temp[countT] = data[i];
                countT++;
            }
        }

        // Horizontal
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                map[countM][k] = map[k][i];
            }
            countM++;
        }

        // Diagonal
        for (int i = 0; i < n; i++) {
            map[countM][i] = map[i][i];
            map[countM + 1][i] = map[i][n - 1 - i];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] dataA = new int[50];
        int[] dataB = new int[50];
        int[] dataC = new int[50];

        for (int i = 0; i < n * n; i++) {
            dataA[i] = scanner.nextInt();
        }
        for (int i = 0; i < n * n; i++) {
            dataB[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            dataC[i] = scanner.nextInt();
        }

        int[][] winAmap = new int[50][50];
        int[][] winBmap = new int[50][50];
        boolean flagA = false;
        boolean flagB = false;

        buildWinMap(n, dataA, winAmap);
        buildWinMap(n, dataB, winBmap);

        for (int i = 0; i < m; i++) {
            int[] subC = new int[i + 1];
            System.arraycopy(dataC, 0, subC, 0, i + 1);

            flagA = checkWin(n, subC, winAmap);
            flagB = checkWin(n, subC, winBmap);

            if (flagA && flagB) {
                System.out.println("Tie");
                return;
            } else if (flagA) {
                System.out.println("A Win");
                return;
            } else if (flagB) {
                System.out.println("B Win");
                return;
            }
        }
        System.out.println("Tie");
        scanner.close();
    }
}
