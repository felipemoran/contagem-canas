package br.cefetmg.lsi.opencv.multipleObjectTracking.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader { 
  
    private Properties props;  
    private String nomeDoProperties = "multipleObjectTracking.properties";
  
    protected PropertiesLoader(){  
    	props = new Properties();
            
    	try{  
        	FileInputStream in = new FileInputStream(nomeDoProperties);
    		props.load(in);  
    		in.close();  
    	}
    	catch(IOException e){
    		System.err.println("Falha ao ler arquivo de propriedades.");
    		e.printStackTrace();
    	}
    	
    }
  
    protected String getValor(String chave){  
    	return (String)props.getProperty(chave);  
    }

    protected void setValor(String chave, String valor) {
        props.setProperty(chave, valor);

        try{
            FileOutputStream out = new FileOutputStream(nomeDoProperties);
            props.store(out, "");
            out.close();
        } catch(IOException e){
            System.err.println("Falha ao gravar o arquivo de propriedades");
        }
    }

}
