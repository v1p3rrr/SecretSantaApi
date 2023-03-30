package com.example.secretsanta.model.dto;

import com.example.secretsanta.model.db.Participant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDTO {
    private Long id;
    private String name;
    private String wish;
    private ParticipantDTO recipient;

    public ParticipantDTO(Long id, String name, String wish, ParticipantDTO recipient) {
        this.id = id;
        this.name = name;
        this.wish = wish;
        this.recipient = recipient;
    }
}

//    private Long id;
//    private String name;
//    private String wish;
//    private Long recipientId;
//    private String recipientName;
//    private String recipientWish;
//
//    public ParticipantDTO(Long id, String name, String wish, Long recipientId, String recipientName, String recipientWish) {
//        this.id = id;
//        this.name = name;
//        this.wish = wish;
//        this.recipientId = recipientId;
//        this.recipientName = recipientName;
//        this.recipientWish = recipientWish;
//    }