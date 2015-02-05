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
public class Login extends RequestURI{
    private String _userName;
    private String _phoneNumber;
    private Vector<String> _responseMassegeBody;
    private String _cookie;

    public Login(String _userName, String _phoneNumber) {
        this._userName = _userName;
        this._phoneNumber = _phoneNumber;
        this._responseMassegeBody=new  Vector<>();
        this._type="Login";

    }

    public String getUserName() {
        return _userName;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }
    public void responseParameters(){
        this._responseMassegeBody.add("ERROR 765: Cannot login, missing parameters");
        this.setCode("405");
    }
    public void responseUserExist(){
        this._responseMassegeBody.add("ERROR 888: Cannot login, user already exist");
        this.setCode("405");
    }
    public void massegeSuccess(){
        this._responseMassegeBody.add("Welcome "+_userName+_phoneNumber);
        this.setCode("200");
    }
    public void setCookie(String _cookie) {
        this._cookie = _cookie;
    }

}
