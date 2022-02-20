package ru.roculka.roculka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private String phone;
    private String country;
    private String city;
    private String address;
    private Boolean isEnabled;
    private Boolean isActive;
    @Column(name = "active_code")
    private String activeCode;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    @Column(name = "date_birth")
    private LocalDateTime dateBirth;
    @Transient
    private Integer age;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime date_create;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime date_update;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_location")
    private UserLocation userLocation;

    @OneToMany(
            mappedBy = "userGet",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<UserRating> userRatingGet;

    @OneToMany(
            mappedBy = "userPut",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<UserRating> userRatingPut;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;


    public User(@NonNull String username, @NonNull String password, @NonNull String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @PrePersist
    private void setDateCreate(){
        date_create = LocalDateTime.now();
        date_update = LocalDateTime.now();
    }

    @PreUpdate
    private void setDateUpdate(){
        date_update = LocalDateTime.now();
    }


    @PostLoad
    private void setAge(){
        if (dateBirth != null){
           Period age = Period.between(LocalDateTime.now().toLocalDate(), dateBirth.toLocalDate());
           this.age = age.getYears();
        }
    }
}
