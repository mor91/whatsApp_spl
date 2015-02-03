/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author chen
 */
public class Group {
    private String _groupName;
    private Vector<User> _users;

    public Group(String _groupName) {
        this._groupName = _groupName;
        this._users=new Vector<>();
    }
    public void addMember(User phoneNumber){
        _users.add(phoneNumber);
    }
    public void removeMember(User phoneNumber){
        _users.remove(phoneNumber);
    }
    public boolean containUser(User phoneNumber){
        if(_users.contains(phoneNumber)){
            return true;
        }
        else{
            return false;
        }
    } 
    
}

