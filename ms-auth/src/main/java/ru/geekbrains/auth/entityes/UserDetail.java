package ru.geekbrains.auth.entityes;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Data
@Entity
@Table(name = "users_details")
public class UserDetail {
    public UserDetail(){
        this.addresses=new ArrayList<>();
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "birthdate")
    private LocalDateTime birthDate;
    @Column(name = "gender")
    private String gender;
    @Column(name = "status")
    private String status;
    @Column(name = "delete_at")
    private LocalDateTime delete_at;
    @OneToOne (optional = false,mappedBy = "user_details")
    private User owner;

    @ManyToMany
    @JoinTable(name = "details_addresses",
            joinColumns = @JoinColumn(name = "details_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Collection<Address> addresses;
}
