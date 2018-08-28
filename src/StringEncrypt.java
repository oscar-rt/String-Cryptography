
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
        
        if(text.length() > CryptoGUI.STRING_LIMIT){
            return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                    + "Max string size is around: " + CryptoGUI.STRING_LIMIT);
        }
        
        if(this.passkey == null || this.passkey.equals("")){
            return new OperationReport(false, "NO PASSKEY ENTERED", "Please enter a passkey.");
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
