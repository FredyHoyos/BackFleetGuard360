package com.fleetguard.api.repository;

import com.fleetguard.api.model.Shift;
import org.springframework.data.repository.CrudRepository;

public interface ShiftRepository  extends CrudRepository<Shift, Long> {
}
