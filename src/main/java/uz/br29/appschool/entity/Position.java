package uz.br29.appschool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import uz.br29.appschool.security.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "_position")
@Builder
public class Position extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
