package uz.br29.appschool.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.br29.appschool.security.BaseEntity;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "_degree")
@Builder
public class Degree extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double pricePerAcademicHour;


}
