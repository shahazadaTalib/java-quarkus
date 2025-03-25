package eagel.tailung.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String gender;

    @JsonManagedReference
    @OneToOne(mappedBy = "citizen",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Aadhar aadhar;

}
