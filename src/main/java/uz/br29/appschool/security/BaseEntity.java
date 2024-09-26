package uz.br29.appschool.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//bu anotatsiya qachonki o'zgarrish bo'lgan yoki yaratilgan momentda auduting amallarini bajarish un
public abstract class BaseEntity { // barcha entitylarda mavjud bo'lgan umumiy fieldlar

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; //entityning id.si ya'ni takrorlanmas fildi

    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;// yaratilgan oxirgi vaqti

    @LastModifiedDate
    @JsonIgnore
    private Timestamp updatedDate; // o'zgartirilgan vaqti

    @CreatedBy
    @JsonIgnore
    private Long createdBy;//Kim create qilganini(yaratganini) id.sini olib ketadi

    @LastModifiedBy
    @JsonIgnore
    private Long updatedBy; //kim update qilganini(o'zgartirganini) id.sini oladi


}