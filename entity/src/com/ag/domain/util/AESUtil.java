package com.ag.domain.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.picketbox.commons.cipher.Base64;


public class AESUtil {
	
	static String IV = "z35al_@0}@5y~QJP";
	
	public static byte[] encryptBytes(String plainText, String encryptionKey) throws Exception {
		 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		 SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		 cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		 return cipher.doFinal(plainText.getBytes("UTF-8"));
	 }
	  
	 public static String decryptBytes(byte[] cipherText, String encryptionKey) throws Exception{
		 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		 SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
		 cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
		 return new String(cipher.doFinal(cipherText),"UTF-8");
	 }
	 
	 public static String encrypt(String plainText, String encryptionKey) throws Exception {
		return new String(Base64.encodeBytes(encryptBytes( plainText,  encryptionKey)));
	 }
	 
	 public static String decrypt(String plainText, String encryptionKey) throws Exception {
		return decryptBytes( Base64.decode(plainText),  encryptionKey);
	 }
	 
/*	 public static void main(String[] args) throws Exception {
		System.out.println(decrypt("7F/49WgcfFpHOSiGQh3ewiJj/uZ+r4sF7s2N5yAEqvREJ3uPla0YMKZrtZSKskl7isxiuG7UxGabP6orWHLRAZGn7XcqlrYlvsFO5ECij5Y=", "wXf;7-*!i)&d7TCM"));
	}*/
}
