package atul.backend.jpahibernate.service_h2.impl;

import atul.backend.jpahibernate.entities_h2.UserLogin;
import atul.backend.jpahibernate.model_h2.UserDetailsH2;
import atul.backend.jpahibernate.repository_h2.UserLoginRepository;
import atul.backend.jpahibernate.service_h2.h2RequestsService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service(value = "userService")
public class h2RequestServiceImpl implements h2RequestsService, UserDetailsService {

    static final Logger logger = Logger.getLogger(h2RequestServiceImpl.class.getName());

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin user = userLoginRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<UserLogin> findAll() {
        List<UserLogin> list = new ArrayList<>();
        userLoginRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(int id) {
        userLoginRepository.deleteById(id);
    }

    @Override
    public UserLogin findOne(String username) {
        return userLoginRepository.findByUsername(username);
    }

    @Override
    public UserLogin findById(int id) {
        Optional<UserLogin> optionalUser = userLoginRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public UserDetailsH2 update(UserDetailsH2 userDto) {
        UserLogin user = findById(userDto.getId());
        if (user != null) {
            BeanUtils.copyProperties(userDto, user, "password");
            userLoginRepository.save(user);
        }
        return userDto;
    }

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

    @Override
    public UserLogin save(UserDetailsH2 user) {
        UserLogin newUser = new UserLogin();
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setAge(user.getAge());
        newUser.setSalary(user.getSalary());
        return userLoginRepository.save(newUser);
    }
}
