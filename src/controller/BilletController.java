package controller;

import java.sql.Connection;

import annotation.*;
import db.MyConnect;
import models.*;
import util.MySession;
import model.*;

@Controller
@Auth(role = {"client","admin"})
public class BilletController {
    MySession session = new MySession();

    @Get
    @Url(url = "AppTemoin/billet")
    @RootPage(path = "billet.jsp")
    public ModelView showAllVol() throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        try {
            con= MyConnect.getConnection();
            String role = (String)session.get("role");
            if (role.compareTo("admin") == 0) {
                model.addObject("listBillet", Billet.getAll(con));
            }else{
                model.addObject("listBillet", Billet.getAllMyBillet(con,((User)session.get("user")).getId()));
            }
            model.setUrl("billet.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (con != null) {
                con.close();
            }
        }

        return model;
    }

}