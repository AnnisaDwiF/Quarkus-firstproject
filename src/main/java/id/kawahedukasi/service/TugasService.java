package id.kawahedukasi.service;

import id.kawahedukasi.model.Peserta;
import id.kawahedukasi.model.Tugas;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class TugasService {

    public Response get(Long id) {
        Peserta peserta = Peserta.findById(id);
        if (peserta == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<Tugas> tugasList = Tugas.find("peserta_id = ?1", peserta.id).list();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Tugas tugas : tugasList){
            Map<String,Object> map = new HashMap<>();
            map.put("id",tugas.id);
            map.put("name",tugas.name);
            map.put("peserta_id",id);
            map.put("score",tugas.score);

            result.add(map);
        }
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @Transactional
    public Response post(Long id,  Map<String, Object> request) {
        Peserta peserta = Peserta.findById(id);
        if (peserta == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Tugas tugas = new Tugas();
        tugas.name = request.get("name").toString();
        tugas.score = Integer.parseInt(request.get("score").toString());
        tugas.peserta = peserta;

        //save to database
        tugas.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }
}
