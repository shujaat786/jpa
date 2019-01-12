package atul.backend.jpahibernate.repository_h2;

import atul.backend.jpahibernate.entities_h2.UserLogin;
import atul.backend.jpahibernate.repository.JPAHibernateRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserLoginRepository extends JPAHibernateRepository<UserLogin, Integer> {

    @Query(value = "select * from USERLOGIN  ul where ul.firstname = ?1", nativeQuery = true)
    List<UserLogin> findAllUsers(String id);

    UserLogin findByUsername(String username);
}
