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
            default: 
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
        if(((Login)msg).getPhoneNumber()!=""&&((Login)msg).getUserName()!="")
        {
            if(!_users.containsKey(((Login)msg).getPhoneNumber())){
                User user=new User(((Login)msg).getUserName(), ((Login)msg).getPhoneNumber());
                _users.put(((Login)msg).getPhoneNumber(),user);
                ((RemoveUser)msg).massegeSuccess();
            }
            else {
                ((Login)msg).responseUserExist();
            }
        }
        else{
            ((Login)msg).responseParameters();
        } 
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
        if(((List)msg).getListType()!=""){
            switch(((List)msg).getListType()){
                case "Users": ((List)msg).massegeSuccessUsers(_users);
                    break;
                case "Groups": ((List)msg).massegeSuccessGroups(_groups);
                    break; 
                case "Group": ((List)msg).massegeSuccessGroup(_groups.get(((List)msg).getGroupName()));
                    break;
            }
        }
        else{
            ((List)msg).responseParameters();
        }
    }
    
    private void removeUser(Object msg){
        if(((RemoveUser)msg).getTergetGroup()!=""&&((RemoveUser)msg).getUserPhoneNumber()!=""){
            if(_groups.containsKey(((RemoveUser)msg).getTergetGroup())){
                if( _groups.get(((RemoveUser)msg).getTergetGroup()).containUser(_users.get(((RemoveUser)msg).getUserPhoneNumber()))){
                    _groups.get(((RemoveUser)msg).getTergetGroup()).removeMember(_users.get(((RemoveUser)msg).getUserPhoneNumber()));
                    ((RemoveUser)msg).massegeSuccess();
                }
                else{
                    ((RemoveUser)msg).responseUserNotInGroup();
                }
            }
            else{
                ((RemoveUser)msg).responseTarget();
            }
        }
        else{
            ((RemoveUser)msg).responseParameters();
        }
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
        if(((Send)msg).getContent()!=""&&((Send)msg).getMsgType()!=""&&((Send)msg).getTarget()!=""){
            switch(((Send)msg).getMsgType()){
                case "Group":
                    if(_groups.containsKey(((Send)msg).getTarget())){
                        //sent content to group
                    }
                    else{
                        ((Send)msg).responseTarget();
                    }
                    break;
                case "Direct":
                    if(_users.containsKey(((Send)msg).getTarget())){
                            //sent content to user
                    }
                    else{
                        ((Send)msg).responseTarget();
                    }
                default: ((Send)msg).responseType();
            }
        }
        else{
            ((Send)msg).responseParameters();
        }
    }
}
