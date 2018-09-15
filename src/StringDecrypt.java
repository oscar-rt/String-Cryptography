
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * test2
 */

/**
 *
 * @author Oscar
 */
public class StringDecrypt {
    
    String decryptedString;
    private String text;
    private String passkey;
    private String closingBrackets; //Brackets essentially referring to the surrounding hashes
    private final int HASH_STRING_SIZE = 43;
    
    private final char alphanum[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                    '0','1','2','3','4','5','6','7','8','9', ' ', '.'};
    private ArrayList<Character> cypher;
    
    public StringDecrypt(String text, String passkey){
        this.text = text;
        this.passkey = passkey;
    }
    
    public OperationReport decrypt() throws NoSuchAlgorithmException{
        
        if(this.passkey == null || this.passkey.equals("")){
            return new OperationReport(false, "NO PASSKEY ENTERED", "Please enter a passkey.");
        }
        else{
            if(text != null && !text.equals("")){
                
            }
            else{
                return new OperationReport(false, "TEXT STRING EMPTY", "Input text to decrypt.");
            }
        }
        
        return null;
    }
    
    public String getDecryptedString(){
        
        return this.decryptedString;
    }
}
