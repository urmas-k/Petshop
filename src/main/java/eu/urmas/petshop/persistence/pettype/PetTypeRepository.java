package eu.urmas.petshop.persistence.pettype;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PetTypeRepository extends Repository<PetType, Integer> {

    @Query("select p from PetType p where upper(p.typeName) = upper(:typeName)")
    Optional<PetType> findPetTypeBy(@Param("typeName") String typeName);
}