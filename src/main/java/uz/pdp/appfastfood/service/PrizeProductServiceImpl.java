package uz.pdp.appfastfood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appfastfood.entity.PrizeProduct;
import uz.pdp.appfastfood.payload.ApiResultDTO;
import uz.pdp.appfastfood.payload.PrizeProductCrudDTO;
import uz.pdp.appfastfood.payload.PrizeProductDTO;
import uz.pdp.appfastfood.repository.PrizeProductRepository;
import uz.pdp.appfastfood.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PrizeProductServiceImpl implements PrizeProductService {
    private final ProductRepository productRepository;
    private final PrizeProductRepository prizeProductRepository;

    @Override
    public ApiResultDTO<List<PrizeProductDTO>> read() {
        return ApiResultDTO.success(prizeProductRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList());
    }

    @Override
    public ApiResultDTO<PrizeProductDTO> read(Integer id) {
        return ApiResultDTO.success(toDTO(prizeProductRepository.find(id)));
    }

    @Override
    public ApiResultDTO<PrizeProductDTO> create(PrizeProductCrudDTO crudDTO) {
        PrizeProduct prizeProduct = new PrizeProduct();
        updateEntity(prizeProduct, crudDTO);
        prizeProductRepository.save(prizeProduct);
        return ApiResultDTO.success(toDTO(prizeProduct));
    }

    @Override
    public ApiResultDTO<PrizeProductDTO> update(PrizeProductCrudDTO crudDTO, Integer id) {
        PrizeProduct prizeProduct = prizeProductRepository.find(id);
        updateEntity(prizeProduct, crudDTO);
        prizeProductRepository.save(prizeProduct);
        return ApiResultDTO.success(toDTO(prizeProduct));
    }

    @Override
    public void delete(Integer id) {
        prizeProductRepository.deleteById(id);
    }

    private PrizeProductDTO toDTO(PrizeProduct prizeProduct) {
        return new PrizeProductDTO(prizeProduct.getId(), prizeProduct.getProduct().getId(), prizeProduct.getQuantity());
    }

    private void updateEntity(PrizeProduct prizeProduct, PrizeProductCrudDTO crudDTO) {
        prizeProduct.setProduct(productRepository.find(crudDTO.getProductId()));
        prizeProduct.setQuantity(crudDTO.getQuantity());
    }
}
