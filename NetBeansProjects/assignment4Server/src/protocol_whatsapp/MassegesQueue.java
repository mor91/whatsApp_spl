/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author chen
 */
public class MassegesQueue extends RequestURI{
    private String _userName;
    

    public MassegesQueue(String _userName) {
        this._userName = _userName;
        
    }

    public String getUserName() {
        return _userName;
    }
    
    

}
