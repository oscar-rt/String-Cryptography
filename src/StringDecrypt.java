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
    
    public OperationReport decrypt(){
        
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
