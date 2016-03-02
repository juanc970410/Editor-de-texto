/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.wordprocessor.proxy;

import edu.eci.pdsw.wordprocessor.adaptor.Adaptor;
import static java.lang.reflect.Array.set;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.nlang.tools.CloudBasedChecker.dataprocessor.DefaultTypoDataProcessor;
import org.nlang.tools.CloudBasedChecker.dataprocessor.TypoDataProcessor;
import org.nlang.tools.CloudBasedChecker.datasource.CommunityLinguisticDataSource;
import org.nlang.tools.CloudBasedChecker.datasource.LinguisticDataSource;
import org.nlang.tools.CloudBasedChecker.fixer.TypoTouple;

/**
 *
 * @author Jairo Gonzalez Boada y Juan Camilo Herrera
 */
public class Proxy implements TypoDataProcessor{
    TypoDataProcessor data;
    HashMap<String, String> dic = new HashMap<String, String>();
    TypoTouple actual;

    public Proxy (TypoDataProcessor df){
        this.data = df;
        Set<TypoTouple> inf = new HashSet<>();
        inf = df.getRegisteredTypoTouples();
        Iterator itr = inf.iterator();
        while (itr.hasNext()){
            Object element = itr.next();
            actual = (TypoTouple) element;
            String mal = actual.getTypo();
            String bien = actual.getEquivalent();
            dic.put(mal,bien);
        }
        
    }
    @Override
    public Set<String> getRegisteredTypos() {
        return data.getRegisteredTypos();
    }

    @Override
    public Set<TypoTouple> getRegisteredTypoTouples() {
        return data.getRegisteredTypoTouples();
    }

    @Override
    public String getEquivalentWord(String string) {
        String correccion = null;
        if (dic.containsKey(string)){
            correccion = (String) dic.get(string);
        }
        return correccion;
    }
    
}
