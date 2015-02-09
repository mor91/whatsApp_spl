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
public class AddUser extends RequestURI{
    private String _tergetGroup;
    private String _userPhoneNumber;
    private Vector<String> _responseMassegeBody;

    public AddUser(String _tergetGroup, String _userPhoneNumber) {
        this._tergetGroup = _tergetGroup;
        this._userPhoneNumber = _userPhoneNumber;
        this._responseMassegeBody=new Vector<>();
        this._type="AddUser";
    }

    public String getTergetGroup() {
        return _tergetGroup;
    }

    public String getUserPhoneNumber() {
        return _userPhoneNumber;
    }
    public void responsePermission(){
        this._responseMassegeBody.add("ERROR 669: Permission denied");
    }
    public void responseParameters(){
        this._responseMassegeBody.add("ERROR 242: Cannot add user, missing parameters");
        this.setCode("405");
    }
    public void responseExistInGroup(){
        this._responseMassegeBody.add("ERROR 142: Cannot add user, user already in group");
        this.setCode("405");
    }
    public void responseTargetNoFound(){
        this._responseMassegeBody.add("ERROR 770: Target does not Exist");
        this.setCode("405");
    }
    public void massegeSuccess(){
        this._responseMassegeBody.add(_userPhoneNumber+" added to "+_tergetGroup);
        this.setCode("200");
    }
    public void responseUserNotound(){
        this._responseMassegeBody.add("ERROR 999: User not found");
        this.setCode("405");
    }

    public Vector<String> getResponseMassegeBody() {
        return _responseMassegeBody;
    }
    
}
