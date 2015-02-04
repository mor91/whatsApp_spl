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
        switch(((RequestURI)msg).getUriType()){
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
        return msg;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnd(Object msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void addUSer(Object msg){
        boolean isOk=true;
        if(((AddUser)msg).getUserPhoneNumber()!=""&&((AddUser)msg).getTergetGroup()!=""){
            if(_users.containsKey(((AddUser)msg).getUserPhoneNumber())){
                if(_groups.containsKey(((AddUser)msg).getTergetGroup())){
                    if(!_groups.get(((AddUser)msg).getTergetGroup()).containUser(_users.get(((AddUser)msg).getUserPhoneNumber()))){
                        _groups.get(((AddUser)msg).getTergetGroup()).addMember(_users.get(((AddUser)msg).getUserPhoneNumber()));
                    }
                    else{
                        ((AddUser)msg).responseExistInGroup();
                        isOk=false;
                    }
                }
                else{
                    ((AddUser)msg).responseTargetNoFound();
                    isOk=false;
                }
            }
            else{
               ((AddUser)msg).responseUserNotound();
               isOk=false;
            }
        }
        else{
            ((AddUser)msg).responseParameters();
            isOk=false;
        }
        if(isOk)
            ((AddUser)msg).massegeSuccess();
    }
    private void login(Object msg){
        boolean isOk=true;
        if(((Login)msg).getPhoneNumber()!=""&&((Login)msg).getUserName()!="")
        {
            if(!_users.containsKey(((Login)msg).getPhoneNumber())){
                User user=new User(((Login)msg).getUserName(), ((Login)msg).getPhoneNumber());
                _users.put(((Login)msg).getPhoneNumber(),user);
            }
            else {
                ((Login)msg).responseUserExist();
                isOk=false;
            }
        }
        else{
            ((Login)msg).responseParameters();
            isOk=false;
        }
            if(isOk)
                ((Login)msg).massegeSuccess();   
    }
    private void logout(Object msg){
        _users.remove(((Logout)msg).getUserName());
        ((Logout)msg).massegeSuccess();  
    }
    private void createGroup(Object msg){
        boolean isOk=true;
        if(!_groups.containsKey(((CreateGroup)msg).getGroupName())){
            Group group=new Group(((CreateGroup)msg).getGroupName());
            _groups.put(((CreateGroup)msg).getGroupName(), group);
        }
        else{
            ((CreateGroup)msg).responseNameTaken();
            isOk=false;
        }
        for (String user : ((CreateGroup)msg).getUsersVector()) {
            if(_users.containsKey(user))
            {
                _groups.get(((CreateGroup)msg).getGroupName()).addMember(_users.get(user));
            }
            else{
                ((CreateGroup)msg).responseUnknownUser(user);
                isOk=false;
            }
        }
        if(isOk)
            ((CreateGroup)msg).massegeSuccess();
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
        boolean isOk=true;
        if(((RemoveUser)msg).getTergetGroup()!=""&&((RemoveUser)msg).getUserPhoneNumber()!=""){
            if(_groups.containsKey(((RemoveUser)msg).getTergetGroup())){
                if( _groups.get(((RemoveUser)msg).getTergetGroup()).containUser(_users.get(((RemoveUser)msg).getUserPhoneNumber()))){
                    _groups.get(((RemoveUser)msg).getTergetGroup()).removeMember(_users.get(((RemoveUser)msg).getUserPhoneNumber()));
                }
                else{
                    ((RemoveUser)msg).responseUserNotInGroup();
                    isOk=false;
                }
            }
            else{
                ((RemoveUser)msg).responseTarget();
                isOk=false;
            }
        }
        else{
            ((RemoveUser)msg).responseParameters();
            isOk=false;
        }
        if(isOk)
            ((RemoveUser)msg).massegeSuccess();
    }
    private void massegeQueue(Object msg){
        if(!_users.get(((MassegesQueue)msg).getUserName()).getMassegesQueue().isEmpty()){
            ((MassegesQueue)msg).massegeSuccess(_users.get(((MassegesQueue)msg).getUserName()));
        }
        else{
            ((MassegesQueue)msg).massegeNoMasseges();
        } 
    }
    private void send(Object msg){
        switch(((Send)msg).getMsgType()){
            case "Group":
                if(_groups.containsKey(((Send)msg).getTarget())){
                    //sent content to group
                }
                else{
                    //error target not found 
                }
                break;
            case "Direct":
                if(_users.containsKey(((Send)msg).getTarget())){
                        //sent content to user
                }
                else{
                    //error target not found 
                }
            default: //invalid type 
        }
    }
}
