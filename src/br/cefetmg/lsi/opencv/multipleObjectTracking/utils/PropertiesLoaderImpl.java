package br.cefetmg.lsi.opencv.multipleObjectTracking.utils;


public class PropertiesLoaderImpl {
	private static PropertiesLoader loader = new PropertiesLoader();
    
	public static String getValor(String chave){  
    	return (String)loader.getValor(chave);  
	}

    public static void setValor(String chave, String valor) { loader.setValor(chave, valor); }
}
