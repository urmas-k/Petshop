package eu.urmas.petshop.persistence.sale;

import eu.urmas.petshop.persistence.pet.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface SaleRepository extends Repository<Sale, Integer> {
    @Query("select s from Sale s where s.pet = :pet")
    Optional<Sale> findSaleBy(Pet pet);

    void delete(Sale sale);
}