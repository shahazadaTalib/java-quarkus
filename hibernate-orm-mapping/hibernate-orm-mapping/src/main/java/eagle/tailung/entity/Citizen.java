package eagle.tailung.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Citizen {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    String name;
    String gender;
    @OneToOne(mappedBy = "citizen",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    Aadhar aadhar;

    @OneToMany(mappedBy = "citizen",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    List<SimCard> simCards;

    @ManyToMany
    List<Bank> banks;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Aadhar getAadhar() {
        return aadhar;
    }

    public void setAadhar(Aadhar aadhar) {
        this.aadhar = aadhar;
    }

    public List<SimCard> getSimCards() {
        return simCards;
    }

    public void setSimCards(List<SimCard> simCards) {
        this.simCards = simCards;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }
}
