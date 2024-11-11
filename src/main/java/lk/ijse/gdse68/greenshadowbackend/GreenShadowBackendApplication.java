package lk.ijse.gdse68.greenshadowbackend;

import jakarta.servlet.annotation.MultipartConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:63342")
@SpringBootApplication
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2MB //upload krn file eke -ekathuwa file wal 1MB nam eke file ek process krn use krnne ram ek ek 2 t adu unam primary memory eke //4MB unot ek use krnne  ek file system ek//tiyen mb gane ekk awot ek ram ekt ynw nattm threshold ekk dal  seconder memary ekt ynw
        maxFileSize = 1024 * 1024 * 10, // upload krn allow krn maximum file eke size eke //ex-10MB
        maxRequestSize = 1024 * 1024 * 50 // upload file ekth ekk sampurna data wal ekthuwa //50MB
)
public class GreenShadowBackendApplication{
    public static void main(String[] args) {
        SpringApplication.run(GreenShadowBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}

/**
 #* @author : sachini
 #* @date : 2024-10-28
 #**/