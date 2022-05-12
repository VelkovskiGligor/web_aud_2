package mk.ukim.finki.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Manufacturers")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "manufacturer_address")
    private String address;

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Manufacturer() {

    }
}
