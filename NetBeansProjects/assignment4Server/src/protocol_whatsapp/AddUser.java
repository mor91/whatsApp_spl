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
public class AddUser {
    private String _tergetGroup;
    private String _userPhoneNumber;

    public AddUser(String _tergetGroup, String _userPhoneNumber) {
        this._tergetGroup = _tergetGroup;
        this._userPhoneNumber = _userPhoneNumber;
    }
    
}
