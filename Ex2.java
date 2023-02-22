/**
 * Program szyfruje slowo podane przez uzytkownika
 * @author Bartosz Surma
 * @version 12-12-2022
 */
import java.util.Scanner;

public class Ex2 {
    /**
     * Rozmiar tablicy
     */
    final static int N = 5;
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        System.out.print("Podaj slowo do zaszyfrowania:\t");
        String word = stdIn.nextLine();
        stdIn.close();
        String codedWord = encrypt(word);

        System.out.println("Zaszyfrowane slowo:\t"+ codedWord);
    }

    /**
     * Metoda szyfruje slwo
     * @param word oryginalne slowo
     * @return zaszyfrowane slowo
     */
    public static String encrypt(String word) {
        var A = new char[N][N];
        char filler = 'a';
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = filler++;
                if(filler == 'v') filler++;
            }
        }

        System.out.println("A:");
        printMatrix(A);

        var B = cloneMatrix(A);
        var temp = B[N-1];
        for (int i = N-1; i > 0; i--) {
            B[i] = B[i-1];
        }
        B[0] =  temp;

        System.out.println("B:");
        printMatrix(B);
        String encryption = "";
        for (int i = 0; i < word.length(); i++) {
            searching:
            for (int j = 0; j < N; j++) {
                for (int j2 = 0; j2 < N; j2++) {
                    if(word.charAt(i) == A[j][j2]){
                        encryption+= B[j][j2];
                        break searching;
                    }        
                }
            }    
        }

        return encryption;
    }
    /**
     * Metoda kopiuje wartosci z macierzy x do drugiej macierzy
     * @param x macierz znakow typu char
     * @return macierz przekopiowanych wartosci z macierzy x
     */
    public static char[][] cloneMatrix(char[][]x) {
        var cloneX = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cloneX[i][j] =  x[i][j];
            }
        }

        return cloneX;
    }
    /**
     * Wypisuje sformatowana zawartosc macierzy zawierajacej znaki typu char
     * @param x macierz char
     */
    public static void printMatrix(char[][]x) {
        for (char[] cs : x) {
            for (char c : cs) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
