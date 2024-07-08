package uz.pdp.appfastfood.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.ThoughtCrudDTO;
import uz.pdp.appfastfood.service.ThoughtService;
import uz.pdp.appfastfood.utils.AppConstants;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/thought")
public class ThoughtController {
    private final ThoughtService thoughtService;

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).THOUGHT_READ)")
    @GetMapping
    public ApiResultDTO<?> read(@RequestParam int page, @RequestParam int size) {
        return thoughtService.read(page, size);
    }

    @GetMapping("/{id}")
    public ApiResultDTO<?> read(@PathVariable Integer id) {
        return thoughtService.read(id);
    }

    @PostMapping
    public ApiResultDTO<?> create(@RequestBody @Valid ThoughtCrudDTO crudDTO) {
        return thoughtService.create(crudDTO);
    }

    @PutMapping("/{id}")
    public ApiResultDTO<?> update(@RequestBody @Valid ThoughtCrudDTO crudDTO, @PathVariable Integer id) {
        return thoughtService.update(crudDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        thoughtService.delete(id);
    }
}
