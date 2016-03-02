/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.wordprocessor.langproc.typos;

import edu.eci.pdsw.wordprocessor.adaptor.Adaptor;
import edu.eci.pdsw.wordprocessor.proxy.Proxy;
import org.freelang.data.datareader.CloudDataReader;
import org.nlang.tools.CloudBasedChecker.dataprocessor.DefaultTypoDataProcessor;
import org.nlang.tools.CloudBasedChecker.dataprocessor.TypoDataProcessor;
import org.nlang.tools.CloudBasedChecker.datasource.CommunityLinguisticDataSource;
import org.nlang.tools.CloudBasedChecker.fixer.NoTypoFoundException;
import org.nlang.tools.CloudBasedChecker.fixer.TypoFixer;

/**
 *
 * @author hcadavid
 */
public class EnglishTyposDataSource {
    TypoDataProcessor td = null;
    public EnglishTyposDataSource(){
        td = new Proxy(new DefaultTypoDataProcessor(new Adaptor(new CloudDataReader())));
    }
	/**
	 * Obj: Verificar que la palabra ingresada este sujeta a correcciones, por ejemplo
	 * por un error tipico de digitacion identificado.	
	 * @param word
	 * @return la palabra correcta equivalente. Null si no hay ocurrencias.
	 */
	public String check(String word){
            
            //System.out.println(word);
            TypoFixer tf=new TypoFixer(td);
            
            try {
                String res=tf.checkAndFixTypo(word);
                return res;
            } catch (NoTypoFoundException ex) {
                return null;
            }
	}
	
	
}
