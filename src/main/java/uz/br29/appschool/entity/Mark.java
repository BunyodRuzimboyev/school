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
@Table(name = "_mark")
@Builder
public class Mark extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Short quantity;


}
