package demo.test.app.consume;

import demo.test.app.dto.TaxDto;
import demo.test.app.service.TaxService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author js
 */

@Service
public class Listen {
    @Autowired
    TaxService taxService;
    
//    @RabbitListener(queues = "ALL_TASK")
//    public List<TaxDto> ALL_TASK(String msg){
//        List<TaxDto> dto = taxService.listTax(null);
//        return dto;
//    }
//
//    @RabbitListener(queues = "APPROVE_TASK")
//    public void APPROVE_TASK(String msg){
//        taxService.listTax("APPROVE");
//    }
}
