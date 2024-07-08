package uz.pdp.appfastfood.service;

import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.EmployeeCrudDTO;
import uz.pdp.appfastfood.payload.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    ApiResultDTO<List<EmployeeDTO>> readAll();

    ApiResultDTO<EmployeeDTO> readByUserId(Integer userId);

    ApiResultDTO<EmployeeDTO> create(EmployeeCrudDTO crudDTO);

    ApiResultDTO<EmployeeDTO> update(EmployeeCrudDTO crudDTO, Integer id);
    ApiResultDTO<EmployeeDTO> changeStatus();

    void delete(Integer id);
}
