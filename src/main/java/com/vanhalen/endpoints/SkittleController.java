package com.vanhalen.endpoints;

import com.vanhalen.domain.Skittle;
import com.vanhalen.interfaces.SkittleLogicInterface;
import com.vanhalen.interfaces.MqttServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/skittles")
public class SkittleController {

    private SkittleLogicInterface _skittleLogic;
    private MqttServiceInterface _mqttService;

    @Autowired
    public SkittleController(SkittleLogicInterface skittleLogic, MqttServiceInterface mqttService){
        _skittleLogic = skittleLogic;
        _mqttService = mqttService;
    }

//    @ApiOperation(value = "Use username and password to obtain JWT bearer token", response = String.class)
//    @PostMapping("/sort")
//    public ResponseEntity SortSkittles(@RequestBody String color){
//        try{
//            _mqttService.publishMessage("VanHalen/Vending", String.valueOf(color).getBytes());
//            return ResponseEntity.status(HttpStatus.OK).body("Mqtt message sent");
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//        }
//    }

    @PostMapping("/sort")
    public ResponseEntity SortSkittles(@RequestBody Skittle skittle) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Mqtt message sent");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

//    @GetMapping("/get")
//    public ResponseEntity GetSkittles() {
//        t
//    }
}
