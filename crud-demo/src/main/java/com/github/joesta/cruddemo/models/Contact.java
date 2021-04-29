package com.github.joesta.cruddemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable {
    private String emailAddress;
    private String cellPhone;
}
