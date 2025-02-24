package eagle.tailung.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bank {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    String name;
    String branch;
    String ifscCode;
    @ManyToMany(mappedBy = "banks")
    List<Citizen> citizens;

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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(List<Citizen> citizens) {
        this.citizens = citizens;
    }
}
