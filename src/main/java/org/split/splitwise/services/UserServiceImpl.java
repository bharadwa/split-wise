package org.split.splitwise.services;

import org.split.splitwise.exceptions.UserAlreadyExistsException;
import org.split.splitwise.models.User;
import org.split.splitwise.models.UserStatus;
import org.split.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerUser(String name, String email, String phoneNumber, String password) throws UserAlreadyExistsException {
        if(this.userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        User user =new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        user.setUserStatus(UserStatus.ACTIVE);
        return userRepository.save(user);
    }
}
