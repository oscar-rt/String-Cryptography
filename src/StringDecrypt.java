
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
    
    private final char alphanum[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                    '0','1','2','3','4','5','6','7','8','9', ' ', '.'};
    private ArrayList<Character> cypher;
    
    public StringDecrypt(String text, String passkey){
        this.text = text;
        this.passkey = passkey;
    }
    
    public OperationReport decrypt() throws NoSuchAlgorithmException{
        
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

            try {
            if((text.length() + (HASH_STRING_SIZE * 2)) > CryptoGUI.STRING_LIMIT){
                return new OperationReport(false, "TEXT STRING TOO BIG", "Input a smaller string. \n\n"
                        + "Max string size is: " + text.length() + (HASH_STRING_SIZE * 2));
            }
            
            cypher = new ArrayList<>();
            String hash = stringSha256ToBase64(this.passkey);
            this.openingBracket = hash;
            char[] hashA = hash.toCharArray();
            
            while(cypher.size() < 64){
                if(!cypher.isEmpty()){
                    hash = stringSha256ToBase64(hash);
                    hashA = hash.toCharArray();
                    if(cypher.size() == 1){
                        this.closingBracket = hash;
                    }
                }
                for(int i = 0; i < hashA.length; i++){
                    if(!cypher.contains(hashA[i])){
                        cypher.add(hashA[i]);
                    }
                }
            }
            
            HashMap<Character, Character> mapToSymbols = new HashMap<>();
            for(int i = 0; i < cypher.size(); i++){
                mapToSymbols.put(cypher.get(i), this.alphanum[i]);
            }
            
            Pattern pattern = Pattern.compile(this.openingBracket + "(.*?)" + this.closingBracket);
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
            
            } catch (NoSuchAlgorithmException ex) {
            }
            
            return new OperationReport(true, "", "");
        }
    }
    
    public String getDecryptedString(){
        
        return this.decryptedString;
    }
    
    public static String stringSha256ToBase64(String text) throws NoSuchAlgorithmException{
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        return Base64.getEncoder().encodeToString(digester.digest(text.getBytes())).replace("=", "");
    }
}
