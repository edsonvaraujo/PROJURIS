package com.projuris.serviceordem.specifications;

import com.projuris.serviceordem.models.ClientModel;
import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.models.SoModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;

public class SpecificationTemplate {
    @And({
            @Spec(path = "equipmentBrand", spec = Like.class),
            @Spec(path = "equipmentModel", spec = Like.class)})

    public interface EquipmentSpec extends Specification<EquipmentModel> {}

    @And({
            @Spec(path = "clientName", spec = Like.class),
            @Spec(path = "clientTelephone", spec = Like.class),
            @Spec(path = "clientEmail", spec = Like.class)
    })

    public interface ClientSpec extends Specification<ClientModel> {}

    @And({
            @Spec(path = "osStatus", spec = Equal.class),
            @Spec(path = "soEquipments", spec = Like.class)
    })

    public interface SoSpec extends Specification<SoModel> {}
}
