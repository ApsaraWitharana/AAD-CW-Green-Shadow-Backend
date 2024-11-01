package lk.ijse.gdse68.greenshadowbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crop")
public class Crop {
    @Id
    @Column(name = "crop_code", nullable = false, length = 50)
    private String cropCode;  // Unique code for each crop, e.g., CRP-001

    @Column(name = "crop_common_name", nullable = false, length = 100)
    private String cropCommonName;

    @Column(name = "crop_scientific_name", nullable = false, length = 100)
    private String cropScientificName;

    @Column(name = "crop_image", columnDefinition = "LONGTEXT")
    private String cropImage;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "crop_season", nullable = false, length = 50)
    private String cropSeason;

    // Foreign Key to Field
    @ManyToOne
    @JoinColumn(name = "field_code", nullable = false)
    private Field field;
}
