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
public class Send {
    private String _type;
    private String _target;
    private String _content;

    public Send(String _type, String _target, String _content) {
        this._type = _type;
        this._target = _target;
        this._content = _content;
    }
    
}
