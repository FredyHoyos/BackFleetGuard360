package com.fleetguard.api.controller;

import com.fleetguard.api.model.Shift;
import com.fleetguard.api.service.ShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Shift")
@RestController
@RequestMapping("/api/shift")
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @Operation(
            summary = "Get all shfits",
            description = "Este endpoint retorna todos los turnos creados",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/all")
    public Iterable<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @Operation(
            summary = "Get shfit by id",
            description = "Este endpoint retorna el turnos correspondiente al id proporcionado",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/{id}")
    public Shift getShiftById(@PathVariable("id") Long id) {
        return shiftService.getShiftById(id);
    }

    @Operation(
            summary = "Register for the shift",
            description = "Este endpoint permite registrar a los turnos",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PostMapping("/register")
    public @ResponseBody Shift registerShift(@RequestBody Shift shift) {
        return shiftService.createShift(shift);
    }

    @Operation(
            summary = "Update shift",
            description = "Este endpoint permite actualizar a una ruta por el id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @PutMapping("/{id}")
    public @ResponseBody Shift updateShift(@PathVariable("id") Long id, @RequestBody Shift shift) {
        return shiftService.updateShift(id, shift);
    }

    @Operation(
            summary = "Delete shit",
            description = "Este endpoint permite eliminar a un turno por el id",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @DeleteMapping("/{id}")
    public @ResponseBody String deleteShift(@PathVariable("id") Long id) {
        return shiftService.deleteShift(id);
    }

}
