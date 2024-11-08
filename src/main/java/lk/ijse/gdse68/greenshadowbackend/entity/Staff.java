package lk.ijse.gdse68.greenshadowbackend.entity;
import jakarta.persistence.*;
import lk.ijse.gdse68.greenshadowbackend.util.GenderEnum;
import lk.ijse.gdse68.greenshadowbackend.util.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @Column(name = "staff_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "designation")
    private String designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "joined_date")
    private Date joinedDate;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "address_line_4")
    private String addressLine4;

    @Column(name = "address_line_5")
    private String addressLine5;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @OneToMany(mappedBy = "usedBy")
    private List<Vehicle> vehicles;

    @OneToOne(mappedBy = "staff", fetch = FetchType.LAZY)
    private Equipment equipment;

    //Many-to many relationship with staff
//    @ManyToMany
//    private List<Field> fields;
}
