
import java.util.ArrayList;


/**
 *
 * @author Oscar
 */

//This class contains all the necessary information to encrypt or decrypt a 
//particular message

public class Cypher {
    
    private char operation; //'E' = encrypt, 'D' = decrypt
    private String passkey;
    
    private String openingBracket;
    private String closingBracket; //Brackets essentially referring to the surrounding hashes
    private final int HASH_STRING_SIZE = 43;
    
    private final char alphanum[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                    'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                    '0','1','2','3','4','5','6','7','8','9', ' ', '.'};
    private ArrayList<Character> cypher;
    
    public Cypher(char operation, String passkey){
        this.operation = operation;
        this.passkey = passkey;
    }
    
    
    
}
