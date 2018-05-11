package com.romvol.awapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

import static com.romvol.awapp.domain.Procedure.Type.HAIRCUT;

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Procedure {

    @Id
    private String id;
    @NotBlank
    private BigDecimal price;
    private Type type = HAIRCUT;

    enum Type {
        HAIRCUT,
        NAILS
    }
}
