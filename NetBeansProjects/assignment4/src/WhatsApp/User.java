/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhatsApp;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import java.util.Map;

/**
 *
 * @author chen
 */
public class User {
    private String _userName;
    private String _phoneNumber;
    private Queue<WhatsAppMassege> _massegesQueue;
    private Cookie _cookie;
    private Map<String ,Group> groups;

    public User(String _userName, String _phoneNumber) {
        this._userName = _userName;
        this._phoneNumber = _phoneNumber;
        this._massegesQueue =new LinkedList();
        this._cookie=new BasicClientCookie((_userName+"Cookie"), _userName);
        this.groups=new HashMap<>();
    }

    public String getUserName() {
        return _userName;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }
        public void addMassegeToQueue(WhatsAppMassege msg){
        _massegesQueue.add(msg);
    }

    public Cookie getCookie() {
        return _cookie;
    }
        
    public Queue<WhatsAppMassege> getMassegesQueue(){
        return _massegesQueue;
    }

    public Map<String, Group> getGroups() {
        return groups;
    }
    public void addGroup(Group group){
        groups.put(group.getGroupName(), group);
    }
    public void removeGroup(Group group){
        groups.remove(group.getGroupName());
    }
}
