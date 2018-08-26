
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    public StringEncrypt(String text, String passkey){
        this.text = text;
        this.passkey = passkey;
    }
    
    public OperationReport encrypt(){
        
        if(text.length() > CryptoGUI.STRING_LIMIT - CryptoGUI.HEADER_LENGTH){
            return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                    + "Max string size: " + (CryptoGUI.STRING_LIMIT - CryptoGUI.HEADER_LENGTH));
        }
        
        if(this.passkey == null){
            
        }
        else{
            
        }
        
        return null;
    }
    
    public String getEncryptedString(){
        
        return this.encryptedString;
    }
    
    public static String fileSha256ToBase64(String text) throws NoSuchAlgorithmException{
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        return Base64.getEncoder().encodeToString(digester.digest(text.getBytes()));
    }
}
