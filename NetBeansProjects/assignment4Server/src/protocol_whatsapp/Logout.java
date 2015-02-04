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
public class Logout extends RequestURI{
    private String _userName;

    public Logout(String _userName) {
        this._userName = _userName;
        this._type="Logout";
    }

    public String getUserName() {
        return _userName;
    }
    
    
}
