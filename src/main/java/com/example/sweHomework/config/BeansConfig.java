package com.example.sweHomework.config;

import com.example.sweHomework.beans.DataBaseBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean(name = "db")
    public DataBaseBean dataBaseBean(){
        return new DataBaseBean();
    }

}
