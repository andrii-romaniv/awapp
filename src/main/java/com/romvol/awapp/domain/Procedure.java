package com.romvol.awapp.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
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
    private Float price;
    @Builder.Default
    private Type type = HAIRCUT;

    enum Type {
        HAIRCUT,
        NAILS
    }
}
