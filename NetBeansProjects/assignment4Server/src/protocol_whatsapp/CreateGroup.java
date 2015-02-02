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
public class CreateGroup {
    private String _groupName;
    private Map<String, String> _usersMap;

    public CreateGroup(String _groupName, Map<String, String> _usersMap) {
        this._groupName = _groupName;
        this._usersMap = new HashMap<>();
    }
    
    public void addUserToGroup(String userName, String phoneNumber){
        _usersMap.put(userName, phoneNumber);
    }
    
}
