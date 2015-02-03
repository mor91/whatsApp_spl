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
public class CreateGroup extends RequestURI{
    private String _groupName;
    private Vector<String> _usersVector;

    public CreateGroup(String _groupName) {
        this._groupName = _groupName;
        this._usersVector=new Vector<>();
    }
    
    public void addUserToGroup(String userName){
        _usersVector.add(userName);
    }

    public String getGroupName() {
        return _groupName;
    }

    public Vector<String> getUsersVector() {
        return _usersVector;
    }
    
    
}
