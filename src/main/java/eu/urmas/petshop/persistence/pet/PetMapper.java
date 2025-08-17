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
    @Mapping(source = "price", target = "price")
    @Mapping(source = "birthDate", target = "birthDate")
    PetDto toPetDto(Pet pet);

    @Mapping(source = "petType", target = "petType")
    @Mapping(source = "name", target = "petName")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "id", target = "petId")
    PetInfo toPetInfo(Pet pet);

    List<PetInfo> toPetInfos(List<Pet> pets);

    @Mapping(source = "petName", target = "name")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "price", target = "price")
    @Mapping(target = "petType", ignore = true)
    Pet toPet(PetDto petDto);

    default String map(PetType petType) {
        return petType != null ? petType.getTypeName() : null;
    }

    @InheritConfiguration(name = "toPet")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pet updatePet(PetDto petDto, @MappingTarget Pet pet);
}