package com.example.secretsanta.model.db;

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
public class Group {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name = "wish")
    private String wish;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @OneToOne
    private Participant recipient;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;
}
