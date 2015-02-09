/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;
import WhatsApp.User;
import WhatsApp.Group;
import WhatsApp.WhatsAppServer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chen
 */
public class WhatsAppProtocol<T> implements protocol.ServerProtocol{
    private WhatsAppServer server;
    

    public WhatsAppProtocol(WhatsAppServer server) {
        this.server=server;   
    }
    
    @Override
    public Object processMessage(Object msg) {
        ResponseHTTP response=null;
        /*if(((RequestURI)msg).getCode().compareTo("404")==0){
            response=new ResponseURI(null, null, "404");
            return response;
        }*/
        switch(((RequestURI)msg).getUriType()){
            case "AddUser":
                addUSer(msg);
                response=new ResponseHTTP(((AddUser)msg).getResponseMassegeBody(), null, ((AddUser)msg).getCode());
                break;
            case"Login":
                login(msg);
                response=new ResponseHTTP(((Login)msg).getResponseMassegeBody(), ((Login)msg).getHeaders(), ((Login)msg).getCode());
                break;
            case "Logout":
                logout(msg);
                response=new ResponseHTTP(((Logout)msg).getResponseMassegeBody(), null,((Logout)msg).getCode());
                break;
            case "CreateGroup":
                createGroup(msg);
                response=new ResponseHTTP(((CreateGroup)msg).getResponseMassegeBody(), null, ((Logout)msg).getCode());
                break;
            case "List":
                list(msg);
                response=new ResponseHTTP(((List)msg).getResponseMassegeBody(), null, ((List)msg).getCode());
                break;
            case "RemoveUser":
                removeUser(msg);
                response=new ResponseHTTP(((RemoveUser)msg).getResponseMassegeBody(), null, ((RemoveUser)msg).getCode());
                break;
            case "MassegeQueue":
                massegeQueue(msg);
                response=new ResponseHTTP(((MassegesQueue)msg).getResponseMassegeBody(), null, ((MassegesQueue)msg).getCode());
                break;
            case "Send":
                send(msg);
               // response=new ResponseURI(((Send)msg).g, null, null);
                break;
            default: response=new ResponseHTTP(null, null, "404");
                break;
        }
        return response;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnd(Object msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void addUSer(Object msg){
        if(((AddUser)msg).getUserPhoneNumber()!=""&&((AddUser)msg).getTergetGroup()!=""){//parameters
            if(server.grtUsers().containsKey(((AddUser)msg).getUserPhoneNumber())){//user exist
                if(server.getGroups().containsKey(((AddUser)msg).getTergetGroup())){//terget exist
                    if(!server.getGroups().get(((AddUser)msg).getTergetGroup()).containUser(server.grtUsers().get(((AddUser)msg).getUserPhoneNumber()))){//user already in group
                        for (User user : server.getGroups().get(((AddUser)msg).getTergetGroup()).getUsers()) {
                            if(user.getCookie().getValue().compareTo(((AddUser)msg).getRequestingCookie())!=0){
                                ((AddUser)msg).responsePermission();
                                ((AddUser)msg).setCode("403");
                                break;
                            }
                        }
                        server.addUserToGroup(server.grtUsers().get(((AddUser)msg).getUserPhoneNumber()),server.getGroups().get(((AddUser)msg).getTergetGroup()) );
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
            if(!server.grtUsers().containsKey(((Login)msg).getPhoneNumber())){
                User user=new User(((Login)msg).getUserName(), ((Login)msg).getPhoneNumber());
                server.addUser(user);
                ((Login)msg).massegeSuccess(user);
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
        server.removeUser(server.getUserByCookie(((Logout)msg)._requestingCookie));
        ((Logout)msg).massegeSuccess();  
    }
    
    private void createGroup(Object msg){
        boolean isOk=true;
        if(!server.getGroups().containsKey(((CreateGroup)msg).getGroupName())){
            Group group=new Group(((CreateGroup)msg).getGroupName());
            server.addGroup( group);
        }
        else{
            ((CreateGroup)msg).responseNameTaken();
            isOk=false;
        }
        for (String user : ((CreateGroup)msg).getUsersVector()) {
            if(server.getGroups().containsKey(user))
            {
                server.addUserToGroup(server.getUserByPhone(user), server.getGroup(((CreateGroup)msg).getGroupName()));
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
                case "Users": ((List)msg).massegeSuccessUsers(server.grtUsers());
                    break;
                case "Groups": ((List)msg).massegeSuccessGroups(server.getGroups());
                    break; 
                case "Group": ((List)msg).massegeSuccessGroup(server.getGroup((((List)msg).getGroupName())));
                    break;
            }
        }
        else{
            ((List)msg).responseParameters();
        }
    }
    
    private void removeUser(Object msg){
        if(((RemoveUser)msg).getTergetGroup()!=""&&((RemoveUser)msg).getUserPhoneNumber()!=""){
            if(server.getGroups().containsKey(((RemoveUser)msg).getTergetGroup())){
                if( server.getGroup(((RemoveUser)msg).getTergetGroup()).containUser(server.grtUsers().get(((RemoveUser)msg).getUserPhoneNumber()))){
                    for (User user :server.getGroup(((RemoveUser)msg).getTergetGroup()).getUsers()) {
                        if(user.getCookie().getValue().compareTo(((RemoveUser)msg).getRequestingCookie())!=0){
                                    ((RemoveUser)msg).setCode("403");
                                    break;
                        }
                    }
                    server.removeUserFromGroup(server.grtUsers().get(((RemoveUser)msg).getUserPhoneNumber()), server.getGroup(((RemoveUser)msg).getTergetGroup()));
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
        if(!server.getUserByName((((MassegesQueue)msg).getUserName())).getMassegesQueue().isEmpty()){
            ((MassegesQueue)msg).massegeSuccess(server.getUserByName(((MassegesQueue)msg).getUserName()));
        }
        else{
            ((MassegesQueue)msg).massegeNoMasseges();
        } 
    }
    
    private void send(Object msg){
        if(((Send)msg).getContent()!=""&&((Send)msg).getMsgType()!=""&&((Send)msg).getTarget()!=""){
            switch(((Send)msg).getMsgType()){
                case "Group":
                    if(server.getGroups().containsKey(((Send)msg).getTarget())){
                        ((Send)msg).responseSuccess();
                    }
                    else{
                        ((Send)msg).responseTarget();
                    }
                    break;
                case "Direct":
                    if(server.grtUsers().containsKey(((Send)msg).getTarget())){
                            ((Send)msg).responseSuccess();
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
