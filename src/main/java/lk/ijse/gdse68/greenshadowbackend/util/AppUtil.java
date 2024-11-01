package lk.ijse.gdse68.greenshadowbackend.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

public class AppUtil {

    public static String[] toBase64ProfilePic(MultipartFile fieldImage1, MultipartFile fieldImage2) {
        String[] base64Images = new String[2];
        try {
            byte[] imageBytes1 = fieldImage1.getBytes();
            byte[] imageBytes2 = fieldImage2.getBytes();

            base64Images[0] = Base64.getEncoder().encodeToString(imageBytes1); // Base64 for first image
            base64Images[1] = Base64.getEncoder().encodeToString(imageBytes2); // Base64 for second image

        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Images;
    }

}
