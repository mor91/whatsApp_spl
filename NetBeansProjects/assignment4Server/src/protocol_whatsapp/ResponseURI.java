/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;

import java.util.Vector;
import org.apache.http.protocol.ResponseContent;

/**
 *
 * @author chen
 */
public class ResponseURI {
    private Vector<String> _massegeBody;
    private Vector<String> _headers;
    private String _code;

    public ResponseURI(Vector<String> _massegeBody, Vector<String> _headers ,String code) {
        this._massegeBody = _massegeBody;
        this._headers = _headers;
        this._code=code;
    }

    public StringBuilder getHTTPResponse(){
        StringBuilder massege=new StringBuilder();
        massege.append("HTTP/1.1 ");
        massege.append(_code);
        switch(_code){
            case "200":
                massege.append(" Ok");
                break;
            case "404":
                massege.append(" Not Found");
                break;
            case "403":
                massege.append(" Forbidden");
                break;
            case "405":
                massege.append(" Method Not Allowed");
                break;
            case "418":
                massege.append(" I'm a teapot");
                break;                       
        }
        massege.append("\n");
        if(_headers!=null){
            for (String header : _headers) {
                massege.append(header);
                massege.append("\n");
            }  
        }
        if(_massegeBody!=null){
            massege.append("\n");  
            for (String massegeBody : _massegeBody) {
                massege.append(massegeBody);
                massege.append("\n");
            }
        }
        massege.append("$");
        return massege;
    }
    
    
}
