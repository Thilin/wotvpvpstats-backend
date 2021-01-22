package com.mxh.wotvpvpstats.services.impl;

import com.mxh.wotvpvpstats.domains.User;
import com.mxh.wotvpvpstats.exceptions.EmailOrNickNameAlreadyInUseException;
import com.mxh.wotvpvpstats.exceptions.PasswordDifferenceException;
import com.mxh.wotvpvpstats.projections.dtos.UserDTO;
import com.mxh.wotvpvpstats.repositories.UserRepository;
import com.mxh.wotvpvpstats.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(UserDTO dto) {
        var optionalUser = userRepository.findByEmailOrNickName(dto.getEmail(), dto.getNickName());
        if(optionalUser.isPresent())
            throw new EmailOrNickNameAlreadyInUseException("Email/NickName Already In use");
        else{
            var user = new User();
            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setNickName(dto.getNickName());
            if(!dto.getPassword().equals(dto.getPasswordConfirmation()))
                throw new PasswordDifferenceException("The password and password confirmation do not match");
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(user);
        }
        return dto;
    }
}
