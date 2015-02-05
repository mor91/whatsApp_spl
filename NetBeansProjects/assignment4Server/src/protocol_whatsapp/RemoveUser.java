/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;

import java.util.Vector;

/**
 *
 * @author chen
 */
public class RemoveUser extends RequestURI{
    private String _tergetGroup;
    private String _userPhoneNumber;
    private Vector<String> _responseMassegeBody;

    public RemoveUser(String _tergetGroup, String _userPhoneNumber) {
        this._tergetGroup = _tergetGroup;
        this._userPhoneNumber = _userPhoneNumber;
        this._type="RemoveUser";
        this._responseMassegeBody=new Vector<>();
    }

    public String getTergetGroup() {
        return _tergetGroup;
    }

    public String getUserPhoneNumber() {
        return _userPhoneNumber;
    }
    public void massegeSuccess(){
        this._responseMassegeBody.add(_userPhoneNumber +" removed from "+_tergetGroup);
        this.setCode("200");
    }
    public void responseParameters(){
        this._responseMassegeBody.add("ERROR 336: Cannot remove, missing parameters");
        this.setCode("405");
    }
    public void responseTarget(){
        this._responseMassegeBody.add("ERROR 769: Target does nor exist");
        this.setCode("405");
    }
    public void responseUserNotInGroup(){
        this._responseMassegeBody.add("ERROR 777: user not in group");
        this.setCode("405");
    }
}
