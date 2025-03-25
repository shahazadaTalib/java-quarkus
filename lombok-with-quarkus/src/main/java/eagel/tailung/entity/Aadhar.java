package eagel.tailung.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.arc.All;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Aadhar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long aadharNumber;
    String company;
    @OneToOne
    @JsonBackReference
    Citizen citizen;

}
