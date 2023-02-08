/* 
 * Дана прямоугольная карта размера MxN (N, M меньше 20)
 * На карте стоит фигура в точке(Х, Y), которая может ходить на одну клеточку вправо или вниз за один ход
 * Посчитать количество маршрутов, которым фигура может попасть в нижнюю правую клетку
 *  - На карте могут быть стены
 *  - M и N могут быть до 1000
 * Задачу разбить на методы
 */

package DZ;

public class DZ {
    static int mX = 1000, nY = 1000; // размер поля
    static int xPoint = 2, yPoint = 5; // начальная позиция фигуры
    static int deltaX = mX - xPoint, deltaY = nY - yPoint + 1; 
    static int[][] barrier = new int[deltaY][deltaX];

    public static void main(String[] args) {

        int numRaz = start(deltaX, deltaY);
        System.out.printf(
                "Размер карты %dx%d. Из точки (%d, %d) до нижней правой части поля\n %d маршрутов(та)", mX, nY,
                xPoint, yPoint, numRaz);
    }

    static int start(int dx, int dy) {
        int a = 0;
        for (int c = dx - 1; c >= 0; c--) {
            for (int s = 0; s < dy - 1; s++) {
                barrier[s][c] = 1;
                step(xPoint, yPoint, mX, nY);
                a++;
            }
        }
        return a;
    }

    // вывод массива в консоль
    static void stPrint(int dx, int dy) {
        for (int s = 0; s < dy; s++) {
            for (int c = 0; c < dx; c++) {
                System.out.print(barrier[s][c] + "\t");
            }
            System.out.println();
        }
    }

    static void step(int xp, int yp, int xEnd, int yEnd) {
        int xset = xp, yset = yp;
        int[] coord = new int[3];
        while ((xset != xEnd) || (yset != yEnd)) {
            if (coord[2] == 0) {
                coord = stepRigth(xset, yset, xEnd);
                xset = coord[0];
                yset = coord[1];
            }
            if (coord[2] == 1) {
                coord = stepDown(xset, yset, yEnd);
                xset = coord[0];
                yset = coord[1];
            }
            if (xset == xEnd && yset != yEnd) {
                coord = stepDown(xset, yset, yEnd);
                xset = coord[0];
                yset = coord[1];
            }
            coord[2] = 0;
        }
    }

    static int[] stepRigth(int x, int y, int xEnd) {
        int[] res = new int[] { x, y, 0 };
        if (x < xEnd) {
            if (stopX(x + 1, y) != 1) {
                res[0] = ++x;
            } else {
                res[2] = 1;
            }
        }

        return res;
    }

    static int[] stepDown(int x, int y, int yEnd) {
        int[] res = new int[] { x, y, 0 };
        if (y < yEnd) {
            res[1] = ++y;
        } else
            res[2] = 1;
        return res;
    }

    // преграды
    static int stopX(int x, int y) {
        int pointStop = 0;
        int indY = y - yPoint, indX = x - xPoint - 1;
        pointStop = barrier[indY][indX];
        return pointStop;
    }

}