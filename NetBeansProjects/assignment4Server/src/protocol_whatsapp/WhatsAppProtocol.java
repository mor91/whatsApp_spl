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
public class WhatsAppProtocol<T> implements protocol.ServerProtocol{
    private Map<String,User > _users;
    private Map<String,Group> _groups;
    

    public WhatsAppProtocol() {
        this._users = new HashMap<>();
    }
    
    @Override
    public Object processMessage(Object msg) {
        switch(((RequestURI)msg).getType()){
            case "AddUser":
                addUSer(msg);
                break;
            case"Login":
                login(msg);
                break;
            case "Logout":
                logout(msg);
                break;
            case "CreateGroup":
                createGroup(msg);
                break;
            case "List":
                list(msg);
                break;
            case "RemoveUser":
                removeUser(msg);
                break;
            case "MassegeQueue":
                massegeQueue(msg);
                break;
            case "Send":
                send(msg);
                break;
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnd(Object msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void addUSer(Object msg){
            if(_users.containsKey(((AddUser)msg).getUserPhoneNumber())){
                if(_groups.containsKey(((AddUser)msg).getTergetGroup())){
                    if(!_groups.get(((AddUser)msg).getTergetGroup()).containUser(_users.get(((AddUser)msg).getUserPhoneNumber()))){
                        _groups.get(((AddUser)msg).getTergetGroup()).addMember(_users.get(((AddUser)msg).getUserPhoneNumber()));
                    }
                    else{
                        //error 142 cannot add user, user all ready in group
                    }
                }
                else{
                    //error 770 target not exist
                }
            }
            else{
                //error *** user not found
            }
            
    }
    private void login(Object msg){
        if(!_users.containsKey(((Login)msg).getPhoneNumber())){
            User user=new User(((Login)msg).getUserName(), ((Login)msg).getPhoneNumber());
            _users.put(((Login)msg).getPhoneNumber(),user);
        }
        else {
            //error user exists
        }
        //cookie
    }
    private void logout(Object msg){
        if(_users.containsKey(((Logout)msg).getUserName())){
            _users.remove(((Logout)msg).getUserName());
        }
        else {
            //error user not found
        }
    }
    private void createGroup(Object msg){
        if(!_groups.containsKey(((CreateGroup)msg).getGroupName())){
            Group group=new Group(((CreateGroup)msg).getGroupName());
            _groups.put(((CreateGroup)msg).getGroupName(), group);
        }
        else{
            //error group name exists
            
        }
        for (String user : ((CreateGroup)msg).getUsersVector()) {
            if(_users.containsKey(user))
            {
                _groups.get(((CreateGroup)msg).getGroupName()).addMember(_users.get(user));
            }
            else{
                //error user not found 
            }
        }
    }
    private void list(Object msg){
        switch(((List)msg).getListType()){
            case "Users": //return users
                break;
            case "Group": //return group
                break;
            case "Groups": //return groups
                break; 
        }
    }
    private void removeUser(Object msg){
        if(_users.containsKey(((RemoveUser)msg).getUserPhoneNumber())){
            if(_groups.containsKey(((RemoveUser)msg).getTergetGroup())){
                
            }
        }
    }
    private void massegeQueue(Object msg){
        
    }
    private void send(Object msg){
        
    }
}
