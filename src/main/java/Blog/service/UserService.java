package Blog.service;

import Blog.model.User;
import Blog.web.dto.UserRegistrationDto;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

//    void saveUser (User user);
    User findByUsername(String username);
//    void deleteUserById (Long id);
//    List <User> getAllUsers ();
    void save(UserRegistrationDto registration);

}