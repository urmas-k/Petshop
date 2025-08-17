package eu.urmas.petshop.controller.pet;

import eu.urmas.petshop.controller.pet.dto.PetInfo;
import eu.urmas.petshop.infrastructure.rest.error.ApiError;
import eu.urmas.petshop.controller.pet.dto.PetDto;
import eu.urmas.petshop.service.pet.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/pet")
    @Operation(summary = "Adds a pet", description = "Adds a pet. Throws error ’PetType not found’ if petType is not found from system")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK"), @ApiResponse(responseCode = "400", description = "PetType not foud", content = @Content(schema = @Schema(implementation = ApiError.class))),

    })
    public void addPet(@RequestBody @Valid PetDto petDto) {
        petService.addPet(petDto);

    }

    @GetMapping("/pet/{petId}")
    @Operation(summary = "Finds a pet by its ID", description = "Finds a pet by its ID, if no match is found then error is thrown")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ok"), @ApiResponse(responseCode = "404", description = "Pet does not exist", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public PetDto getPet(@PathVariable Integer petId) {
        return petService.findPet(petId);
    }

    @GetMapping("/pets")
    @Operation(summary = "Finds all pets")
    public List<PetInfo> findAllPets() {
        return petService.findAllPets();
    }

    @PutMapping("/pet/{petId}")
    @Operation(summary = "Updates a pet", description = "if there are any null value fields, those wont`t get updated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request body: payload validation failed",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Pet does not exit / PetType not found",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })

    public void updatePet(@PathVariable Integer petId ,@RequestBody @Valid PetDto petDto) {
petService.updatePet(petId, petDto);
    }


}
