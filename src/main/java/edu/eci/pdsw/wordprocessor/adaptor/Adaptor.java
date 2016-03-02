/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.wordprocessor.adaptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.freelang.data.datareader.CloudDataReader;
import org.freelang.data.datareader.CloudReadingException;
import org.nlang.tools.CloudBasedChecker.datasource.LinguisticDataSource;
import org.nlang.tools.CloudBasedChecker.fixer.TypoTouple;

/**
 *
 * @author Jairo Gonzalez Boada y Juan Camilo Herrera
 */
public class Adaptor implements LinguisticDataSource{
    private CloudDataReader cloud;
    private final Set<TypoTouple> set = new HashSet<>();
    private TypoTouple tup;
    private List<String> list;
    private String [] a;
    public Adaptor(CloudDataReader cl){
        this.cloud = cl;
    }
    @Override
    public Set<TypoTouple> getData() {
        try {
            this.list = cloud.getData();
            for (int i = 0; i<list.size(); i++){
                a = (list.get(i).split("->")); 
                tup = new TypoTouple(a[0], a[1]);
                set.add(tup);
            }
        } catch (CloudReadingException ex) {
            Logger.getLogger(Adaptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }
    
    
}
