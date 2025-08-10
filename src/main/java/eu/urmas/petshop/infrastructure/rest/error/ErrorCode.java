package eu.urmas.petshop.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NO_PET_EXISTS("Pet does not exist");

    private final String message;
}