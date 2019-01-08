package atul.backend.jpahibernate.service.impl;

import atul.backend.jpahibernate.entities_h2.UserLogin;
import atul.backend.jpahibernate.model_h2.UserDetailsH2;
import atul.backend.jpahibernate.repository_h2.UserLoginRepository;
import atul.backend.jpahibernate.service.h2RequestsService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class h2RequestServiceImpl implements h2RequestsService {

    static final Logger logger = Logger.getLogger(h2RequestServiceImpl.class.getName());
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public List<UserDetailsH2> fetchUserDetails(String id) {
        List<UserDetailsH2> userDetailsH2s;
        logger.info("select * from USERLOGIN  ul where ul.firstname " + id);
        List<UserLogin> userLogin = userLoginRepository.findAllUsers(id);
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(
                UserLogin.class, UserDetailsH2.class
        ).byDefault().register();

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        userDetailsH2s = mapperFacade.mapAsList(userLogin, UserDetailsH2.class);
        return userDetailsH2s;
    }
}
