/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.wordprocessor.langproc.style;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author hcadavid
 */
public class EnglishStyleAutoCorrector {

	final static LinkedHashMap<String,String> equivalencesMap;
        
        static{		
		equivalencesMap=new LinkedHashMap<>();		
		
		equivalencesMap.put("bro", "brother");
		equivalencesMap.put("mom", "mother");
		equivalencesMap.put("how yo doin", "how are you doing");
				
	}

    
    public static String checkAndReplace(String text){
        String output=text;
        for (Map.Entry<String,String> entry:equivalencesMap.entrySet()){
            output=output.replaceAll("\\b"+entry.getKey()+"\\b",entry.getValue());
        }
        return output;
    }
    

}
