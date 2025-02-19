package controller;

import java.sql.Connection;

import annotation.*;
import bd.MyConnect;
import models.*;
import util.MySession;
import model.*;

@Controller
@Auth(role = {"admin"})
public class VolAdminController {
    MySession session = new MySession();

    @Get
    @Url(url = "AppTemoin/listVol")
    @RootPage(path = "vol.jsp")
    public ModelView showAllVol() throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        try {
            con= MyConnect.getConnection();
            model.addObject("listAvion", Avion.getAll(con));
            model.addObject("listVille", Ville.getAll(con));
            model.addObject("listVol", Vol.getAll(con));
            model.setUrl("vol.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (con != null) {
                con.close();
            }
        }

        return model;
    }

    @Post
    @Url(url = "AppTemoin/insertVol")
    @RootPage(path = "vol.jsp")
    @AuthMethode(role = {"admin"})
    public ModelView creatVol(@ObjParam(value = "vol") Vol vol) throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        try {
            con= MyConnect.getConnection();
            
            vol.insert(con);
            
            model.addObject("listAvion", Avion.getAll(con));
            model.addObject("listVille", Ville.getAll(con));
            model.addObject("listVol", Vol.getAll(con));
            model.setUrl("vol.jsp");
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