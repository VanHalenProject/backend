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

    @Autowired
    public SkittleController(SkittleLogicInterface skittleLogic){
        _skittleLogic = skittleLogic;
    }

    @PostMapping("/sort")
    public ResponseEntity SortSkittles(@RequestBody Skittle skittle) {
        try {
            if (_skittleLogic.sortSkittles(skittle))
                return ResponseEntity.status(HttpStatus.OK).body("Message sent");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send message");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

//    @GetMapping("/get")
//    public ResponseEntity GetSkittles() {
//        t
//    }
}
