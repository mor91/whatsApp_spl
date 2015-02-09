/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhatsApp;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author chen
 */
public class WhatsAppServer {
    private Map<String, User> userByName;
    private Map<String ,User> userByPhone;
    private Map<String ,User> userByCookie;
    private Map<String ,Group> groups;

    public WhatsAppServer() {
        this.groups=new HashMap<>();
        this.userByCookie=new HashMap<>();
        this.userByName=new HashMap<>();
        this.userByPhone=new HashMap<>();
    }
    public Group getGroup(String name){
        return groups.get(name);
    }
    public void addGroup(Group group){
        groups.put(group.getGroupName(), group);
    }
    public void addUser(User user){
        userByCookie.put(user.getCookie().getValue(), user);
        userByName.put(user.getUserName(), user);
        userByPhone.put(user.getPhoneNumber(), user);
    }
    public User getUserByCookie(String cookie){
        return userByCookie.get(cookie);
    }
    public User getUserByName(String name){
        return userByName.get(name);
    }
    public User getUserByPhone(String phone){
        return userByPhone.get(phone);
    }
    public void removeUser(User user){
        userByCookie.remove(user.getCookie());
        userByName.remove(user.getUserName());
        userByPhone.remove(user.getPhoneNumber());
        for (Map.Entry<String, Group> group : user.getGroups().entrySet()) {
            groups.get(group.getValue().getGroupName()).removeMember(user);
        }             
    }
    public void addUserToGroup(User user, Group group){
        groups.get(group.getGroupName()).addMember(user);
        userByName.get(user.getUserName()).addGroup(group);
    }
    public void removeUserFromGroup(User user, Group group){
        groups.get(group.getGroupName()).removeMember(user);
        userByName.get(user.getUserName()).removeGroup(group);
    }
    public Map<String, User> grtUsers(){
        return userByPhone;
    }
    public Map<String ,Group> getGroups(){
        return groups;
    }
    
}
