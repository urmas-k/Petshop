package eu.urmas.petshop.service.pet;

import eu.urmas.petshop.controller.pet.dto.PetInfo;
import eu.urmas.petshop.infrastructure.rest.error.Error;
import eu.urmas.petshop.infrastructure.rest.exception.DataNotFoundException;
import eu.urmas.petshop.persistence.pet.Pet;
import eu.urmas.petshop.controller.pet.dto.PetDto;
import eu.urmas.petshop.persistence.pet.PetMapper;
import eu.urmas.petshop.persistence.pet.PetRepository;
import eu.urmas.petshop.persistence.pettype.PetType;
import eu.urmas.petshop.persistence.pettype.PetTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final PetTypeRepository petTypeRepository;


    public void addPet(PetDto petDto) {
        PetType petType = petTypeRepository.findPetTypeBy(petDto.getPetType()).orElseThrow(() -> new DataNotFoundException(Error.NO_PET_TYPE_EXISTS.getMessage()));
        Pet pet = petMapper.toPet(petDto);
        pet.setPetType(petType);
        petRepository.save(pet);
    }

    public PetDto findPet(Integer petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new DataNotFoundException(Error.NO_PET_EXISTS.getMessage()));
        return petMapper.toPetDto(pet);
    }

    public List<PetInfo> findAllPets() {
        List<Pet> pets = petRepository.findAll();
        return petMapper.toPetInfos(pets);
    }


}