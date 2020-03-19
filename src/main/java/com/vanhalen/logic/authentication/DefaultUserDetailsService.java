package com.vanhalen.logic.authentication;

import com.vanhalen.interfaces.UserLogicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private UserLogicInterface _userLogic;

    @Autowired
    public DefaultUserDetailsService(UserLogicInterface userLogic) {
        _userLogic = userLogic;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = _userLogic.findByUserName(userName);
        return user.orElseThrow();
    }
}
