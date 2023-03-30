package com.example.secretsanta.misc.responce_message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DefaultResponseMessage {
    private Integer status;
    private String path;
    private String error;
}
