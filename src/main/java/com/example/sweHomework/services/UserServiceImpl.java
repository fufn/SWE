package com.example.sweHomework.services;

import com.example.sweHomework.entities.Roles;
import com.example.sweHomework.entities.Users;
import com.example.sweHomework.repositories.RoleRepository;
import com.example.sweHomework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(s);

        if(user!=null){
            return user;
        }else{
            throw new UsernameNotFoundException("USER NOT FOUND");
        }

    }

    @Override
    public Users getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users addUser(Users user) {

        Users checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            Roles userRole = roleRepository.findByRole("ROLE_GUEST");
            ArrayList<Roles> roles = new ArrayList<>();
            roles.add(userRole);
            user.setRoles(roles);
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }
}
