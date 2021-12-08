package com.example.sweHomework.config;

import com.example.sweHomework.beans.DataBaseBean;
import com.example.sweHomework.services.UserService;
import com.example.sweHomework.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean(name = "db")
    public DataBaseBean dataBaseBean(){
        return new DataBaseBean();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

}
