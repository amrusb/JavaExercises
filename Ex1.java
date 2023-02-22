/**
 * Program dla kazdego wiersza macerzy oblicza wartosc minimalna
 * a dla kazdej kolumny wartosc maksymalna
 * @author Bartosz Surma
 * @version 11-12-2022
 */

import java.util.Arrays;
import java.util.Random;

public class Ex1{
    /*
     * Liczba wierszy macierzy
     */
    public final static int N = 4;
    /*
     * Liczba kolumn macierzy
     */
    public final static int M = 7;

    public static void main(String[] args) {
        var matrix = new int[N][M];
        var minInt = new int[N];
        var maxInt = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] =  new Random().nextInt(1000);
                System.out.printf("%-4d", matrix[i][j]);
            }
            System.out.println();
        }

        minInt = rowMin(matrix);
        maxInt = collumnMax(matrix);

        System.out.println("\nWartosc minimalna dla poszczegolnych wierszy:");
        int counter = 1;
        for (int i : minInt) {
            System.out.println(counter++ + ": " + i);
        }
        System.out.println("\nWartosc maksymalna dla poszczegolnych kolumn:");
        counter = 1;
        for (int i : maxInt) {
            System.out.println(counter++ + ": " + i);
        }

        System.out.println("\nWartosc maksymalna w tablicy minInt[]: " + findMax(minInt));
        System.out.println("Wartosc minimalna w tablicy maxInt[]: " + findMin(maxInt));
        findMedian(matrix);
    }

    /**
     * Szukanie minimum w poszczegolnych wierszach macierzy
     * @param m macierz zawierajaca liczby calkowite
     * @return tablica zawierajaca wartosci minimalne kazdego wiersza
    */
    public static int[] rowMin(int m[][]){
        var intArray = new int[N];
        for (int i = 0; i < N; i++) {
            int min = m[i][0];
            for (int item : m[i]) {
                if(item < min){
                    min = item;
                }
            }
            intArray[i] = min;
        }
        return intArray;
    }
    /**
     * Szukanie maksimum w poszczegolnych kolumnach macierzy
     * @param m macierz zawierajaca liczby calkowite
     * @return tablica zawierajaca wartosc maksymalna kazdej kolumny
     */
    public static int[] collumnMax(int m[][]) {
        var intArray = new int[M];
        for (int i = 0; i < M; i++) {
            int max = m[0][i];
            for (int j = 0; j < N; j++) {
                if(m[j][i] > max){
                    max = m[j][i];
                }
            }
            intArray[i] = max;
        }
        return intArray;
    }
    /**
     * Szukanie maksimum w tablicy jednowymiarowej
     * @param arr tablica zawierajaca liczby calkowite
     * @return wartosc kamsymalna tablicy jednowymiarowej
     */
    public static int findMax(int []arr) {
        int max = 0;

        for (int i : arr) {
            if(i > max) max = i;
        }
        return max;
    }
    /**
     * Szukanie minimum w tablicy jednowymiarowej
     * @param arr tablica jednowymiarowa, zawierajaca liczby calkowite
     * @return wartosc minilamna tablicy jednowymiarowej
     */
    public static int findMin(int []arr) {
        int min = 0;

        for (int i : arr) {
            if(i > min) min = i;
        }
        return min;
    }
    /**
     * Szukanie mediany dla kazdego wiersza macierzy
     * @param m macierz liczb calkowitych
     */
    public static void findMedian(int [][]m) {
        int counter = 1;
        for (int[] row : m) {
            Arrays.sort(row);
            System.out.printf("Mediana wiersza %d. wynosi: ", counter);
            if(row.length % 2 == 0){
                double median = (row[row.length/2] + row[row.length/2 - 1])/2.0;
                System.out.println(median);
            }   
            else{
                System.out.println(row[row.length/2]);
            }
        }
    }
}