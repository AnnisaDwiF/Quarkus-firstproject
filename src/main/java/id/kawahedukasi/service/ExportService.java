package id.kawahedukasi.service;

import id.kawahedukasi.model.Peserta;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ExportService {
    public Response exportPeserta() throws JRException {

        //load template jasper
        File file = new File("src/main/resources/TemplatePeserta.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        //create new datasource jasper for all data participant(peserta)
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Peserta.listAll());

//        Map<String, Object> param = new HashMap<>();
//        param.put("DATASOURCE", jrBeanCollectionDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), jrBeanCollectionDataSource);

        byte[] japerResult = JasperExportManager.exportReportToPdf(jasperPrint);

        return Response.ok().type("application/pdf").entity(japerResult).build();


    }
}
