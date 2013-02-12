package bbc.forge.music.utils;

public class HexUtils {
	
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
	
	public Long getLong(String key) throws Exception{
		Long l;
		
		try  {
			l = Long.parseLong(key,16);
		} catch (NumberFormatException nfe) {
			return 0l;
		}	   
		return l;	   
	
	}
	
}
