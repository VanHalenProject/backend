package com.vanhalen.endpoints;

import com.vanhalen.domain.Skittle;
import com.vanhalen.interfaces.SkittleLogicInterface;
import com.vanhalen.interfaces.MqttServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/skittles")
@Api(tags = {"Sort skittles"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
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

    @GetMapping("/get")
    public ResponseEntity GetSkittles() {
        try {
            var skittle = _skittleLogic.getSkittles();
            return ResponseEntity.status(HttpStatus.OK).body(skittle);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
