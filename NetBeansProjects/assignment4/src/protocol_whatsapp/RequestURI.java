
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;

import org.apache.http.cookie.Cookie;

/**
 *
 * @author chen
 */
public class RequestURI {
    protected String _code;
    protected String _type;
    protected String _requestingCookie;
    public String getUriType(){
        return _type;
    }

    public String getCode() {
        return _code;
    }

    public String getType() {
        return _type;
    }

    public void setCode(String _code) {
        this._code = _code;
    }

    public String getRequestingCookie() {
        return _requestingCookie;
    }

    public void setRequestingCookie(String _requestingCookie) {
        this._requestingCookie = _requestingCookie;
    }
}
