package uz.iSystem.market.bookmarket.product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.iSystem.market.bookmarket.exception.ApiRequestException;
import uz.iSystem.market.bookmarket.product.productType.ProductTypeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final ProductTypeService productTypeService;

    public ProductService(ModelMapper mapper, ProductRepository productRepository,
                          ProductTypeService productTypeService) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.productTypeService = productTypeService;
    }

    public ProductDto create(ProductDto productDto) {

        productTypeService.getEntity(productDto.getProductTypeId());
        Product product = mapper.map(productDto, Product.class);
        product.setCreated_date(LocalDateTime.now());
        product.setStatus("Active");
        productRepository.save(product);
        productDto.setId(product.getId());
        productDto.setStatus(product.getStatus());
        setProductType(productDto);
        return productDto;
    }

    public List<ProductDto> getAll() {

        List<ProductDto> productDtoList = productRepository.getAllByDeleted_dateIsNull()
                .stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        if (!productDtoList.isEmpty()){
            for (ProductDto productDto : productDtoList){
                setProductType(productDto);
            }
            return productDtoList;
        }
        throw new ApiRequestException("Products not found!");
    }

    public ProductDto getOne(Integer id) {

        ProductDto productDto = mapper.map(getEntity(id), ProductDto.class);
        setProductType(productDto);
        return productDto;
    }

    public ProductDto update(Integer id, ProductDto productDto) {

        Product product = getEntity(id);
        Product product1 = mapper.map(productDto, Product.class);
        product1.setId(id);
        product1.setStatus(product.getStatus());
        product1.setCreated_date(product.getCreated_date());
        productRepository.save(product1);
        productDto = mapper.map(product1, ProductDto.class);
        setProductType(productDto);
        return productDto;
    }

    public void delete(Integer id) {

        Product product = getEntity(id);
        product.setDeleted_date(LocalDateTime.now());
        productRepository.save(product);
    }


    // |- Secondary functions -|

    public void setProductType(ProductDto productDto){
        productDto.setProductType(productTypeService.getOne(productDto.getProductTypeId()));
    }

    public Product getEntity(Integer id){
        Optional<Product> optionalProduct = productRepository.getByIdAndDeleted_dateIsNull(id);
        if (optionalProduct.isEmpty()){
            throw new ApiRequestException("Product not found!");
        }
        return optionalProduct.get();
    }

}