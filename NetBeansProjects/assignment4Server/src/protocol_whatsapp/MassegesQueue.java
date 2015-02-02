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
    private Queue _massegesQueue;

    public MassegesQueue(String _userName, Queue _massegesQueue) {
        this._userName = _userName;
        this._massegesQueue =new LinkedList();
    }
    
    public void addMassegeToQueue(String msg){
        _massegesQueue.add(msg);
    }
    public Queue getMassegesQueue(){
        Queue returnQueue= _massegesQueue;
        while(!_massegesQueue.isEmpty())
            _massegesQueue.remove();
        return returnQueue;
    }
}
