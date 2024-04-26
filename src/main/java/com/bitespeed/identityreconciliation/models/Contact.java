package com.bitespeed.identityreconciliation.models;


import com.bitespeed.identityreconciliation.models.enums.LinkPrecedence;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact extends BaseModel {

    private String phoneNumber;
    private String email;
    private Integer linkedId;
    private LinkPrecedence linkPrecedence;

}
