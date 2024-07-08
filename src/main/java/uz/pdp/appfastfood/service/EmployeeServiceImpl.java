package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Employee;
import uz.pdp.appfastfood.entity.User;
import uz.pdp.appfastfood.enums.PermissionEnum;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.EmployeeCrudDTO;
import uz.pdp.appfastfood.payload.EmployeeDTO;
import uz.pdp.appfastfood.repository.EmployeeRepository;
import uz.pdp.appfastfood.repository.UserRepository;

import java.util.List;

import static uz.pdp.appfastfood.utils.AppConstants.checkPermission;
import static uz.pdp.appfastfood.utils.AppConstants.getUser;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResultDTO<List<EmployeeDTO>> readAll() {
        return ApiResultDTO.success(employeeRepository.findAll().stream().map(this::toDTO).toList());
    }

    @Override
    public ApiResultDTO<EmployeeDTO> readByUserId(Integer userId) {
        try {
            checkPermission(PermissionEnum.EMPLOYEE_READ);
            return ApiResultDTO.success(toDTO(employeeRepository.findEmployeeByUserId(userId)));
        } catch (Exception e) {
            Employee employee = employeeRepository.findEmployeeByUserId(userId);
            User user = getUser();
            if (!employee.getUser().equals(user)) {
                throw new RuntimeException();
            }
            return ApiResultDTO.success(toDTO(employee));
        }
    }

    @Override
    public ApiResultDTO<EmployeeDTO> create(EmployeeCrudDTO crudDTO) {
        User user = userRepository.find(crudDTO.getUserId());
        Employee employee = new Employee(user, crudDTO.getType(), crudDTO.isAtWork());
        employeeRepository.save(employee);
        return ApiResultDTO.success(toDTO(employee));
    }

    @Override
    public ApiResultDTO<EmployeeDTO> update(EmployeeCrudDTO crudDTO, Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        if (crudDTO.getUserId() != null) {
            employee.setUser(userRepository.find(crudDTO.getUserId()));
        }
        employee.setType(crudDTO.getType());
        employee.setAtWork(crudDTO.isAtWork());
        employeeRepository.save(employee);
        return ApiResultDTO.success(toDTO(employee));
    }

    @Override
    public ApiResultDTO<EmployeeDTO> changeStatus() {
        User user = getUser();
        Employee employee = employeeRepository.findEmployeeByUserId(user.getId());
        employee.setAtWork(!employee.isAtWork());
        employeeRepository.save(employee);
        return ApiResultDTO.success(toDTO(employee));
    }

    @Override
    public void delete(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
    }

    private EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getUser().getId(), employee.getType(), employee.isAtWork());
    }
}
