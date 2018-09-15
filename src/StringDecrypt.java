
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
            ArrayList<Character> character = new ArrayList<>();
            String baseHash = null;
            while(character.size() < 64){
                if(character.isEmpty()){
                    baseHash = StringEncrypt.stringSha256ToBase64(passkey);
                }
                else{
                    baseHash = StringEncrypt.stringSha256ToBase64(baseHash);
                }
                char[] currCypher = baseHash.toCharArray();
                for(int i = 0; i < currCypher.length; i++){
                    if(!character.contains(currCypher[i])){
                        character.add(currCypher[i]);
                    }
                }
            }
            String concat = "";
            
            
                
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
