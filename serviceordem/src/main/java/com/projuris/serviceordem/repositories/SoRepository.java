package com.projuris.serviceordem.repositories;

import com.projuris.serviceordem.models.SoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
public interface SoRepository extends JpaRepository<SoModel, UUID>, JpaSpecificationExecutor<SoModel> {
  @Query(value="select max(so_Number) from tb_so",nativeQuery = true)
  Long getMaxSoNumber();

}
