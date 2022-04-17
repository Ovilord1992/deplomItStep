package com.roculka.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_rating")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_get")
    private User userGet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_put")
    private User userPut;

    private Integer score;
    private String comment;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_create")
    private LocalDateTime dateCreate;


    @PrePersist
    private void setDateCreate(){
        dateCreate = LocalDateTime.now();
    }
}
