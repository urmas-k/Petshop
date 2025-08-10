package eu.urmas.petshop.persistence.pet;

import eu.urmas.petshop.controller.pet.dto.PetDto;
import eu.urmas.petshop.controller.pet.dto.PetInfo;
import eu.urmas.petshop.persistence.pettype.PetType;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

    @Mapping(source = "petType", target = "petType")
    @Mapping(source = "name", target = "petName")
    @Mapping(target = "price", ignore = true)
    @Mapping(source = "birthDate", target = "birthDate")
    PetDto toPetDto(Pet pet);

    @InheritInverseConfiguration(name = "toPetDto")
    @Mapping(source = "id", target = "petId")
    PetInfo toPetInfo(Pet pet);

    List<PetInfo> toPetInfos(List<Pet> pets);
    
    default String map(PetType petType) {
        return petType != null ? petType.getName() : null;
    }
}