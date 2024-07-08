package uz.pdp.appfastfood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.appfastfood.entity.Filial;
import uz.pdp.appfastfood.payload.FilialCrudDTO;
import uz.pdp.appfastfood.payload.FilialDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilialMapper {
    FilialDTO toDto(Filial filial);

    void updateEntity(@MappingTarget Filial filial, FilialCrudDTO crudDTO);
}