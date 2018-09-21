
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDecrypt {
    
    String decryptedString;
    private String text;
    private String passkey;
    private String openingBracket;
    private String closingBracket;//Brackets essentially referring to the surrounding hashes
    private final int HASH_STRING_SIZE = 43;
    
    private char alphanum[];
    private ArrayList<Character> cypher;
    
    public StringDecrypt(String text, String passkey){
        this.text = text;
        this.passkey = passkey;
    }
    
    public OperationReport decrypt(){
        
         if(text.length() > CryptoGUI.STRING_LIMIT){
            return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                    + "Max string size is: " + (CryptoGUI.STRING_LIMIT - (text.length() + (HASH_STRING_SIZE * 2))) );
        }
        if(text.length() == 0){
            return new OperationReport(false, "TEXT STRING EMPTY", "Input text to encrypt. \n\n"
                    + "Max string size is: " + (CryptoGUI.STRING_LIMIT - (text.length() + (HASH_STRING_SIZE * 2))) );
        }
        
        if(this.passkey == null || this.passkey.equals("")){
            return new OperationReport(false, "NO PASSKEY ENTERED", "Please enter a passkey.");
        }
        else{
            
            if((text.length() + (HASH_STRING_SIZE * 2)) > CryptoGUI.STRING_LIMIT){
                return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                        + "Max string size is: " + (CryptoGUI.STRING_LIMIT - (text.length() + (HASH_STRING_SIZE * 2))) );
            }
            //Cypher initialization
            Cypher cypherObject = new Cypher(this.passkey);
            this.cypher = cypherObject.cypher;
            this.openingBracket = cypherObject.openingBracket;
            this.closingBracket = cypherObject.closingBracket;
            this.alphanum = cypherObject.alphanum;

            //map all cypher characters to the alphabet and numbers
            HashMap<Character, Character> mapToSymbols = new HashMap<>();
            for(int i = 0; i < cypher.size(); i++){
                mapToSymbols.put(cypher.get(i), this.alphanum[i]);
            }
            
            //If message was found by hash brackets, then get that message
            Pattern pattern = Pattern.compile("\\Q" + this.openingBracket + "\\E" + 
                                    "(.*?)" + "\\Q" + this.closingBracket + "\\E");
            Matcher matcher = pattern.matcher(text);
            char[] textArray;
            if(matcher.find()){
                textArray = matcher.group(1).toCharArray();
            }
            else{
                textArray = this.text.toCharArray();
            }
            
            
            for(int i = 0; i < textArray.length; i++){
                try{
                    textArray[i] = mapToSymbols.get(textArray[i]);
                }
                catch(Exception e){
                }
            }
            String ftextString = new String(textArray);
            this.decryptedString = ftextString;
            
            return new OperationReport(true, "", "");
        }
    }
    
    public String getDecryptedString(){
        
        return this.decryptedString;
    }
}
