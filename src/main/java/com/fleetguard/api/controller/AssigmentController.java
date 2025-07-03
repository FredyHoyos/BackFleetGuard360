package com.fleetguard.api.controller;

import com.fleetguard.api.DTO.CreateAssigmentDTO;
import com.fleetguard.api.DTO.GetAssigmentDTO;
import com.fleetguard.api.mapper.AssigmentMapper;
import com.fleetguard.api.model.Assigment;
import com.fleetguard.api.service.AssigmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Assigment")
@RestController
@RequestMapping("/api/assigment")
public class AssigmentController {
    private final AssigmentService assigmentService;
    private final AssigmentMapper assigmentMapper;

    public AssigmentController(AssigmentService assigmentService, AssigmentMapper assigmentMapper) {
        this.assigmentService = assigmentService;
        this.assigmentMapper = assigmentMapper;
    }

    @Operation(
            summary = "Get all assigments",
            description = "Este endpoint retorna todos los turnos asignados creados",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/all")
    public List<GetAssigmentDTO> getAll() {
        return assigmentService.getAllAssigments();
    }

    @Operation(
            summary = "Register for the assigments",
            description = "Este endpoint permite registrar a las asiganciones de los turnos",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/register")
    public @ResponseBody GetAssigmentDTO register(@RequestBody CreateAssigmentDTO assigment) {
        return assigmentService.createAssigment(assigment);
    }

    @Operation(
            summary = "Update assigment",
            description = "Este endpoint permite actualizar a una asignación por el id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{id}")
    public @ResponseBody GetAssigmentDTO update(@PathVariable Long id, @RequestBody CreateAssigmentDTO dto) {
        return assigmentService.updateAssigment(id, dto);
    }

    @Operation(
            summary = "Delete assigment",
            description = "Este endpoint permite eliminar a una asiganción por el id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/{id}")
    public @ResponseBody String delete(@PathVariable int id) {
        return assigmentService.deleteAssigment(id);
    }
}
