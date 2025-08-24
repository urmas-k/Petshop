package eu.urmas.petshop.controller.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetInfo extends PetDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer petId;
}
