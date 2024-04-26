package com.bitespeed.identityreconciliation.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentifyContactRequestDto {
    private String email;
    private Integer phoneNumber;
}
