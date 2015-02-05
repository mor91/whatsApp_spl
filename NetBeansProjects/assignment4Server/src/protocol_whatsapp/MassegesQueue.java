/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol_whatsapp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


/**
 *
 * @author chen
 */
public class MassegesQueue extends RequestURI{
    private String _userName;
    private Vector<String> _responseMassegeBody;
    

    public MassegesQueue(String _userName) {
        this._userName = _userName;
        this._type="MassegesQueue";
         this._responseMassegeBody=new Vector<>();
        
    }

    public String getUserName() {
        return _userName;
    }
    public void massegeSuccess(User user){
        while(!user.getMassegesQueue().isEmpty()){
            WhatsAppMassege whatsAppMassege=user.getMassegesQueue().poll();
            this._responseMassegeBody.add("from: "+whatsAppMassege.getFrom()+"Msg: "+whatsAppMassege.getMsg());
        }
        this.setCode("200");
    }
    public void massegeNoMasseges(){
        this._responseMassegeBody.add("No new masseges");
        this.setCode("200");
    }

}
