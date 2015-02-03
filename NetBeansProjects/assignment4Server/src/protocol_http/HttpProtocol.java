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
        boolean isValidUri=false;
        RequestURI requestURI=null;
         if(((Map<String,String>)msg).containsKey("POST")){
             if(((Map<String,String>)msg).get("POST").compareTo("login.jsp")==0){
                 isValidUri=true;
                 String userName=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                 String phoneNumber=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                 requestURI=new Login(userName, phoneNumber);
             }
             if(((Map<String,String>)msg).get("POST").compareTo("list.jsp")==0){
                 isValidUri=true;
                 requestURI=new List(((Map<String,String>)msg).get("massegeBody").split("=")[1]);
             }
             if(((Map<String,String>)msg).get("POST").compareTo("create_group.jsp")==0){
                 isValidUri=true;
                 String groupName=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                 String[] users=((Map<String,String>)msg).get("massegeBody").split("&");
                 requestURI=new CreateGroup(groupName);
                 for(int i=1;i<users.length;i++){
                    ((CreateGroup)requestURI).addUserToGroup(users[i].split("=")[1]);
                 }
             }
             if(((Map<String,String>)msg).get("POST").compareTo("send.jsp")==0){
                 isValidUri=true;
                 String type=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                 String target=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                 String content=((Map<String,String>)msg).get("massegeBody").split("&")[2].split("=")[1];
                 requestURI=new Send(type, target, content);
             }
             if(((Map<String,String>)msg).get("POST").compareTo("add_user.jsp")==0){
                 isValidUri=true;
                 String targrt=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                 String userPhone=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                 requestURI=new AddUser(targrt, userPhone);
             }
             if(((Map<String,String>)msg).get("POST").compareTo("remove_user.jsp")==0){
                 isValidUri=true;
                  String targrt=((Map<String,String>)msg).get("massegeBody").split("&")[0].split("=")[1];
                 String userPhone=((Map<String,String>)msg).get("massegeBody").split("&")[1].split("=")[1];
                 requestURI=new RemoveUser(targrt, userPhone);
             }
            
        }
        if(((Map<String,String>)msg).containsKey("GET")){
            if(((Map<String,String>)msg).get("GET").compareTo("logout.jsp")==0){
                 isValidUri=true;
                 
                 //requestURI=new Logout(null);
             }
            if(((Map<String,String>)msg).get("GET").compareTo("queue.jsp")==0){
                 isValidUri=true;
                 //requestURI=new MassegesQueue(null);
             }
        }
        if(!isValidUri){
            //response code 404
        }
        else{
            //check 
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
