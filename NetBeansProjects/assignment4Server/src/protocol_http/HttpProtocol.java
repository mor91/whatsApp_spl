/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_http;
import java.net.URI;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import java.util.Map;


/**
 *
 * @author chen
 */
public class HttpProtocol<T> implements protocol.ServerProtocol{

    @Override
    public Object processMessage(Object msg) {
        boolean isValidUri=false;
         if(((Map<String,String>)msg).containsKey("POST")){
             if(((Map<String,String>)msg).get("POST").compareTo("login.jsp")==0){
                 isValidUri=true;
             }
             if(((Map<String,String>)msg).get("POST").compareTo("list.jsp")==0){
                 isValidUri=true;
             }
             if(((Map<String,String>)msg).get("POST").compareTo("create_group.jsp")==0){
                 isValidUri=true;
             }
             if(((Map<String,String>)msg).get("POST").compareTo("send.jsp")==0){
                 isValidUri=true;
             }
             if(((Map<String,String>)msg).get("POST").compareTo("add_user.jsp")==0){
                 isValidUri=true;
             }
             if(((Map<String,String>)msg).get("POST").compareTo("remove_user.jsp")==0){
                 isValidUri=true;
             }
            
        }
        if(((Map<String,String>)msg).containsKey("GET")){
            if(((Map<String,String>)msg).get("GET").compareTo("logout.jsp")==0){
                 isValidUri=true;
             }
            if(((Map<String,String>)msg).get("GET").compareTo("queue.jsp")==0){
                 isValidUri=true;
             }
        }
        if(!isValidUri){
            //response code 404
        }
        else{
            //check 
        }
       
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnd(Object msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public URI processRequestMassege(Object msg){
        return null;
    } 
    public HttpResponse processResponseMessage(Object msg){
        int statusCode=0;
        StatusLine statusLine=new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), statusCode, null);
        HttpResponse response=new BasicHttpResponse(statusLine);
        return response;
    } 
}
