/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer_http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chen
 */
public class HttpTokenizer<T> implements tokenizer.Tokenizer{
    private InputStreamReader _in;
    private char _delimiter;
    private boolean _isAlive;
    
    @Override
    public Object nextMessage() {
        StringBuilder stringBuilder=new StringBuilder();
        int c;
        try {
            while((c=_in.read())!=-1){
                if(c=='\n'){
                    
                }
                if(c==_delimiter){
                    break;
                }  
                else stringBuilder.append((char)c);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpTokenizer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    @Override
    public void addInputStream(InputStreamReader inputStreamReader) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
