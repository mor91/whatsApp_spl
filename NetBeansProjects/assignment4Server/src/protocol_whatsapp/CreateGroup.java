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
    private Vector<String> _responseMassegeBody;

    public CreateGroup(String _groupName) {
        this._groupName = _groupName;
        this._usersVector=new Vector<>();
        this._responseMassegeBody=new Vector<>();
        this._type="CreateGroup";
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
    public void responseParametes(){
        this._responseMassegeBody.add("ERROR 675: Cannot create group, missing parameters");
    }
    public void responseUnknownUser(String user){
        this._responseMassegeBody.add("ERROR 929: unknowen user "+user);
    }
    public void responseNameTaken(){
        this._responseMassegeBody.add("ERROR 511: Group Name Already Taken");
    }
    public void massegeSuccess(){
        this._responseMassegeBody.add("Group "+_groupName+" Created");
    }
    
}
