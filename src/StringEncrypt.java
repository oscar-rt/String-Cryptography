
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oscar
 */
public class StringEncrypt {
    
    private String encryptedString;
    private String text;
    private String passkey;
    private String closingBrackets; //Brackets essentially referring to the surrounding hashes
    private final int HASH_STRING_SIZE = 43;
    
    private final char alphanum[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                    '0','1','2','3','4','5','6','7','8','9', '+', '/'};
    
    public StringEncrypt(String text, String passkey){
        this.text = text;
        this.passkey = passkey;
    }
    
    public OperationReport encrypt(){
        
        if(text.length() > CryptoGUI.STRING_LIMIT){
            return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                    + "Max string size is: " + text.length() + (HASH_STRING_SIZE * 2));
        }
        if(text.length() == 0){
            return new OperationReport(false, "TEXT STRING EMPTY", "Input text to encrypt. \n\n"
                    + "Max string size is: " + text.length() + (HASH_STRING_SIZE * 2));
        }
        
        if(this.passkey == null || this.passkey.equals("")){
            return new OperationReport(false, "NO PASSKEY ENTERED", "Please enter a passkey.");
        }
        else{
            if((text.length() + (HASH_STRING_SIZE * 2)) > CryptoGUI.STRING_LIMIT){
                return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                    + "Max string size is: " + text.length() + (HASH_STRING_SIZE * 2));
            }
            
            
            
        }
        
        return null;
    }
    
    public String getEncryptedString(){
        
        return this.encryptedString;
    }
    
    public static String stringSha256ToBase64(String text) throws NoSuchAlgorithmException{
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        return Base64.getEncoder().encodeToString(digester.digest(text.getBytes())).replace("=", "");
    }
    
    private static char[] getCypher(){
        
        
        
        return null;
    }
}

