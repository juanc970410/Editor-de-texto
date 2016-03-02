/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.wordprocessor.command;

import javax.swing.JTextArea;

/**
 *
 * @author JuanHerrera
 */
public class CommandDelete implements Command{
    private JTextArea txt;
    private JTextArea textArea;
    private String texto;
    private String carac;
    
    
    public CommandDelete(JTextArea text){
        txt = text;
        
        texto = txt.getText();
        
    }
    
    @Override
    public void execute() {
       carac=txt.getText();
       txt.setText(texto);
       texto = carac;
    }

    @Override
    public void undo() {
        carac=txt.getText();
        txt.setText(texto);
        texto=carac;
    }
    
}
