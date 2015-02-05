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
public class Logout extends RequestURI{
    private String _userName;
    private Vector<String> _responseMassegeBody;

    public Logout(String _userName) {
        this._userName = _userName;
        this._type="Logout";
        this._responseMassegeBody=new Vector<>();
    }

    public String getUserName() {
        return _userName;
    }
    public void massegeSuccess(){
        this._responseMassegeBody.add("Googbye");
        this.setCode("200");
    }
    
}
