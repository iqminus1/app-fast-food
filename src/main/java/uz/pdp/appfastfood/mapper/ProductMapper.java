package uz.pdp.appfastfood.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.appfastfood.entity.Product;
import uz.pdp.appfastfood.payload.ProductCrudDTO;
import uz.pdp.appfastfood.payload.ProductDTO;
import uz.pdp.appfastfood.repository.CategoryRepository;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductDTO toDto(Product product);
    void updateEntity(@MappingTarget Product product, ProductCrudDTO productCrudDTO);

    default ProductDTO toDefaultDTO(Product product) {
        ProductDTO dto = toDto(product);
        dto.setCategoryId(product.getCategory().getId());
        if (product.getAttachment() != null) {
            dto.setAttachmentId(product.getAttachment().getId());
        }
        return dto;
    }
}