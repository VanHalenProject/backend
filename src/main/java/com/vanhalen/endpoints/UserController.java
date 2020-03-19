package com.vanhalen.endpoints;

import com.vanhalen.endpoints.viewmodels.auth.AuthRequestViewModel;
import com.vanhalen.interfaces.AuthLogicInterface;
import com.vanhalen.interfaces.UserLogicInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(tags = {"User management"})
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully executed request"),
        @ApiResponse(code = 400, message = "Failed to execute request"),
        @ApiResponse(code = 401, message = "Unauthorized: Invalid username or password"),
        @ApiResponse(code = 403, message = "Unauthorized: No access to resource"),
        @ApiResponse(code = 404, message = "Resource not found")
})
public class UserController {

    private UserLogicInterface _userLogic;
    private AuthLogicInterface _authLogic;

    @Autowired
    public UserController(UserLogicInterface userLogic, AuthLogicInterface authLogic) {
        _userLogic = userLogic;
        _authLogic = authLogic;
    }

    @ApiOperation(value = "Use username and password to obtain JWT bearer token", response = String.class)
    @PostMapping("/auth")
    public ResponseEntity authenticate(@RequestBody AuthRequestViewModel authRequestViewModel) {
        try {
            var bearerToken = _authLogic.authenticate(authRequestViewModel.getUsername(), authRequestViewModel.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(bearerToken);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
