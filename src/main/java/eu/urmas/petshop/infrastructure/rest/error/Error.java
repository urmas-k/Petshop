package eu.urmas.petshop.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    NO_PET_EXISTS("Pet does not exist"),
    NO_PET_TYPE_EXISTS("PetType not found");
   
    private final String message;
}