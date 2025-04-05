package eagel.tailung.entity;

import io.quarkus.arc.All;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotBlank(message = "Name cannot be blank")
    @Length(min = 10,max = 50,message = "name should be between 10 char to 50 char max")
    String name;
    @Pattern(regexp = "[MF]",message = "Value can be either M or F")
    String gender;
    @Length(min = 12,max = 12,message = "add must be 12  chacter")
    String aadharNo;
    @Min(value = 10,message = "minimum age should be 10 year")
    @Max(value = 24,message = "maximum age should be 24 year.")

    int age;
}
