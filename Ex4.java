/**
 * Program konwertuje podana przez uzytkownika liczbe w zapisie oktagonalnym na liczbe w zapisie decymalnym
 * @author Bartosz Surma
 * @version 25-02-2023
 */
import java.util.*;

class OctNum{
    int val;
    String dec;

    public OctNum(){
        val = 0;
        dec = "NULL";
    }
    public OctNum(int n, String d){
        val = n;
        dec = d;
    }
    public OctNum(char[] octNum, int n){
        getVal(octNum, n);
    }
    /**Metoda konwertuje podana przez uzytkownika liczbe w zapisie oktagonalnym na liczbe w zapisie decymalnym,
     * a takze konwertuje int na String
     * @param octNum tablica zawierajaca liczbe w zapisie oktagonalnym
     * @param n dlugosc tablicy octNum
     */
    public void getVal(char[] octNum, int n){
        if(!checkIfOct(octNum, n)){
            System.out.println("ERROR: Podany ciag znakow nie jest liczba w zapisie oktagonalnym.");
            System.exit(-1);
        }
        val = oct2Dec(octNum, n);
        dec = decInt2String(val);
    }
    /**Metoda konwertuje liczbe w zapisie oktagonalnym na liczbe w zapisie decymalnym
     * @param octNum tablica zawierajaca liczbe w zapisie oktagonalnym
     * @param n dlugosc tablicy octNum
     * @return liczba w zapisie decymalnym
     */
    public int oct2Dec(char[] octNum, int n){
        int tempVal = 0;
        double pos = 0;
        for (int i = n - 1; i >= 0; i--) {
            tempVal += Character.getNumericValue(octNum[i])* Math.pow(8.0, pos);
            pos++;
        }
        return tempVal;
    }
    /** Metoda konwertuje liczbe typu int na napis reprezentujacy te wartosc w systemie decymalnym
     * @param decVal wartosc liczby w systemie decymalnym
     * @return  napis reprezentujacy wartosc decVal
     */
    public String decInt2String(int decVal){
        String reverseDec = "", tempDec = "";
        while(decVal != 0){
            int digit = decVal % 10;
            decVal /= 10;
            reverseDec += Integer.toString(digit);
        }

        for (int i = reverseDec.length() - 1; i >= 0; i--) {
            tempDec += reverseDec.charAt(i);
        }
        return tempDec;
    }
    /** Metoda sprawdza czy podana tablica zawiera znaki typu char z zakresu '0' - '7'
     * @param octNum tablica typu char zawierajaca znaki podane przez uzytkownika
     * @param n dlugosc tablicy octNum ustalona przez uzytkownika
     * @return true jesli zawiera znaki z zakresu '0' - '7' i false w przeciwnym przypadku
     */
    public boolean checkIfOct(char[] octNum, int n){
        for(int i = n - 1; i >= 0; i--){
            if(Character.isDigit(octNum[i])){
                if(octNum[i] >= '0' && octNum[i] <= '7') continue;
                else return false;
            }
            else return false;
        }
        return true;
    }
    /**
     * Metoda wypisuje zawartosc pola val i dec obiektu
    */
    public void printNum(){
        System.out.println();
        System.out.println("Wprowadzona liczba w systemie decymalnym:");
        System.out.println("\tint: "+ val);
        System.out.println("\tString: " + dec);
    }
}

public class Ex4{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Podaj dlugosc liczby w zapisie oktagonalnym:\t");
        int n = input.nextInt();
        var octNumber = new char[n];
        System.out.print("Podaj liczbe w zapisie oktagonalnym o dlugosci " + n + "\t");
        String temp = input.next();
        if(temp.length() > n){
            System.out.println("Dlugosc podanej liczby jest wieksza od zadeklarowanej liczby. Jej wartosc zostanie obcieta do " + n + " pierwszych cyfr.");
        }
        else if(temp.length() < n){
            n = temp.length();
        }
        for (int i = 0; i < n; i++) {
            octNumber[i] = temp.charAt(i); 
        }
        var num = new OctNum(octNumber, n);
        num.printNum();
        input.close();
        System.out.println("Koniec programu.");
    }
}