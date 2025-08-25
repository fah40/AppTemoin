package controller;

import java.sql.Connection;

import annotation.*;
import db.MyConnect;
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
    public ModelView creatVol(
        @ObjParam(value = "vol") Vol vol, 
        @RequestParam(value = "prix_economique") double prix_economique, 
        @RequestParam(value = "prix_business") double prix_business,
        @RequestParam(value = "date_debut") String date_debut,
        @RequestParam(value = "date_fin") String date_fin
        ) throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        int idvol= 0;
        model.setUrl("vol.jsp");
        try {
            con= MyConnect.getConnection();
            
            idvol = vol.insert(con);
            
            HistoPrix prix= new HistoPrix();
            prix.setDate_debut(date_debut);
            prix.setDate_fin(date_debut);
            prix.setPrix_business(prix_business);
            prix.setPrix_economique(prix_economique);
            prix.setId_vol(idvol);
            prix.insert(con);

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