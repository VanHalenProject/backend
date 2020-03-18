package com.vanhalen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
public class User {

    @Id
    @Getter
    private UUID id;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String password;
}
