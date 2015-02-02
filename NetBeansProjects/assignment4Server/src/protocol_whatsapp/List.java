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
            case "users":
                createUsersList();
                break;
            case "group":
                createGroupList();
                break;
            case "groups":
                createGroupsList();
                break;
            default: inValidType();
                break;
        }
                
    }
    
    private void createUsersList(){
        Map<String,String> usersList=new HashMap<>();
    }
    private void createGroupList(){
        
    }
    private void createGroupsList(){
        
    }
    private void inValidType(){
        _isValidType=false;
    }
}
