package com.romvol.awapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Customer {

    @Id
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    private BigDecimal discount;
    @NotNull
    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
    private String phoneNumber;
}