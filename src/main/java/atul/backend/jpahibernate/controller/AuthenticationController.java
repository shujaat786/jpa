package atul.backend.jpahibernate.controller;

import atul.backend.jpahibernate.config.JwtTokenUtil;
import atul.backend.jpahibernate.entities_h2.UserLogin;
import atul.backend.jpahibernate.model.AuthToken;
import atul.backend.jpahibernate.model.LoginUser;
import atul.backend.jpahibernate.model.Request;
import atul.backend.jpahibernate.model.Response;
import atul.backend.jpahibernate.service_h2.impl.h2RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private h2RequestServiceImpl userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<Response<AuthToken>> register(@RequestBody Request<LoginUser> loginUser) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getPayLoad().getUsername(), loginUser.getPayLoad().getPassword()));
        final UserLogin user = userService.findOne(loginUser.getPayLoad().getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        Response<AuthToken> register = new Response<>();
        register.setResponse(new AuthToken(token, user.getUsername()));
        register.setHeaderModel(loginUser.getHeaderModel());
        return new ResponseEntity<>(register, HttpStatus.OK);
    }
}
