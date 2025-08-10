package eu.urmas.petshop.service.pet;

import eu.urmas.petshop.infrastructure.rest.error.ErrorCode; // was: Error
import eu.urmas.petshop.infrastructure.rest.exception.DataNotFoundException;
import eu.urmas.petshop.persistence.pet.Pet;
import eu.urmas.petshop.persistence.pet.PetDto;
import eu.urmas.petshop.persistence.pet.PetMapper;
import eu.urmas.petshop.persistence.pet.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public PetDto findPet(Integer petId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.NO_PET_EXISTS.getMessage()));
        return petMapper.toPetDto(pet);
    }
}