package com.example.secretsanta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ParticipantDTO {
    private Long id;
    private String name;
    private String wish;
}