package uz.axonlogic.paymeintegration.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity(name = "card")
public class CardEntity {

    @Id
    private String id ;

    @Column(name = "number")
    private String number;

}