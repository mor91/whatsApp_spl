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
public class Send extends RequestURI{
    private String _msgType;
    private String _target;
    private String _content;

    public Send(String _msgType, String _target, String _content) {
        this._type = _msgType;
        this._target = _target;
        this._content = _content;
    }

    public String getTarget() {
        return _target;
    }

    public String getContent() {
        return _content;
    }

    public String getMsgType() {
        return _msgType;
    }
    
    
    
}
