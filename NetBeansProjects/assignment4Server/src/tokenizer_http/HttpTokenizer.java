/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer_http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;

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
        Map<String,String> massegeMap=new HashMap<>();
        StringBuilder stringBuilder=new StringBuilder();
        int c;
        try {
            while((c=_in.read())!=-1){
                if(c=='\n'){
                    stringBuilder.append("\n");
                }
                if(c==_delimiter){
                    break;
                }  
                else stringBuilder.append((char)c);
            }
        } catch (IOException ex) {
            _isAlive=false;
            Logger.getLogger(HttpTokenizer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        String massege=stringBuilder.toString();
        String[] massegeBody=massege.split("\n\n");
        String[] lines=massegeBody[0].split("\n");
        massegeMap.put(lines[0].split(" ")[0],lines[0].split(" ")[1]);
        for(int i=1;i<lines.length;i++){
            massegeMap.put(lines[i].split(": ")[0], lines[i].split(": ")[1]);
        }
        massegeMap.put("massegeBody", massegeBody[1]);
        return massegeMap;
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
