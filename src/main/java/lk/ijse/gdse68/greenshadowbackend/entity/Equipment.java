package lk.ijse.gdse68.greenshadowbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @Column(name = "e_id", nullable = false, length = 50)
    private String id;

    @NotBlank(message = "Equipment name is required")
    @Size(max = 100, message = "Equipment name must be less than 100 characters")
    @Column(name = "e_name", nullable = false)
    private String name;

    @NotBlank(message = "Type is required")
    @Size(max = 50, message = "Type must be less than 50 characters")
    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must be less than 20 characters")
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_code", nullable = false)
    private Field field;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;


}
