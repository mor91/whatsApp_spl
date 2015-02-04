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
public class WhatsAppMassege {
    private String _from;
    private String _msg;

    public WhatsAppMassege(String _from, String _msg) {
        this._from = _from;
        this._msg = _msg;
    }

    public String getFrom() {
        return _from;
    }

    public String getMsg() {
        return _msg;
    }
    
}
