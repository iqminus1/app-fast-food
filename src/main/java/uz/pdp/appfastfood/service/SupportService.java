package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.enums.SupportStatus;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.SupportCrudDTO;
import uz.pdp.appfastfood.payload.SupportDTO;

import java.util.List;

public interface SupportService {
    ApiResultDTO<List<SupportDTO>> allSMSFromUsers();

    ApiResultDTO<List<SupportDTO>> smsByType(SupportStatus status);

    ApiResultDTO<SupportDTO> sendSMS(SupportCrudDTO crudDTO);
    void deleteSMS(Integer id);
}
