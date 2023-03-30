package com.example.secretsanta.model.db;

import com.example.secretsanta.model.dto.GroupDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "groupp")
public class Group {
    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    @NotBlank
    @Column(name="name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "groupp", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("groupp")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Participant> participants;

    public void setParticipants(List<Participant> participants) {
        if (participants!=null){
            participants.forEach( participant -> participant.setGroupp(this));
            this.participants=participants;
        }
    }

    public void updateFromDTO(GroupDTO groupDTO){
        if (groupDTO.getName()!=null && !groupDTO.getName().isBlank()){
            this.setName(groupDTO.getName());
        }
        if (groupDTO.getDescription()!=null){
            this.setDescription(groupDTO.getDescription());
        }
    }

    public GroupDTO toDTO(){
        return new GroupDTO(id = getId(), name = getName(), description = getDescription());
    }
}
