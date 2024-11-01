package lk.ijse.gdse68.greenshadowbackend.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class AppUtil {

    public static String toBase64ProfilePic(MultipartFile profilePic){
        String proPicBase64=null;
        try{
            byte[] imageByteCollection= profilePic.getBytes();
            proPicBase64= Base64.getEncoder().encodeToString(imageByteCollection);

        }catch(Exception e ){
            e.printStackTrace();
        }
        return proPicBase64;
    }
}
