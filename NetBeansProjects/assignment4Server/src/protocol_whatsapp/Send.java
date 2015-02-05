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
public class Send extends RequestURI{
    private String _msgType;
    private String _target;
    private String _content;
    private Vector<String> _responseMassegeBody;
    
    public Send(String _msgType, String _target, String _content) {
        this._type = _msgType;
        this._target = _target;
        this._content = _content;
        this._type="Send";
        this._responseMassegeBody=new Vector<>();
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
    public void responseTarget(){
        _responseMassegeBody.add("ERROR 771: Target does not exist");
    }
    public void responseType(){
        _responseMassegeBody.add("ERROR 836: invalid type");
    }
    public void responseParameters(){
        _responseMassegeBody.add("ERROR 711: Cannot send, missing parameters");
    }
    
    
}
