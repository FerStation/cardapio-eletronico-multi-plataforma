package PacoteLogico;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografarMD5 {
	
	public String criptografarMD5(String senha){
		
		String senhaCriptografada = null;
		
		try  {    
			
		    MessageDigest md = MessageDigest.getInstance( "MD5" );  
		      
		    md.update( senha.getBytes() );  
		    BigInteger hash = new BigInteger( 1, md.digest() );  
		    senhaCriptografada = hash.toString( 16 );    
		}  
		  
		catch(NoSuchAlgorithmException ns)  {  
		    ns.printStackTrace();  
		}
		
		return senhaCriptografada;
		
	}
	

}
