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
public class User {
    private String _userName;
    private String _phoneNumber;
    private Queue _massegesQueue;

    public User(String _userName, String _phoneNumber) {
        this._userName = _userName;
        this._phoneNumber = _phoneNumber;
        this._massegesQueue =new LinkedList();
    }

    public String getUserName() {
        return _userName;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
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
