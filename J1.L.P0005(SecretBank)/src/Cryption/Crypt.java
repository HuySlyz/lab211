/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cryption;

public class Crypt {
    
    //ascii char from 33 -> 127
    public static String encrypt(String str) {
        StringBuffer sb = new StringBuffer();
        int key = 2;
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            //char at 125 -> char at 33
            if (temp >= 127- key) {
                temp = (char) ( temp - 127 + 33 + key);
                System.out.println(Integer.valueOf(temp));
            }else{
                
            temp += key;
            }
            sb.append(temp);
        }
        return sb.toString();
    }
    public static String decrypt(String str) {
        StringBuffer sb = new StringBuffer();
        int key = 2;
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            //char at 33 -> 127
            if (temp <= 33 + key) { //35
                temp = (char) ( temp - 33 + 127 - key); //127
                System.out.println(Integer.valueOf(temp));
            }else{
                temp -= key;
            }
            sb.append(temp);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String result = encrypt("tr~");
        System.out.println(result);
    }
}

 