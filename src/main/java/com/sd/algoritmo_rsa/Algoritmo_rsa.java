/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sd.algoritmo_rsa;

import java.util.Scanner;

/**
 *
 * @author natha
 */
public class Algoritmo_rsa {
    
    public static void main(String[] args) {
        RSA rsa = new RSA();
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.println("O que você deseja fazer?");
            System.out.println("1 - Criptografar uma mensagem");
            System.out.println("2 - Descriptografar uma mensagem");
            System.out.println("3 - Fechar programa");
            int op = scanner.nextInt(); 
            scanner.nextLine();
            switch(op){
                case 1 -> {
                    System.out.println("Digite a mensagem:");
                    String msg = scanner.nextLine();
                    byte[] encrypted = rsa.encrypt(msg.getBytes());
                    System.out.println("Texto criptografado (Hex): " + RSA.bytesToHexString(encrypted));
                }
                
                case 2 -> {
                    System.out.println("Digite a mensagem criptografada (Hex):");
                    String msg = scanner.nextLine();
                    byte[] decrypted = rsa.decrypt(RSA.hexStringToBytes(msg));
                    System.out.println("Texto descriptografado: " + new String(decrypted));
                }
                case 3 -> {
                    return;
                } 
            }
        }
    }
}
