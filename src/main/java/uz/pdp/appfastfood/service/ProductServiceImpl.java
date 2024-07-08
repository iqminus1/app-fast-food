package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.Product;
import uz.pdp.appfastfood.mapper.ProductMapper;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.ProductCrudDTO;
import uz.pdp.appfastfood.payload.ProductDTO;
import uz.pdp.appfastfood.repository.AttachmentRepository;
import uz.pdp.appfastfood.repository.CategoryRepository;
import uz.pdp.appfastfood.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final ProductMapper productMapper;

    @Override
    public ApiResultDTO<List<ProductDTO>> readByDiscount() {
        return ApiResultDTO.success(
                productRepository.findAllByDiscountIsNotNull().stream()
                        .map(productMapper::toDefaultDTO)
                        .toList());
    }

    @Cacheable(value = "productAll")
    @Override
    public ApiResultDTO<List<ProductDTO>> read() {
        return ApiResultDTO.success(productRepository.findAll()
                .stream()
                .map(productMapper::toDefaultDTO)
                .toList());
    }

    @Cacheable(key = "#id", value = "product")
    @Override
    public ApiResultDTO<ProductDTO> read(Integer id) {
        return ApiResultDTO.success(productMapper.toDefaultDTO(productRepository.find(id)));
    }

    @CacheEvict(value = "productAll", allEntries = true)
    @Override
    public ApiResultDTO<ProductDTO> create(ProductCrudDTO crudDTO) {
        Product product = new Product();
        updateEntity(crudDTO, product);
        productRepository.save(product);
        return ApiResultDTO.success(productMapper.toDefaultDTO(product));
    }

    @CachePut(key = "#id", value = "product")
    @CacheEvict(value = {"productAll", "userBasket", "userBasketAll"}, allEntries = true)
    @Override
    public ApiResultDTO<ProductDTO> update(ProductCrudDTO crudDTO, Integer id) {
        Product product = productRepository.find(id);
        updateEntity(crudDTO, product);
        productRepository.save(product);
        return ApiResultDTO.success(productMapper.toDefaultDTO(product));
    }

    @CacheEvict(key = "#id", value = {"product", "productAll", "userBasketAll", "userBasket"}, allEntries = true)
    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    private void updateEntity(ProductCrudDTO crudDTO, Product product) {
        productMapper.updateEntity(product, crudDTO);
        product.setCategory(categoryRepository.find(crudDTO.getCategoryId()));
        if (crudDTO.getAttachmentId() != null) {
            product.setAttachment(attachmentRepository.find(crudDTO.getAttachmentId()));
        }
    }

//    private ProductDTO toDTO(Product product) {
//        ProductDTO productDTO = new ProductDTO(product.getId(), product.getCategory().getId(), product.getNameRu(), product.getNameUz(), product.getPrice(), null);
//        if (product.getAttachment() != null) {
//            productDTO.setAttachmentId(product.getAttachment().getId());
//        }
//        return productDTO;
//    }
}
