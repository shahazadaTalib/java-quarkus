package eagle.tailung.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

@Entity
public class Aadhar {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
     Long aadharNumber;
     String company;
     @OneToOne
     @JsonBackReference
     Citizen citizen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(Long aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
}
