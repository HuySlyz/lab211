/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Hashing {
    public static void main(String[] args) {
        System.out.println(getHash("password").equals(getHash("Password")));
    }
    
    public static String getHash(String input){
        byte[] byteInput = input.getBytes();
        String hashValue = "";
        try{
            //This MessageDigest class provides applications the functionality of a message digest algorithm, such as SHA-1 or SHA-256. 
            //message digest with sha-256 method
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //input is type array of byte -> convert from string to byte array
            md.update(byteInput);
            //digest method return an array of byte
            byte[] digestedBytes = md.digest();
            //convert result to string (uppercase)
            hashValue = DatatypeConverter.printHexBinary(digestedBytes);
        }catch(Exception e){
            e.printStackTrace();
        }
        return hashValue;
    }
}
