package com.projuris.serviceordem.repositories;

import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.models.SoProgressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SoProgressRepository extends JpaRepository<SoProgressModel, UUID>, JpaSpecificationExecutor<SoProgressModel> {

    @Modifying
    @Query(value ="select * from tb_soprogress where sos_so_id= :soId",nativeQuery = true)
    List<SoProgressModel> FindAllSoSoProgress(@Param("soId") UUID soId);
}
