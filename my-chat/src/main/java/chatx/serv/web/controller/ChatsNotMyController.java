package chatx.serv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class ChatsNotMyController
{
    //**************************************************************************    
    @RequestMapping (value = {"/chats-not-my/", "/chats-not-my.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    
    public String showChatsNotMy () 
    {  
        return "chats-not-my";
    }
        
    //**************************************************************************    
}
