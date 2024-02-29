package org.example;

import java.util.Random;
import java.util.Scanner;

public class FiveByFive {
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (isCheckWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (isCheckWin(DOT_0)) {
                System.out.println("Победил искуственный интелект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }

    //проверка заполнены ли все поля
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    //выявление победителя
    public static boolean isCheckWin(char dot) {
        int countI, countJ, countD1, countD2, countd1_ab,countd2_ab,countd1_und,countd2_und;
        for (int i = 0; i < SIZE; i++) {
            countI = 0; countD1 = 0; countd1_ab=0; countd1_und=0;
            countJ = 0; countD2 = 0; countd2_ab=0; countd2_und=0;

            for (int j = 0; j < SIZE-3; j++) {
                if (map[i][j] == dot&&map[i][j+1]==dot&&map[i][j+2]==dot&&map[i][j+3]==dot) countI++;
                if (map[j][i] == dot&&map[j+1][i]==dot&&map[j+2][i]==dot&&map[j+3][i]==dot) countJ++;
                if (map[j][j] == dot&&map[j+1][j+1]==dot&&map[j+2][j+2]==dot&&map[j+3][j+3]==dot) countD1++;
                if (map[j][SIZE - 1 - j] == dot&&map[j+1][SIZE-1-(j+1)]==dot&&map[j+2][SIZE-1-(j+2)]==dot&&map[j+3][SIZE-1-(j+3)]==dot) countD2++;
                if (countD1 >0 || countD2 >0 || countI >0 || countJ >0)
                    return true;
            }
            for (int j = 0; j < SIZE-1; j++) {
                if (map[j][j+1]==dot) countd1_ab++;
                if (map[j+1][j]==dot) countd1_und++;
                if (map[SIZE-2-j][j]==dot) countd2_ab++;
                if (map[SIZE-1-j][j+1]==dot) countd2_und++;
                if (countd1_ab==DOTS_TO_WIN|| countd1_und==DOTS_TO_WIN|| countd2_ab==DOTS_TO_WIN|| countd2_und==DOTS_TO_WIN)
                    return true;
            }

        }
        return false;
    }

    //ход компьютера
    public static void aiTurn() {
        int x, y; int n;
        int kx;
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            n=-1;
            kx=0;
            //проверка строк
            for (int j = 0; j < SIZE-2; j++) {
                if (map[i][j] == DOT_X&&map[i][j]==map[i][j+1]&&map[i][j]==map[i][j+2]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[i][3]==DOT_EMPTY){
                                map[i][3]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (3 + 1) + " " + (i + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[i][4]==DOT_EMPTY) {
                                map[i][4]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (4 + 1) + " " + (i + 1));
                                count++;
                            }
                            else if (map[i][0]==DOT_EMPTY) {
                                map[i][0]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (0 + 1) + " " + (i + 1));
                                count++;
                            }
                            break;
                        case 2:
                            if (map[i][1]==DOT_EMPTY) {
                                map[i][1]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1 + 1) + " " + (i + 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[i][j] == DOT_X&&map[i][j]==map[i][j+1]&&map[i][j]==map[i][j+3]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[i][2]==DOT_EMPTY){
                                map[i][2]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (2 + 1) + " " + (i + 1));
                                count++;
                            }

                            break;
                        case 1:
                            if (map[i][3]==DOT_EMPTY) {
                                map[i][3]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (3 + 1) + " " + (i + 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[i][j] == DOT_X&&map[i][j]==map[i][j+2]&&map[i][j]==map[i][j+3]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[i][1]==DOT_EMPTY){
                                map[i][1]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1 + 1) + " " + (i + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[i][2]==DOT_EMPTY) {
                                map[i][2]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (2 + 1) + " " + (i + 1));
                                count++;
                            }
                            break;
                    }
                }
            }


            //проверка столбцов
            n=-1;
            kx=0;
            for (int j = 0; j < SIZE-2; j++) {
                if (map[j][i] == DOT_X&&map[j][i]==map[j+1][i]&&map[j][i]==map[j+2][i]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[3][i]==DOT_EMPTY){
                                map[3][i]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i+1) + " " + (3 + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[4][i]==DOT_EMPTY) {
                                map[4][i]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (4 + 1));
                                count++;
                            }
                            else if (map[0][i]==DOT_EMPTY) {
                                map[0][i]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (0 + 1));
                                count++;
                            }
                            break;
                        case 2:
                            if (map[1][i]==DOT_EMPTY) {
                                map[1][i]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (1+ 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[j][i] == DOT_X&&map[j][i]==map[j+1][i]&&map[j][i]==map[j+3][i]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[2][i]==DOT_EMPTY){
                                map[2][i]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (2 + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[3][i]==DOT_EMPTY) {
                                map[3][i]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (3 + 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[j][i] == DOT_X&&map[j][i]==map[j+2][i]&&map[j][i]==map[j+3][i]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[1][j]==DOT_EMPTY){
                                map[1][j]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (1 + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[2][j]==DOT_EMPTY) {
                                map[2][j]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (i + 1) + " " + (2 + 1));
                                count++;
                            }
                            break;
                    }
                }
            }


