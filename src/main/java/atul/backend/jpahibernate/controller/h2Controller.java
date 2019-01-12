package atul.backend.jpahibernate.controller;

import atul.backend.jpahibernate.model.Response;
import atul.backend.jpahibernate.model_h2.UserDetailsH2;
import atul.backend.jpahibernate.service_h2.h2RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/h2")
public class h2Controller {
    @Autowired
    h2RequestsService h2RequestsService;

    @PostMapping("/details/{id}")
    public ResponseEntity<Response<List<UserDetailsH2>>> getIntro(@PathVariable String id){
        Response<List<UserDetailsH2>> h2Response = new Response<>();
        h2Response.setResponse(h2RequestsService.fetchUserDetails(id));
        h2Response.setHeaderModel(null);
        return new ResponseEntity<>(h2Response, HttpStatus.OK);
    }
}
