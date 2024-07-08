package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.EmployeeCrudDTO;
import uz.pdp.appfastfood.service.EmployeeService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).EMPLOYEE_READ.name())")
    @GetMapping
    public ApiResultDTO<?> readAll() {
        return employeeService.readAll();
    }

    @GetMapping("/{userId}")
    public ApiResultDTO<?> readByUserId(@PathVariable Integer userId) {
        return employeeService.readByUserId(userId);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).EMPLOYEE_CREATE.name())")
    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid EmployeeCrudDTO crudDTO) {
        return employeeService.create(crudDTO);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).EMPLOYEE_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid EmployeeCrudDTO crudDTO, @PathVariable Integer id) {
        return employeeService.update(crudDTO, id);
    }

    @PutMapping
    public ApiResultDTO<?> changeStatus() {
        return employeeService.changeStatus();
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).EMPLOYEE_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.delete(id);
    }
}
