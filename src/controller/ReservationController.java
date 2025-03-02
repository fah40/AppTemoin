package controller;

import java.sql.Connection;

import annotation.*;
import db.MyConnect;
import models.*;
import util.MySession;
import model.*;

@Controller
@Auth(role = {"admin","client"})
public class ReservationController {
    MySession session = new MySession();

    @Get
    @Url(url = "AppTemoin/reservation")
    @RootPage(path = "reservation.jsp")
    public ModelView showAllVol() throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        try {
            con= MyConnect.getConnection();
            model.addObject("listAvion", Avion.getAll(con));
            model.addObject("listVille", Ville.getAll(con));
            model.addObject("listVol", Vol.getAll(con));
            model.setUrl("reservation.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (con != null) {
                con.close();
            }
        }

        return model;
    }

    @Get
    @Url(url = "AppTemoin/reserver")
    @RootPage(path = "reserver.jsp")
    public ModelView getChoosedVol(@RequestParam(value = "idVol") int idVol) throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        try {
            con= MyConnect.getConnection();
            model.addObject("myVol", Vol.getById(idVol,con));
            model.addObject("typeSiege", Siege_type.getAll(con));
            
            model.setUrl("reserver.jsp");
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
    @Url(url = "AppTemoin/saveReservation")
    @RootPage(path = "reserver.jsp")
    @AuthMethode(role = {"client"})
    public ModelView creatReservation(@ObjParam(value = "reservation") Reservation reservation,@RequestParam(value = "id_vol_curr") int id_vol_curr) throws Exception {
        Connection con= null;
        ModelView model= new ModelView();
        try {
            con= MyConnect.getConnection();
            
            reservation.insertCorrectly(con,id_vol_curr);

            model.addObject("myVol", Vol.getById(id_vol_curr,con));
            model.addObject("typeSiege", Siege_type.getAll(con));
            model.addObject("listReservation", Reservation.getAll(con));

            model.setUrl("reserver.jsp");
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