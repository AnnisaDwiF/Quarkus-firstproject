package id.kawahedukasi.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/peserta")
public class PesertaController {
    //list all peserta database
    @GET
    public String inputPathParameter(@PathParam("nama") String nama){
        return "Peserta dengan nama input path parameter : " + nama ;
    }

}
