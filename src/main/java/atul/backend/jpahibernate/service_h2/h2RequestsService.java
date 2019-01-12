package atul.backend.jpahibernate.service_h2;

import atul.backend.jpahibernate.entities_h2.UserLogin;
import atul.backend.jpahibernate.model_h2.UserDetailsH2;

import java.util.List;
public interface h2RequestsService {

    List<UserDetailsH2> fetchUserDetails(String id);
    UserLogin save(UserDetailsH2 user);
    List<UserLogin> findAll();
    void delete(int id);

    UserLogin findOne(String username);

    UserLogin findById(int id);

    UserDetailsH2 update(UserDetailsH2 userDto);
}
