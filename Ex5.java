/**
 * Program konwertuje wyrazy w taki sposób, że wszystkie litery alfabetu są
 * zamieniane na duże, a następnie do ich numeru ASCII dodawana jest wartość 1
 * Umozliwia takze cofniecie kowertowania
 * @author Bartosz Surma
 * @version 1-03-2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

class Converter{
    private Stack<char[]> words;
    private String fileName;
    private boolean isConverted;

    public Converter(){
        words = new Stack<char[]>();
        fileName = "";
        isConverted = false;
    }
    
    /**
     * @param fName Nazwa pliku zawierajacego wyrazy
     */
    public Converter(String fName){
        words = new Stack<char[]>();
        fileName = fName;
        isConverted = false;
        try{
            File wordsFile = new File(fileName);
            Scanner input = new Scanner(wordsFile);
            String buff;
            while(input.hasNextLine()){
                buff = input.nextLine();
                char[] wordBuff = new char[buff.length()];
                for (int i = 0; i < wordBuff.length; i++) {
                    wordBuff[i] = buff.charAt(i);
                }
                words.push(wordBuff);
            }
            input.close();
        }catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
        
    }
    /**
     * Metoda zapisuje zawarosc pola words do pliku
     */
    public void writeStack(){
        Iterator<char[]> it = words.iterator();
        try{
            FileWriter output = new FileWriter(fileName);
            while(it.hasNext()){
                output.write(it.next());
                output.write("\n");
            }
            System.out.println("File saved...");
            output.close();
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Metoda wypisuje zawartosc pola words w konsoli
     */
    public void printStack(){
        if(isConverted ==  true) System.out.println("The list of converted words:");
        else System.out.println("The list of unconverted words:");

        int i = 1;
        for (char[] word : words) {
            System.out.print(i++ + ": ");            
            System.out.println(word);
        }
        System.out.println();
    }

    /**
     * Metoda konwertuje wyrazy
     */
    public void convertStack(){
        if(isConverted == false){
            isConverted = true;
            Iterator<char[]> it = words.iterator();
            while(it.hasNext()){
                convertWord(it.next());
            }
        }
        else{
            System.out.println("Ths file was already converted.");
        }
    }
    private char[] convertWord(char[] word){
        for (int i = 0; i < word.length; i++) {
            if(Character.isAlphabetic(word[i])){
                if(Character.isLowerCase(word[i]))  word[i] = Character.toUpperCase(word[i]);
                if(word[i] == 'Z') word[i] = 'A';
                else word[i]++;
            }
            else{
                System.out.println("ERROR");
                System.exit(-1);
            }
        }
        return word;
    }

    public void unconvertStack(){
        if(isConverted == true){
            isConverted = false;
            Iterator<char[]> it = this.words.iterator();
            while(it.hasNext()){
                unconvertWord(it.next());
            }
        }
        else{
            System.out.println("This file wasn't converted yet.");
        }
    }
    
    private char[] unconvertWord(char[] word){
        for(int i = 0; i < word.length; i++){
            if(Character.isAlphabetic(word[i])){
                if(Character.isUpperCase(word[i]))  word[i] = Character.toLowerCase(word[i]);
                if(word[i] == 'a') word[i] = 'z';
                else word[i]--;
            }
            else{
                System.out.println("ERROR");
                System.exit(-1);
            }
        }
        return word;
    }
}

public class Ex5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name of the file with words:\t");
        String fname = input.nextLine();
        input.close();

        var Conv = new Converter(fname);
        Conv.unconvertStack();
        Conv.convertStack();

        Conv.printStack();
        Conv.writeStack();
        Conv.convertStack();
        Conv.unconvertStack();
        Conv.printStack();
        Conv.writeStack();
    }
}
