package demo.test.app.config.broker;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import demo.test.app.model.Log;
import demo.test.app.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author js
 */

@Service
public class Consume {
    
    @Autowired
    LogRepository logRepository;
    
    Logger logger = LoggerFactory.getLogger(Consume.class);
    
    @RabbitListener(queues = "LOG_REPORT")
    public void LOG_REPORT(String msg){
        logger.info("Msg receive : "+msg);
        createLog(msg);
    }
    
    private void createLog(String data){
        Gson gson = new Gson();
        JsonObject strToJson = gson.fromJson(data,JsonObject.class);
        String resi = String.valueOf(strToJson.get("resi")).replaceAll("\"", "");
        String status = String.valueOf(strToJson.get("status")).replaceAll("\"", "");
        String createdBy = String.valueOf(strToJson.get("createdBy")).replaceAll("\"", "");
        try {
            Log log = new Log();
            log.setResi(resi);
            log.setCreatedBy(createdBy);
            log.setStatus(status);
            log.setPayload(data);
            logRepository.save(log);
            logger.info("Success to save log");
        }catch (Exception e){
            logger.info("Failed to save log");
        }
        
    };
}