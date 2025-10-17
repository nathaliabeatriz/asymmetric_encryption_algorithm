package com.sd.algoritmo_rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    // chave pública: expoente e, chave privada: expoente d, módulo: n
    private BigInteger n, d, e;
    private final int bitLength = 1024; // Tamanho da chave em bits

    // Gera um novo par de chaves (pública e privada) sempre que um objeto RSA é instanciado
    public RSA() {
        SecureRandom r = new SecureRandom();

        // Gerar dois números primos grandes, p e q.
        // A certeza de 100 garante uma probabilidade extremamente alta de que os números são realmente primos.
        BigInteger p = new BigInteger(bitLength / 2, 100, r);
        BigInteger q = new BigInteger(bitLength / 2, 100, r);

        // Calcular n = p * q
        n = p.multiply(q);

        // Contar quantidade de coprimos de n, menores que n
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Escolher um expoente 'e' tal que 1 < e < phi(n) e 'e' seja coprimo de phi(n)
        // 65537 é um valor comum para 'e' por ser primo e eficiente para exponenciação
        e = new BigInteger("65537");
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(new BigInteger("2")); // Garante que 'e' e 'phi' são coprimos
        }

        // Calcular 'd', o inverso multiplicativo modular de 'e' em relação a phi.
        // d é a chave privada que satisfaz a equação (d * e) % phi = 1
        d = e.modInverse(phi);
    }

    // Criptografa a mensagem fornecida usando a chave pública (e, n).
    public byte[] encrypt(byte[] message) {
        // Fórmula da criptografia: C = m^e mod n
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }

    // Descriptografa a mensagem fornecida usando a chave privada (d, n).
    public byte[] decrypt(byte[] encryptedMessage) {
        // Fórmula da descriptografia: M = c^d mod n
        return (new BigInteger(encryptedMessage)).modPow(d, n).toByteArray();
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
    
    public static byte[] hexStringToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}