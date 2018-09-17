
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringEncrypt {
    
    private String encryptedString;
    private String text;
    private String passkey;
    private String openingBracket;
    private String closingBracket; //Brackets essentially referring to the surrounding hashes
    private final int HASH_STRING_SIZE = 43;
    
    private final char alphanum[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                    '0','1','2','3','4','5','6','7','8','9', ' ', '.'};
    private ArrayList<Character> cypher;
    
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
                    mapToSymbols.put(this.alphanum[i], cypher.get(i));
                }
                
                char[] textArray = this.text.toCharArray(); 
                for(int i = 0; i < textArray.length; i++){
                    try{
                        textArray[i] = mapToSymbols.get(textArray[i]);
                    }
                    catch(Exception e){
                        
                    }
                }
                
                this.encryptedString = new String(textArray);
                encryptedRandomString();
                
            } catch (NoSuchAlgorithmException ex) {
            }
            
            return new OperationReport(true, "", "");
        }
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

    private void encryptedRandomString() {
        //surround our string with the padded encrypted string brackets
        String paddedES = openingBracket + encryptedString + closingBracket;
        
        //We get our hash-dependent values to personalize the string
        int sum = 0;
        for(char chara : cypher){
            sum += Character.getNumericValue(chara);
        }
        final int maxVal = 7808;
        
        double lPercent = (((double)sum) / maxVal);
        int finalLength = (int) (lPercent * CryptoGUI.STRING_LIMIT);
        
        if(finalLength < paddedES.length()){
            finalLength = paddedES.length();
        }
        
        int maneuverSpace = finalLength - paddedES.length();
        
        //if true, do some silly maths to add some more hash-dependent values to our string
        if(maneuverSpace != 0){
            
            int manSum = 0;
            for(int i = 0; i < cypher.size() && i < maneuverSpace; i++){
                char chara = cypher.get(i);
                int currCh = Character.getNumericValue(chara);
                if(i%2==0){
                    manSum += currCh * 2;
                }
                else{
                    manSum += currCh * 1;
                }
            }
            
            double mPercent = (((double) manSum)/2)/ maxVal;
            int mIndex = (int) (maneuverSpace * mPercent);
            
            boolean fillInES = false;
            char[] finalES = new char[finalLength];
            char[] paddedESCA = paddedES.toCharArray();
            Random rand = new Random();
            
            for(int i = 0; i < finalLength; i++){
                if(i == mIndex){
                    fillInES = true;
                };
                if(fillInES && i < (mIndex + paddedES.length())){
                    finalES[i] = paddedESCA[i - mIndex];
                }
                else{
                    rand = new Random();
                    int  n = rand.nextInt(63) + 0;
                    finalES[i] = cypher.get(n);
                }
            }
            
            paddedES = new String(finalES);
        }
        
        this.encryptedString = paddedES;
    }
}

