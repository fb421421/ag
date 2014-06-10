package com.ag.domain.util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.picketbox.commons.cipher.Base64;

public class RSAUtils {
	
	private static  final  String privateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQlwUfUnLMMuXTh2j3GRQKUMz7" +
			"PU1KvlSCvRrZwh3F9KbK9VvZeEDW5OMmXGXSx9qDsCYm76/+XgPUty8otbr9/qrZPI/Y+ls1jzwz" +
			"fFQIID1QE8IT4tYBU+ZLry/2wHGtiTagywA7T3ghGAMwdHV0/l6FbQm6wHu/dRAbm7E+0zn4XfRt" +
			"EbVDd23q3nfCxqiMbkGPQtsKaPUBbbB3zd38qpR+16QCxqAG+FHuDUIb2qnJ7o4T9+bWhm0h7T4v" +
			"uUHyCEFmxjhxdLh4F915bVc5mQzi6nDyNelqC9llNM4r1rN7aCNPJaBMdLckxKeYqvHwO98mLD+q" +
			"/EiIU2yoUNyvAgMBAAECggEBAIi0cKBjqKxONyOe9Gwj5uG18YhdNlXDzE1sFBXsxY+jN4vPHY9o" +
			"dg13jRh45cC7OmulwPyxQ/nA0+hrZggcgnahMNGBxBOG6XOjQG0BUMX6DV5HwAhjQKkytRu7wCKR" +
			"YymLAP5X0JlDErZfaKyIf4Ek+yM1q6xJTvb+7ELeGl/lkui6KvcywVuL6mKZaYnQyls/CQUHhso/" +
			"8SobN/BRJrEn3h9dF+HyZFqb/k9Kw7vSP9RZicUIWl98KUEE6HZIM24rH+0MI/jP9Qrfn26XJh7G" +
			"tKKq4nBXh91aMcGfpdCImEO/8SPW0eIqVdOifOsit5PEl8D5ORUeglOD5q/udKECgYEA0RrdRUaf" +
			"Ym553e966fG+3EutykzClDbrsGveC/w3JwOXUQFwbik8TiI+3EIzDSGoTQf7dBdAktnf2nnkp3TM" +
			"YEVvFLHXt9PIJT8w+X5uh26h4ezhhBc+TTzh93shTZaGNNyWWR/fJw7m/F/tPzz4L/M/Nfe2uufR" +
			"U7vIZFYYHb8CgYEAsQQ394pwMH1fZ6PRiKv/nNb3owQlcTqGnrI1E2UOH41irItssRvUPUonyZn7" +
			"u+OTrEYqDuZmwhAmLezzTSOffMTktM9bP1rfSjHSaNHSlMEIuqcswMzcx33UsuUAQ6OHe0bOKrr8" +
			"do0HxRkII/iX6ZhLG4sKuSkmwK8mkwU33RECgYBV6uPgAxSaLSqFtlCar2nsumu+I6wPmkFdS/UA" +
			"l7FRpngWILFBkk/lCs0pU0oGqz2r6Qu4t5UgwcTo0AzTgXo4VE9AAZLRRYOHSl+lI/3mR97cJajD" +
			"zYkb01hFh6I3bxHVxo1AMYhPVCA0snWY9kRD736Zfh7mzTmFXjMIhxeBoQKBgQCHbn/XX06YesNs" +
			"itR8KYQGnh2W1kxDRhDLB/M9pHpav5R4CLAfDPij78FjJ/GGfgZ0rrXs8nA6F5ruVjSLly6ligql" +
			"H2Rjo4XkwRuHYMkpUEr6a378/4AHDQmX2UcLeOjl83U3EhqoJRyFurIUu1SWvxLvvsB0i7x/fUIZ" +
			"B8wG0QKBgFzw01k3vfvVbZMUb4LQXXBnV2Q3WHFHSYt2mLuK60BjY0NfWudvuKKk8G9H7DGuasVh" +
			"xLC6zerpPSOzmJymCgV00WC6hfH0pn4vPf5bJJNIsKZyYgncS28KkJvsczgJRfLXEq3szm0R4PXg" +
			"sj8bVuz8l9us3uUankTPE6hHdX+O"; 

	
	public static String decrypt(String encryptedData){
		String decryptedData=null;
		try {
			// get the private key
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			KeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
	  	    PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		
			Cipher rsaCipher = Cipher.getInstance("RSA");
			rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
			
			byte[] utf8=rsaCipher.doFinal(Base64.decode(encryptedData));
			
			decryptedData = (new String(utf8,"UTF-8"));
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} 
		
		return decryptedData;
	}
	
	
	public static void main(String[] args) {
		/*私钥转der工具openssl pkcs8 -topk8 -nocrypt -inform pem -in private.pem  -outform der  -out  private.der*/
		String password="SW0RmHX4HT9kqrGaIOoonKnbXiuGTQnvDxM6+uXYWoWp0tp7RNuLi+f1k0EYB4TRnYR1pVKcm+VOKZJdVSEMU0CwrtXKGX0mcac6Z9pz64RTmFi+6YEyocESYZypkwWc/92zhI6g6eZoWclzCG3mZ/dg/r8PPp8S/iE3WTZhfcjQvuTxRr6S0jkymJntDqcsd/oR4JVTL82BMiN229UHe9CWD44CeGaCaJSGQjm7GGuYZihGSSJk/1UUdh/Ty9NhynqdeV5Ylls9DeFaP+CAuEwms/37OSD8GotXIBmRz5yDcPVjEtNkTSP4DXGT3a3R6oTLq64wMzTe0mfbI/giRA==";
		System.out.println(decrypt(password));
	}
}
