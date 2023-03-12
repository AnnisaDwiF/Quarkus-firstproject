package id.kawahedukasi.service;

import io.quarkus.arc.properties.IfBuildProperty;
import io.quarkus.scheduler.Scheduled;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import net.sf.jasperreports.engine.JRException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@ApplicationScoped
public class ScheduleService {

    @Inject
    MailService mailService;

    Logger logger = LoggerFactory.getLogger(ScheduleService.class);
    @Scheduled(every = "10s")
    public void generateKawahEdukasi(){
        logger.info("KawahEdukasi");
    }

    @Scheduled(cron = "0 21 12 * *")
    public void generateSendEmailListPeserta() throws JRException, IOException {
        mailService.sendExcelToEmail("annisadwifebryantipnp@gmail.com");
        logger.info("send email success!");

    }
}
