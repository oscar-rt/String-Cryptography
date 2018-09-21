
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;


/**
 *
 * @author Oscar
 */

//This class contains all the necessary information to encrypt or decrypt a 
//particular message

public class Cypher {
    
    private String passkey;
    
    public String openingBracket;
    public String closingBracket; //Brackets essentially referring to the surrounding hashes
    private final int HASH_STRING_SIZE = 43;
    
    public final char alphanum[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                    '0','1','2','3','4','5','6','7','8','9', ' ', '.'};
    public ArrayList<Character> cypher;
    
    public Cypher(String passkey){
        this.passkey = passkey;
        cypher = new ArrayList<>();
        getCypher();
    }

    private void getCypher(){
        
        try{
            //get first hash of passkey, that is the opening bracket
            //map any value not in the cypher to the cypher
            this.openingBracket = stringSha256ToBase64(this.passkey);
            String currHash = this.openingBracket;
            char[] currHashArray = currHash.toCharArray();
            
            for(int i = 0; i < currHashArray.length; i++){
                if(!cypher.contains(currHashArray[i])){
                    cypher.add(currHashArray[i]);
                }
            }
            
            //get second hash of passkey, that is the closing bracket
            //map any value not in the cypher to the cypher
            this.closingBracket = stringSha256ToBase64(this.openingBracket);
            currHash = this.closingBracket;
            currHashArray = currHash.toCharArray();
            
            for(int i = 0; i < currHashArray.length; i++){
                if(!cypher.contains(currHashArray[i])){
                    cypher.add(currHashArray[i]);
                }
            }

            //until the cypher is filled, keep generating hashes 
            //add any new characters not in hashes to the cypher

            while(cypher.size() < 64){
                currHash = stringSha256ToBase64(currHash);
                currHashArray = currHash.toCharArray();
                for(int i = 0; i < currHashArray.length && cypher.size() < 64; i++){
                    if(!cypher.contains(currHashArray[i])){
                        cypher.add(currHashArray[i]);
                    }
                }
            }
        } 
        catch(Exception e){
        }
    }
    
    
    public static String stringSha256ToBase64(String text) throws NoSuchAlgorithmException{
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        return Base64.getEncoder().encodeToString(digester.digest(text.getBytes())).replace("=", "");
    }
    
}
