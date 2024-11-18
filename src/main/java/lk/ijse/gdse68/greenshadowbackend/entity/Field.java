package lk.ijse.gdse68.greenshadowbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "field_code",nullable = false)
    private String fieldCode;

    @Column(name = "field_name", nullable = false, length = 100)
    private String fieldName;

    @Column(name = "field_location", nullable = true)
    private String fieldLocation;

    @Column(name = "extent_size", nullable = false)
    private Double extentSize;

    @Column(name = "field_image1", columnDefinition = "LONGTEXT")
    private String fieldImage1;       // Image URL or base64 encoding

    @Column(name = "field_image2", columnDefinition = "LONGTEXT")
    private String fieldImage2;
    // One-to-Many relationship with Crop
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Crop> crops;
    // One-to-Many relationship with Equipment
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Equipment> equipmentList;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<StaffFieldDetails> staffFieldDetails;


    public Field(String fieldCode, String fieldName) {
        this.fieldCode = fieldCode;
        this.fieldName = fieldName;
    }
}
