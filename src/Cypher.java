
/**
 *
 * @author Oscar
 */

//This class contains all the necessary information to encrypt or decrypt a 
//particular message

public class Cypher {
    
    private char operation; //'E' = encrypt, 'D' = decrypt
    private String passkey;
    
    public Cypher(char operation, String passkey){
        this.operation = operation;
        this.passkey = passkey;
    }
    
}
