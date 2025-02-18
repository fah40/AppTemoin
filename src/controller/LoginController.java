package controller;

import java.sql.Connection;

import annotation.*;
import bd.MyConnect;
import models.*;
import util.MySession;
import model.*;

@Controller
public class LoginController {
    MySession session = new MySession();
    
    @Post
    @Url(url = "TestSprint/login")
    @RootPath(path = "login.jsp")
    public ModelView checkLog(@RequestParam(value = "email") String email, @RequestParam(value = "mdp") String mdp)
            throws Exception {
        ModelView model= new ModelView();
        model.setUrl("login.jsp");
        Connection con= MyConnect.getConnection();
        User userConnect= User.checkLoging(email, mdp, con); 
        
        if ( userConnect != null) {
            session.add("role", userConnect.getRole());
            session.add("user", userConnect);
            model.setUrl("acceuil.jsp");
        }
        
        return model;
    }
}