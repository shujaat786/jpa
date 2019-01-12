package atul.backend.jpahibernate.controller;


import atul.backend.jpahibernate.entities_h2.UserLogin;
import atul.backend.jpahibernate.model.Request;
import atul.backend.jpahibernate.model.Response;
import atul.backend.jpahibernate.model_h2.UserDetailsH2;
import atul.backend.jpahibernate.service_h2.impl.h2RequestServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private h2RequestServiceImpl userService;

    @PostMapping("/saveNewUser")
    public ResponseEntity<Response<UserLogin>> saveUser(@RequestBody Request<UserDetailsH2> user) {
        Response<UserLogin> saveUserResponse = new Response<>();
        saveUserResponse.setResponse(userService.save(user.getPayLoad()));
        saveUserResponse.setHeaderModel(user.getHeaderModel());
        return new ResponseEntity<>(saveUserResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Response<List<UserLogin>>> listUser() {
        Response<List<UserLogin>> getUserList = new Response<>();
        getUserList.setResponse(userService.findAll());
        return new ResponseEntity<>(getUserList, HttpStatus.OK);
    }


   /* @GetMapping("/{id}")
    public ApiResponse<User> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }*/
}


