package com.example.secretsanta.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Participant {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name = "wish")
    private String wish;
    @OneToOne
    private Participant recipient;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;
}
