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
        if(((RequestURI)msg).getCode().compareTo("404")==0){
            return msg;
        }
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
        if(((AddUser)msg).getUserPhoneNumber()!=""&&((AddUser)msg).getTergetGroup()!=""){//parameters
            if(_users.containsKey(((AddUser)msg).getUserPhoneNumber())){//user exist
                if(_groups.containsKey(((AddUser)msg).getTergetGroup())){//terget exist
                    if(!_groups.get(((AddUser)msg).getTergetGroup()).containUser(_users.get(((AddUser)msg).getUserPhoneNumber()))){//user already in group
                        for (User user : _groups.get(((AddUser)msg).getTergetGroup()).getUsers()) {
                            if(user.getCookie().getValue().compareTo(((AddUser)msg).getRequestingCookie())!=0){
                                ((AddUser)msg).responsePermission();
                                ((AddUser)msg).setCode("403");
                                break;
                            }
                        }
                        _groups.get(((AddUser)msg).getTergetGroup()).addMember(_users.get(((AddUser)msg).getUserPhoneNumber()));
                        ((AddUser)msg).massegeSuccess();
                    }
                    else{
                        ((AddUser)msg).responseExistInGroup();
                    }
                }
                else{
                    ((AddUser)msg).responseTargetNoFound();
                }
            }
            else{
               ((AddUser)msg).responseUserNotound();
            }
        }
        else{
            ((AddUser)msg).responseParameters();
        }
    }
    private void login(Object msg){
        if(((Login)msg).getPhoneNumber()!=""&&((Login)msg).getUserName()!="")
        {
            if(!_users.containsKey(((Login)msg).getPhoneNumber())){
                User user=new User(((Login)msg).getUserName(), ((Login)msg).getPhoneNumber());
                _users.put(((Login)msg).getPhoneNumber(),user);
                ((Login)msg).massegeSuccess();
                ((Login)msg).setCookie(user.getCookie().getValue());
                
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
                    for (User user : _groups.get(((RemoveUser)msg).getTergetGroup()).getUsers()) {
                        if(user.getCookie().getValue().compareTo(((RemoveUser)msg).getRequestingCookie())!=0){
                                    ((RemoveUser)msg).setCode("403");
                                    break;
                        }
                    }
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
                        //send content to group
                    }
                    else{
                        ((Send)msg).responseTarget();
                    }
                    break;
                case "Direct":
                    if(_users.containsKey(((Send)msg).getTarget())){
                            //send content to user
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
