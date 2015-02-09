/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_http;
import java.net.URI;
import java.util.Map;
import protocol_whatsapp.AddUser;
import protocol_whatsapp.CreateGroup;
import protocol_whatsapp.List;
import protocol_whatsapp.Login;
import protocol_whatsapp.Logout;
import protocol_whatsapp.MassegesQueue;
import protocol_whatsapp.RemoveUser;
import protocol_whatsapp.RequestURI;
import protocol_whatsapp.Send;

/**
 *
 * @author chen
 */
public class HttpProtocol<T> implements protocol.ServerProtocol{

    @Override
    public Object processMessage(Object msg) {
        RequestURI requestURI=null;
        if(((Map<String,String>)msg).containsKey("POST")||((Map<String,String>)msg).containsKey("GET")){
            switch(((Map<String,String>)msg).get("POST")){
                case "login.jsp":
                    String userName=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                    String phoneNumber=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                    requestURI=new Login(userName, phoneNumber);
                    break;
                case "list.jsp":
                    requestURI=new List(((Map<String,String>)msg).get("massegeBody").split("\n")[0].split("=")[1],((Map<String,String>)msg).get("massegeBody").split("\n")[1].split("=")[1]);
                    ((List)requestURI).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                    break;
                case "create_group.jsp":
                    String groupName=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                    String[] users=((Map<String,String>)msg).get("massegeBody").split("&");
                    requestURI=new CreateGroup(groupName);
                    for(int i=1;i<users.length;i++){
                       ((CreateGroup)requestURI).addUserToGroup(users[i].split("=")[1]);
                    }
                    ((CreateGroup)requestURI).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                    break;
                case "send.jsp":
                    String type=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                    String target=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                    String content=((Map<String,String>)msg).get("massegeBody").split("&")[2].split("=")[1];
                    requestURI=new Send(type, target, content);
                    ((Send)requestURI).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                    break;
                     
                case "add_user.jsp":

                    String targrt=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                    String userPhone=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                    requestURI=new AddUser(targrt, userPhone);
                    ((AddUser)requestURI).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                break;
                     
                case "remove_user.jsp":
                    String targrt1=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                    String userPhone1=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                    requestURI=new RemoveUser(targrt1, userPhone1);
                    ((RequestURI)msg).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                default:
                    switch(((Map<String,String>)msg).get("GET")){
                            case "logout.jsp": ((Logout)requestURI).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                                break;
                            case "queue.jsp": ((MassegesQueue)requestURI).setRequestingCookie(((Map<String,String>)msg).get("Cookie"));
                            default: requestURI.setCode("404");
                                break;
                    }
            }
        }
        return requestURI;
    }

    @Override
    public boolean isEnd(Object msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public URI processRequestMassege(Object msg){
        return null;
    }
    
   
}
