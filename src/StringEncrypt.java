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
        
        return null;
    }
    
    public String getEncryptedString(){
        
        return this.encryptedString;
    }
}
