package lk.ijse.gdse68.greenshadowbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GreenShadowBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenShadowBackendApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}


/**
 #* @author : sachini
 #* @date : 2024-10-28
 #**/