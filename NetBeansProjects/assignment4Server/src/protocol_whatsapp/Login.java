/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;

/**
 *
 * @author chen
 */
public class Login extends RequestURI{
    private String _userName;
    private String _phoneNumber;


    public Login(String _userName, String _phoneNumber) {
        this._userName = _userName;
        this._phoneNumber = _phoneNumber;
        this._type="Login";

    }

    public String getUserName() {
        return _userName;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }
    
}
