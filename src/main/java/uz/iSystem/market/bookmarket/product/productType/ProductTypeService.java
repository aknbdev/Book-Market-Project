package uz.iSystem.market.bookmarket.product.productType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {

    private final ModelMapper mapper;
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ModelMapper mapper, ProductTypeRepository productTypeRepository) {
        this.mapper = mapper;
        this.productTypeRepository = productTypeRepository;
    }

    public ProductTypeDto create(ProductTypeDto productTypeDto) {

        ProductType productType = mapper.map(productTypeDto, ProductType.class);
        productType.setCreated_date(LocalDateTime.now());
        productType.setStatus("Active");
        productTypeRepository.save(productType);
        productTypeDto.setId(productType.getId());
        productTypeDto.setStatus(productType.getStatus());
        return productTypeDto;
    }


    public List<ProductTypeDto> getAll() {

        return productTypeRepository.getAllByDeleted_dateIsNull()
                .stream()
                .map(productType -> mapper.map(productType, ProductTypeDto.class))
                .collect(Collectors.toList());
    }


    public ProductTypeDto getOne(Integer id) {

        return mapper.map(getEntity(id), ProductTypeDto.class);
    }


    public ProductTypeDto update(Integer id, ProductTypeDto productTypeDto) {

        ProductType productType = getEntity(id);
        ProductType productType1 = mapper.map(productTypeDto, ProductType.class);
        productType1.setId(id);
        productType1.setStatus(productType.getStatus());
        productType1.setCreated_date(productType.getCreated_date());
        productType1.setUpdated_date(LocalDateTime.now());
        productTypeRepository.save(productType1);
        productTypeDto = mapper.map(productType1, ProductTypeDto.class);
        return productTypeDto;
    }

    public void delete(Integer id) {

        ProductType productType = getEntity(id);
        productType.setDeleted_date(LocalDateTime.now());
        productTypeRepository.save(productType);
    }



    // |- Secondary functions -|

    public ProductType getEntity(Integer id){
        Optional<ProductType> optionalProductType = productTypeRepository.getByIdAndDeleted_dateIsNull(id);

        if (optionalProductType.isEmpty()){
            throw new ApiRequestException("Product Type not found!");
        }
        return optionalProductType.get();
    }

}