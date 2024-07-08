package uz.pdp.appfastfood.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Attachment;
import uz.pdp.appfastfood.mapper.AttachmentMapper;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.AttachmentDTO;
import uz.pdp.appfastfood.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;
    @Value("${app.base-path}")
    private String BASE_PATH;

    @Override
    public void read(Integer id, HttpServletResponse resp) {
        try {
            Attachment attachment = attachmentRepository.find(id);
            resp.setContentType(attachment.getContentType());
            Files.copy(Path.of(attachment.getPath()), resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiResultDTO<List<AttachmentDTO>> create(HttpServletRequest req) {
        List<AttachmentDTO> attachments = new ArrayList<>();
        try {
            for (Part part : req.getParts()) {
                attachments.add(createOrUpdate(new Attachment(), false, part));
            }
            return ApiResultDTO.success(attachments);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ApiResultDTO<AttachmentDTO> update(Integer id, HttpServletRequest req) {
        Attachment attachment = attachmentRepository.find(id);
        try {
            Collection<Part> parts = req.getParts();
            if (parts.size() == 1) {
                for (Part part : parts) {
                    return ApiResultDTO.success(createOrUpdate(attachment, true, part));
                }
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException(HttpStatus.BAD_REQUEST.name());
    }

    @Override
    public void delete(Integer id) {
        attachmentRepository.deleteById(id);
    }

    private AttachmentDTO createOrUpdate(Attachment attachment, boolean update, Part part) {
        if (update) {
            try {
                Files.delete(Path.of(attachment.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {

            String contentType = part.getContentType();
            String originalName = part.getSubmittedFileName();
            long size = part.getSize();

            String[] split = originalName.split("\\.");
            String s = split[split.length - 1];
            UUID uuid = UUID.randomUUID();

            String name = uuid + "." + s;

            String pathString = BASE_PATH + "/" + name;

            Files.copy(part.getInputStream(), Path.of(pathString));

            attachment.setName(name);
            attachment.setSize(size);
            attachment.setPath(pathString);
            attachment.setContentType(contentType);
            attachment.setOriginalName(originalName);

            attachmentRepository.save(attachment);

            return attachmentMapper.toDTO(attachment);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
