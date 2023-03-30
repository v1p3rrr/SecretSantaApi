package com.example.secretsanta.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "participant")
public class Participant {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name = "wish")
    private String wish;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Participant recipient;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "group_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Group group;
}
