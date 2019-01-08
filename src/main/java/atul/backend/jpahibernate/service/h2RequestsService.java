package atul.backend.jpahibernate.service;

import atul.backend.jpahibernate.model_h2.UserDetailsH2;
import org.springframework.stereotype.Service;

import java.util.List;
public interface h2RequestsService {

    List<UserDetailsH2> fetchUserDetails(String id);
}
