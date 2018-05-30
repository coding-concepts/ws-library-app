/*
 * @(#)HashingServiceImpl.java 1.0 Mar 02, 2018
 */
package com.bar.service.impl;

import com.bar.service.HashingService;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;


import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * <code>HashingServiceImpl</code> class is
 * <p>
 * <p>
 * <pre>
 * <strong>History</strong>    Name              Date            Description
 * <strong>History</strong>    --------------------------------------------------------------------
 * <strong>History</strong>   Pratyush Giri    3/2/18
 * </pre>
 *
 * @author Pratyush Giri
 * @since Mar 02, 2018
 */
public class HashingServiceImpl implements HashingService {




    private static int BUFFER_SIZE = 1024;
    private static final Random RANDOM = new SecureRandom();


    public static void main(String[] args){
        byte[] salt1 = getNextSalt();
        byte[] salt2 = getNextSalt2();

        System.out.println(new String(Hex.encode(salt1)));
        System.out.println(new String(Hex.encode(salt2)));

    }

    @Override
    public String getHash(String algorithmName, byte[] text) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithmName);
        byte[] hash = digest.digest(text);
       // System.out.println(hash);
        String hashToReturn = new String(Hex.encode(hash));
        return hashToReturn;
    }


    @Override
    public String getHash(String algorithm, byte[] text, byte[] salt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hashedBytes = digest.digest(text);
        return  new String(Hex.encode(hashedBytes));
    }

    @Override
    public String[] getHashAndSalt(String algorithm, byte[] text) throws Exception {
        byte[] salt = getNextSalt();
        String hash =  getHash(algorithm, text, salt);
        String[] saltAndHash = new String[2];
        saltAndHash[0] = new String(Hex.encode(salt));
        saltAndHash[1] = hash;
        return saltAndHash;
    }

    @Override
    public String[] getHashAndSalt(String text) throws Exception {
        return new String[0];
    }

    @Override
    public boolean isValid(String algorithm, String plainPassword, String salt, String hashedPassword) {
        return  isValid( algorithm, plainPassword.getBytes(), salt.getBytes(),hashedPassword.getBytes());
    }


    public boolean isValid(String algorithm, byte[] plainPassword, byte[] salt, byte[] hashedPassword) {
        String newHash ="";
        try {
            if (salt == null) {
                newHash = getHash(algorithm, plainPassword);
            } else {
                newHash = getHash(algorithm, plainPassword, salt);
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }




        return  Arrays.areEqual(newHash.getBytes(), hashedPassword);
    }


    public static byte[] getNextSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    public static byte[] getNextSalt2() {
        byte[] salt = new byte[16];
        Random r = new Random();
        r.nextBytes(salt);
        return salt;
    }
}



