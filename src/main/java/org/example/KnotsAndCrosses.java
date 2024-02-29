package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class KnotsAndCrosses {

  public static final int SIZE = 3;
  public static final int DOTS_TO_WIN = 3;
  public static final char DOT_EMPTY = '•';
  public static final char DOT_X = 'X';
  public static final char DOT_0 = '0';
  public static char[][] map;
  public static Scanner sc = new Scanner(System.in);
  public static Random rand = new Random();

  public static void main(String[] args) {
    while (true) {
      initMap();
      printMap();
      while (true) {
        humanTurn();
        printMap();
        if (isCheckWin(DOT_X, "Победил человек")) {
          break;
        }
        aiTurn();
        printMap();
        if (isCheckWin(DOT_0, "Победил искуственный интелект")) {
          break;
        }
      }
      System.out.print("Желаете сыграть еще раз? (Y - да): ");
      if (!sc.next().equalsIgnoreCase("Y")) {
        break;
      }
    }

  }

  //проверка заполнены ли все поля
  public static boolean isMapFull() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (map[i][j] == DOT_EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  //выявление победителя
  public static boolean isCheckWin(char dot, String s) {
    int countI, countJ, countD1, countD2;
    for (int i = 0; i < SIZE; i++) {
      countI = 0;
      countJ = 0;
      countD1 = 0;
      countD2 = 0;
      for (int j = 0; j < SIZE; j++) {
        if (map[i][j] == dot) {
          countI++;
        }
        if (map[j][i] == dot) {
          countJ++;
        }
        if (map[j][j] == dot) {
          countD1++;
        }
        if (map[j][SIZE - 1 - j] == dot) {
          countD2++;
        }
        if (countD1 == DOTS_TO_WIN || countD2 == DOTS_TO_WIN || countI == DOTS_TO_WIN || countJ == DOTS_TO_WIN) {
          System.out.println(s);
          return true;
        } else if (isMapFull()) {
          System.out.println("Ничья");
          return true;
        }
      }
    }
    return false;
  }

  //ход компьютера
  public static void aiTurn() {
    int x, y;
    int kx, k;
    int count = 0;
    for (int i = 0; i < SIZE; i++) {
      k = 0;
      kx = 0;
      x = -1;
      y = -1;
      //проверка строк
      for (int j = 0; j < SIZE; j++) {
        if (map[i][j] == DOT_X) {
          kx++;
        } else {
          if (map[i][j] == DOT_EMPTY) {
            x = i;
            y = j;
            k++;
          }
        }
      }
      if (kx == DOTS_TO_WIN - 1 && k == 1) {
        map[x][y] = DOT_0;
        count++;
        System.out.println("Компьютер походил в точку " + (y + 1) + " " + (x + 1));
        break;
      }
      k = 0;
      kx = 0;
      x = -1;
      y = -1;
      //проверка столбцов
      for (int j = 0; j < SIZE; j++) {
        if (map[j][i] == DOT_X) {
          kx++;
        } else {
          if (map[j][i] == DOT_EMPTY) {
            x = j;
            y = i;
            k++;
          }
        }
      }
      if (kx == DOTS_TO_WIN - 1 && k == 1) {
        map[x][y] = DOT_0;
        count++;
        System.out.println("Компьютер походил в точку " + (y + 1) + " " + (x + 1));
        break;
      }
      // провеока главной диагонали
      k = 0;
      kx = 0;
      x = -1;
      y = -1;
      for (int j = 0; j < SIZE; j++) {
        if (map[j][j] == DOT_X) {
          kx++;
        } else {
          if (map[j][j] == DOT_EMPTY) {
            x = j;
            y = j;
            k++;
          }
        }
      }
      if (kx == DOTS_TO_WIN - 1 && k == 1) {
        map[x][y] = DOT_0;
        count++;
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        break;
      }
//            проверка побочной диагонали
      k = 0;
      kx = 0;
      x = -1;
      y = -1;
      for (int j = 0; j < SIZE; j++) {
        if (map[j][SIZE - 1 - j] == DOT_X) {
          kx++;
        } else {
          if (map[j][SIZE - 1 - j] == DOT_EMPTY) {
            x = j;
            y = SIZE - 1 - j;
            k++;
          }
        }
      }
      if (kx == DOTS_TO_WIN - 1 && k == 1) {
        map[x][y] = DOT_0;
        count++;
        System.out.println("Компьютер походил в точку " + (y + 1) + " " + (x + 1));
        break;
      }
    }
    if (count == 0) {
      do {
        x = rand.nextInt(SIZE);
        y = rand.nextInt(SIZE);
      } while (!isCellValid(x, y));
      System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
      map[x][y] = DOT_0;
    }
  }

  //ход человека
  public static void humanTurn() {
    int x, y;
    do {
      System.out.println("Введите координаты в формате X Y");
      x = sc.nextInt() - 1;
      y = sc.nextInt() - 1;
    } while (!isCellValid(x, y));
    map[x][y] = DOT_X;
  }

  //проверка истинности введенных данных и проверка занята ячейка или нет
  public static boolean isCellValid(int x,
                                    int y) {
    if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
      return false;
    }
    if (map[x][y] == DOT_EMPTY) {
      return true;
    }
    return false;
  }

  //печать игры
  public static void printMap() {
    for (int i = 0; i <= SIZE; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (int i = 0; i < SIZE; i++) {
      System.out.print((i + 1) + " ");
      for (int j = 0; j < SIZE; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  //инициализация массива
  public static void initMap() {
    map = new char[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        map[i][j] = DOT_EMPTY;
      }
    }
  }

}