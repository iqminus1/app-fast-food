package uz.pdp.appfastfood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.appfastfood.entity.Attachment;
import uz.pdp.appfastfood.payload.AttachmentDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttachmentMapper {

    AttachmentDTO toDTO(Attachment attachment);
}