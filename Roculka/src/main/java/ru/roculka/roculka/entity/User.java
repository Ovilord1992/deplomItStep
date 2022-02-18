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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_birth")
    private LocalDateTime dateBirth;
    @JsonIgnore
    private Integer age;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime date_create;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDateTime date_update;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_location")
    private UserLocation userLocation;

    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<UserRating> userRating;


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



    @PostLoad
    private void setAge(){
        if (dateBirth != null){
           Period age = Period.between(LocalDateTime.now().toLocalDate(), dateBirth.toLocalDate());
           this.age = age.getYears();
        }
    }
}
