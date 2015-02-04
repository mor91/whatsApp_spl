/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chen
 */
public class List extends RequestURI{
    private String _listType;
    private boolean _isValidType=true;

    public List(String _listType) {
        this._listType = _listType;
        
        switch(_listType){
            case "Users":
                break;
            case "Group":
                break;
            case "Groups":
                break;
            default: inValidType();
                break;
        }
        this._type="List";
                
    }
    private void inValidType(){
        _isValidType=false;
    }

    public String getListType() {
        return _listType;
    }

    public boolean isIsValidType() {
        return _isValidType;
    }
    
}
