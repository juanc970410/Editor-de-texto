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
package edu.eci.pdsw.wordprocessor.langproc;

import edu.eci.pdsw.wordprocessor.langproc.typos.EnglishTyposDataSource;
import edu.eci.pdsw.wordprocessor.langproc.typos.SpanishTyposDataSource;
import java.util.Stack;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author hcadavid
 */
public class AutoReplaceDocumentFilter extends DocumentFilter {

    private Document document;
    private EnglishTyposDataSource tds;
    private String area;
    private int position;
    
    
    private static final int TEXT_LENGTH_AREA = 20;

    public AutoReplaceDocumentFilter(Document document, EnglishTyposDataSource se) {
        this.document = document;
        this.tds = se;
    }
    
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {    
        position = offset;
        
        if (!text.trim().equals("")) {
                    int p = Math.max(offset - TEXT_LENGTH_AREA, 0);
                    int q = Math.min(document.getLength() - offset, TEXT_LENGTH_AREA);

                    String prefix = document.getText(p, offset - p);
                    String suffix = document.getText(offset, q);
                    area = prefix + text + suffix;
                    
                    //System.out.println("prefiijo:  "+prefix+"  Sufijo:  "+suffix+" texto:  "+text);
                    
                    int lp = prefix.length();
                    while (lp > 0 && !isDelim(prefix.charAt(lp - 1))) {
                        lp--;
                    }
                    
                    int p1 = lp;
                    int ls = 0;
                    while (ls < suffix.length() && lp>0 &&  !isDelim(prefix.charAt(lp - 1))) {
                        ls++;
                    }

                    int q1 = ls;

                    String wPrefix = prefix.substring(p1);
                    String wSuffix = suffix.substring(0, q1);
                    String word = wPrefix + text + wSuffix;
                    
                    String mRep = tds.check(word);

                    if (mRep != null) {
                        String wRep = mRep;
                        fb.replace(offset - wPrefix.length(), word.length() - text.length(), wRep, attrs);
                    } else {
                        super.replace(fb, offset, length, text, attrs);
                    }
                } else {
                    super.replace(fb, offset, length, text, attrs);
                }
    }
    public boolean isDelim(char c) {
        boolean result;
        switch (c) {
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                
                result = true;
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
    
}
