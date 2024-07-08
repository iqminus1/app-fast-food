package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.DeliveryCrudDTO;
import uz.pdp.appfastfood.payload.DeliveryDTO;

import java.util.List;

public interface DeliveryService {
    ApiResultDTO<List<DeliveryDTO>> read();

    ApiResultDTO<DeliveryDTO> read(Integer id);

    ApiResultDTO<DeliveryDTO> create(DeliveryCrudDTO crudDTO);

    ApiResultDTO<DeliveryDTO> update(DeliveryCrudDTO crudDTO, Integer id);

    void delete(Integer id);
}
