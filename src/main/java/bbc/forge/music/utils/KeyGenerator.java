package bbc.forge.music.utils;


public class KeyGenerator {
	
	private static int KEY_LENGTH=5;

	
	public Long get(){
		
		return System.currentTimeMillis()+1;
		
/*		long m=System.currentTimeMillis()+1;
		String hex=Long.toHexString(m);
		return hex;
*/		
	}

	public String getHex(Long key){
		
		return Long.toHexString(key);
		
	}
	
	public Long getLong(String key){
		
		return Long.parseLong(key,16);
	
	}
	
	public String getUrlKey(Long counter){
			
		String[] chars="gjfbw9d6xp3m2nq58v4crhz".trim().split("|");
		int base=chars.length;
		
		String urlkey="";
		int index=0;
		int remainder=0;
		
		while (urlkey.length()<KEY_LENGTH){
			remainder=(int) (counter%base);
			index=(index + urlkey.length() + remainder)%base;
			urlkey+=chars[index];
		}
					
        return urlkey;		
		
	}
	
}