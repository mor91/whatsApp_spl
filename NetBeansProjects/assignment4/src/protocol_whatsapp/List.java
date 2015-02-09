/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;
import WhatsApp.User;
import WhatsApp.Group;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author chen
 */
public class List extends RequestURI{
    private String _listType;
    private boolean _isValidType=true;
    private Vector<String>  _responseMassegeBody;
    private String groupName;

    public List(String _listType, String groupName) {
        this._responseMassegeBody=new Vector<>();
        this._listType = _listType;
        this.groupName=groupName;
        
        switch(_listType){
            case "Users":
                break;
            case "Group":
                break;
            case "Groups":
                break;
            default: inValidType();
                break;
        }
        this._type="List";
                
    }
    private void inValidType(){
        _isValidType=false;
    }

    public String getListType() {
        return _listType;
    }

    public boolean isIsValidType() {
        return _isValidType;
    }

    public Vector<String> getResponseMassegeBody() {
        return _responseMassegeBody;
    }

    public String getGroupName() {
        return groupName;
    }
    
    public void massegeSuccessGroup(Group group){
        String str="";
        for (User user : group.getUsers()) {
           str+=user.getPhoneNumber()+",";
        }
        _responseMassegeBody.add(str.substring(0, str.length()-2));
        this.setCode("200");
    }
    public void massegeSuccessUsers(Map<String, User> users){
        for (Map.Entry<String, User> user: users.entrySet()) {
            _responseMassegeBody.add(user.getValue().getUserName()+"@"+user.getValue().getPhoneNumber());
        }
        this.setCode("200");
    }
    public void massegeSuccessGroups(Map<String,Group> groups){
        for (Map.Entry<String, Group> group : groups.entrySet()) {
            String str=group.getValue().getGroupName()+":";
            for (User user : group.getValue().getUsers()) {
                str+=user.getPhoneNumber()+",";
            }
            _responseMassegeBody.add(str.substring(0, str.length()-2));
        }
        this.setCode("200");
    }
    public void responseParameters(){
        this._responseMassegeBody.add("ERROR 273: mossing parameters");
        this.setCode("405");
    }
}
