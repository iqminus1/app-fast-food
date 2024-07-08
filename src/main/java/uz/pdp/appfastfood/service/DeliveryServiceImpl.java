package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Delivery;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.DeliveryCrudDTO;
import uz.pdp.appfastfood.payload.DeliveryDTO;
import uz.pdp.appfastfood.repository.DeliveryRepository;
import uz.pdp.appfastfood.repository.FilialRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final FilialRepository filialRepository;

    @Override
    public ApiResultDTO<List<DeliveryDTO>> read() {
        return ApiResultDTO.success(deliveryRepository.findAll().stream().map(this::toDTO).toList());
    }

    @Override
    public ApiResultDTO<DeliveryDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(deliveryRepository.find(id)));
    }

    @Override
    public ApiResultDTO<DeliveryDTO> create(DeliveryCrudDTO crudDTO) {
        Delivery delivery = new Delivery();
        updateEntity(delivery, crudDTO);
        deliveryRepository.save(delivery);
        return ApiResultDTO.success(toDTO(delivery));
    }

    @Override
    public ApiResultDTO<DeliveryDTO> update(DeliveryCrudDTO crudDTO, Integer id) {
        Delivery delivery = deliveryRepository.find(id);
        updateEntity(delivery, crudDTO);
        deliveryRepository.save(delivery);
        return ApiResultDTO.success(toDTO(delivery));
    }

    @Override
    public void delete(Integer id) {
        filialRepository.deleteById(id);
    }

    private void updateEntity(Delivery delivery, DeliveryCrudDTO crudDTO) {
        delivery.setFilial(filialRepository.find(crudDTO.getFilialId()));
        delivery.setPriceKm(crudDTO.getPriceKm());
        delivery.setMinPrice(crudDTO.getMinPrice());
        delivery.setTextRu(crudDTO.getTextRu());
        delivery.setTextUz(crudDTO.getTextUz());
    }

    private DeliveryDTO toDTO(Delivery delivery) {
        return new DeliveryDTO(delivery.getId(), delivery.getFilial().getId(), delivery.getPriceKm(), delivery.getMinPrice(), delivery.getTextUz(), delivery.getTextRu());
    }
}
