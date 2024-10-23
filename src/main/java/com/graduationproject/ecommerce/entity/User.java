package com.graduationproject.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private LocalDateTime accountCreationDate;
    private LocalDateTime accountUpdateDate;

    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private Auth auth;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Store store;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;
}