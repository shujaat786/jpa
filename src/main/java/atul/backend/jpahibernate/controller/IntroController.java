package atul.backend.jpahibernate.controller;

import atul.backend.jpahibernate.model.Details;
import atul.backend.jpahibernate.model.HeaderModel;
import atul.backend.jpahibernate.model.Request;
import atul.backend.jpahibernate.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/staticDataFeeding")
public class IntroController {

    @GetMapping("/intro/{id}")
    public String getIntro(@PathVariable String id){
        return "Hello" + " " + id;
    }

    @RequestMapping(value = "/details",  method = RequestMethod.POST)
    public ResponseEntity<Response<Details>> getDetails (@RequestBody Request<String> fetchDetails) {
        Response<Details> response = new Response<>();
        Details details = new Details();
        details.setCompany("Capgemini");
        details.setName("Atul Pandey");
        response.setResponse(details);
        fetchDetails.getHeaderModel().setUserId(fetchDetails.getPayLoad());
        response.setHeaderModel(fetchDetails.getHeaderModel());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