//            // провеока главной диагонали
            n=-1;
            kx=0;
            for (int j = 0; j < SIZE-2; j++) {
                if (map[j][j] == DOT_X&&map[j][j]==map[j+1][j+1]&&map[j][j]==map[j+2][j+2]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[3][3]==DOT_EMPTY){
                                map[3][3]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (3+1) + " " + (3 + 1));
                                count++;
                            }

                            break;
                        case 1:
                            if (map[4][4]==DOT_EMPTY) {
                                map[4][4]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (4 + 1) + " " + (4 + 1));
                                count++;
                            }
                            else if (map[0][0]==DOT_EMPTY) {
                                map[0][0]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (0 + 1) + " " + (0 + 1));
                                count++;
                            }
                            break;
                        case 2:
                            if (map[1][1]==DOT_EMPTY) {
                                map[1][1]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1+ 1) + " " + (1+ 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[j][j] == DOT_X&&map[j][j]==map[j+1][j+1]&&map[j][j+1]==map[j+3][j+1]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[2][2]==DOT_EMPTY){
                                map[2][2]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (2 + 1) + " " + (2+ 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[3][3]==DOT_EMPTY) {
                                map[3][3]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (3 + 1) + " " + (3 + 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[j][j] == DOT_X&&map[j][j]==map[j+2][j+2]&&map[j][j]==map[j+3][j+3]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[1][1]==DOT_EMPTY){
                                map[1][1]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1 + 1) + " " + (1 + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[2][2]==DOT_EMPTY) {
                                map[2][2]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (2 + 1) + " " + (2 + 1));
                                count++;
                            }
                            break;
                    }
                }
            }

//            проверка побочной диагонали
            n=-1;
            kx=0;
            for (int j = 0; j < SIZE-2; j++) {
                if (map[j][SIZE-1-j] == DOT_X&&map[j][SIZE-1-j]==map[j+1][SIZE-1-(j+1)]&&map[j][j]==map[j+2][SIZE-1-(j+2)]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[3][1]==DOT_EMPTY){
                                map[3][1]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1+1) + " " + (3+ 1));
                                count++;
                            }

                            break;
                        case 1:
                            if (map[4][0]==DOT_EMPTY) {
                                map[4][0]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (0 + 1) + " " + (4 + 1));
                                count++;
                            }
                            else if (map[0][4]==DOT_EMPTY) {
                                map[0][4]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (4 + 1) + " " + (0 + 1));
                                count++;
                            }
                            break;
                        case 2:
                            if (map[1][3]==DOT_EMPTY) {
                                map[1][3]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1+ 1) + " " + (3+ 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[j][SIZE-1-j] == DOT_X&&map[j][SIZE-1-j]==map[j+1][SIZE-1-(j+1)]&&map[j][SIZE-1-(j+1)]==map[j+3][SIZE-1-(j+3)]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[2][2]==DOT_EMPTY){
                                map[2][2]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (2 + 1) + " " + (2+ 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[3][1]==DOT_EMPTY) {
                                map[3][1]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (1 + 1) + " " + (3 + 1));
                                count++;
                            }
                            break;
                    }
                }
            }
            kx=0;
            for (int j = 0; j < SIZE-3; j++) {
                if (map[j][SIZE-1-j] == DOT_X&&map[j][SIZE-1-j]==map[j+2][SIZE-1-(j+2)]&&map[j][j]==map[j+3][SIZE-1-(j+3)]){ kx++; n=j;}
                if (kx==1){
                    switch (n){
                        case 0:
                            if (map[1][3]==DOT_EMPTY){
                                map[1][3]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (3+ 1) + " " + (1 + 1));
                                count++;
                            }
                            break;
                        case 1:
                            if (map[2][2]==DOT_EMPTY) {
                                map[2][2]=DOT_0;
                                System.out.println("Компьютер походил в точку " + (2 + 1) + " " + (2 + 1));
                                count++;
                            }
                            break;
                    }
                }
            }
        }

        // проверка диагоналей над главной и под главной

        // прокерка диагоналей над второстепенной и под второстеппеной
        if (count == 0) {
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_0;
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
        map[y][x] = DOT_X;
    }

    //проверка истиности введенных данных и проверка занята ячейка или нет
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
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
