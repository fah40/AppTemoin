package controller;

import java.sql.Connection;
import java.util.HashMap;

import annotation.*;
import db.MyConnect;
import models.*;
import util.MySession;
import model.*;

@Controller
public class LoginController {
    MySession session = new MySession();

    @Post
    @Url(url = "AppTemoin/login")
    @RootPage(path = "login.jsp")
    public ModelView checkLog(@RequestParam(value = "email") String email, @RequestParam(value = "mdp") String mdp)
    throws Exception {
        ModelView model= new ModelView();
        model.setUrl("login.jsp");
        Connection con= MyConnect.getConnection();
        User userConnect= User.checkLogin(email, mdp, con); 
        HashMap<String, String> error=new HashMap<>();
        HashMap<String, String> value=new HashMap<>();
        
        if ( userConnect != null) {
            session.add("role", userConnect.getUserRole().getNom());
            session.add("user", userConnect);
            
            model.addObject("listAvion", Avion.getAll(con));
            model.addObject("listVille", Ville.getAll(con));
            model.addObject("listVol", Vol.getAll(con));
            
            if (userConnect.getRole().compareTo("admin") == 0) {
                model.setUrl("vol.jsp");
            }else{
                model.setUrl("reservation.jsp");
            }
        }
        error.put("email","veuillez verifiez l'email !");
        error.put("mdp","veuillez verifiez le mot de pass!");
        
        value.put("email", email);
        value.put("mdp", mdp);
        model.addObject("error",error);
        model.addObject("value",value);

        return model;
    }

    @Get
    @Url(url = "AppTemoin/deconnection")
    public ModelView deconnection()
            throws Exception {
        ModelView model= new ModelView();
        model.setUrl("login.jsp");
        session.destroy();
        return model;
    }
}