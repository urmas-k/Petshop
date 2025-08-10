package eu.urmas.petshop.persistence.pet;

import lombok.RequiredArgsConstructor;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

    @Mapping(source = "petType.name", target = "petType")
    @Mapping(source = "name", target = "petName")
    @Mapping(target = "price", ignore = true)
    @Mapping(source = "birthDate", target = "birthDate")
    PetDto toPetDto(Pet pet);

}