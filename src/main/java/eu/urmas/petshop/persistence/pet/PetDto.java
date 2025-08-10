package eu.urmas.petshop.persistence.pet;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto implements Serializable {
    private String petType;
    private String petName;
    private LocalDate birthDate;
    private BigDecimal price;
}