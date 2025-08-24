package eu.urmas.petshop.persistence.pet;

import eu.urmas.petshop.controller.pet.dto.PetDto;
import eu.urmas.petshop.controller.pet.dto.PetInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T14:29:09+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class PetMapperImpl implements PetMapper {

    @Override
    public PetDto toPetDto(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetDto petDto = new PetDto();

        petDto.setPetType( map( pet.getPetType() ) );
        petDto.setPetName( pet.getName() );
        petDto.setPrice( pet.getPrice() );
        petDto.setBirthDate( pet.getBirthDate() );

        return petDto;
    }

    @Override
    public PetInfo toPetInfo(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetInfo petInfo = new PetInfo();

        petInfo.setPetId( pet.getId() );
        petInfo.setPetType( map( pet.getPetType() ) );
        petInfo.setPetName( pet.getName() );
        petInfo.setPrice( pet.getPrice() );
        petInfo.setBirthDate( pet.getBirthDate() );

        return petInfo;
    }

    @Override
    public List<PetInfo> toPetInfos(List<Pet> pets) {
        if ( pets == null ) {
            return null;
        }

        List<PetInfo> list = new ArrayList<PetInfo>( pets.size() );
        for ( Pet pet : pets ) {
            list.add( toPetInfo( pet ) );
        }

        return list;
    }

    @Override
    public Pet toPet(PetDto petDto) {
        if ( petDto == null ) {
            return null;
        }

        Pet pet = new Pet();

        pet.setName( petDto.getPetName() );
        pet.setBirthDate( petDto.getBirthDate() );
        pet.setPrice( petDto.getPrice() );

        return pet;
    }

    @Override
    public Pet updatePet(PetDto petDto, Pet pet) {
        if ( petDto == null ) {
            return pet;
        }

        if ( petDto.getPetName() != null ) {
            pet.setName( petDto.getPetName() );
        }
        if ( petDto.getBirthDate() != null ) {
            pet.setBirthDate( petDto.getBirthDate() );
        }
        if ( petDto.getPrice() != null ) {
            pet.setPrice( petDto.getPrice() );
        }

        return pet;
    }
}
