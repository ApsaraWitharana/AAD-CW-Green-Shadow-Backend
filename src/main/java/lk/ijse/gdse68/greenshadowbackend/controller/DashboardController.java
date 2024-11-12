package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.DashResDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.ResponseDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffDTO;
import lk.ijse.gdse68.greenshadowbackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@CrossOrigin(origins = "*")
public class DashboardController {
    @Autowired
    private StaffService service;
    @Autowired
    private DashResDTO responseDTO;

    @GetMapping
    public String newMethod(){
        return "Green Shadow!";
    }
    @GetMapping("/getStaff")
      public ResponseEntity<DashResDTO> gerAllStaff(){
        try {
            List<StaffDTO> staffDTOList = service.getAllStaff();
            responseDTO.setCode(HttpStatus.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(staffDTOList);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);

        }catch (Exception e){
            responseDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
}

}
