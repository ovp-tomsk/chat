package chatx.serv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class ChatsMyController
{
    //**************************************************************************    
    @RequestMapping (value = {"/chats-my/", "/chats-my.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    
    public String showPgChatsMy () 
    {  
        return "chats-my";
    }
        
    //**************************************************************************    
}
