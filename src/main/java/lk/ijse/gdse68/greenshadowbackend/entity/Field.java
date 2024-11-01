package lk.ijse.gdse68.greenshadowbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "field_code", nullable = false, length = 50)
    private String fieldCode;

    @Column(name = "field_name", nullable = false, length = 100)
    private String fieldName;

    @Column(name = "field_location", nullable = true, columnDefinition = "POINT")
    private String fieldLocation;

    @Column(name = "extent_size", nullable = false)
    private double extentSize;

    @Column(name = "field_image1", columnDefinition = "LONGTEXT")
    private String fieldImage1;       // Image URL or base64 encoding

    @Column(name = "field_image2", columnDefinition = "LONGTEXT")
    private String fieldImage2;

}
