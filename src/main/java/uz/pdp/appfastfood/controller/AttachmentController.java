package uz.pdp.appfastfood.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.AttachmentDTO;
import uz.pdp.appfastfood.service.AttachmentService;
import uz.pdp.appfastfood.utils.AppConstants;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstants.BASE_PATH_V1 + "/attachment")
public class AttachmentController {
    public final AttachmentService attachmentService;


    @GetMapping("/{id}")
    public void read(@PathVariable Integer id, HttpServletResponse resp) {
        attachmentService.read(id, resp);
    }


    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).ATTACHMENT_CREATE.name())")
    @PostMapping
    public ApiResultDTO<List<AttachmentDTO>> create(HttpServletRequest req) {
        return attachmentService.create(req);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).ATTACHMENT_UPDATE.name())")
    @PutMapping("/{id}")
    public ApiResultDTO<AttachmentDTO> update(@PathVariable Integer id, HttpServletRequest req) {
        return attachmentService.update(id, req);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.appfastfood.enums.PermissionEnum).ATTACHMENT_DELETE.name())")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        attachmentService.delete(id);
    }
}
