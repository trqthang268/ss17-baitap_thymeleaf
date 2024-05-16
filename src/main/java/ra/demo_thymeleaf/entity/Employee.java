package ra.demo_thymeleaf.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(name = "employee_name")
    @NotEmpty(message = "Employee name is empty")
    private String employeeName;
    @Column(name = "address")
    @NotEmpty(message = "Employee address is empty")
    private String address;
    @Column(name = "phone")
    @NotEmpty(message = "Employee phone is empty")
    private String phone;
    @Column(name = "dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Employee Date Of Birth is empty")
    @Past(message = "Date Of Birth is not valid")
    private Date dateOfBirth;

}
