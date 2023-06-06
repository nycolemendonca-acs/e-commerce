package com.santana.java.back.end.service;

import com.santana.java.back.end.dto.ItemDTO;
import com.santana.java.back.end.dto.ProductDTO;
import com.santana.java.back.end.dto.ShopDTO;
import com.santana.java.back.end.dto.ShopReportDTO;
import com.santana.java.back.end.model.Shop;
import com.santana.java.back.end.repository.ShopRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    @Autowired
    private final ShopRepository shopRepository;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final UserService userService;

    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository
                .findAllByDateGreatherTran(shopDTO.getDate());
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);

        if (shop.isPresent()) return ShopDTO.convert(shop.get());
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {
        if (userService.getUserByCpf(shopDTO.getUserIdentifier()) == null) { return null; }
        if (!validateProducts(shopDTO.getItems())) { return null; }

        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return ShopDTO.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());

            if (productDTO == null) { return false; }
            item.setPrice(productDTO.getPrice());
        }
        return true;
    }

    public List<ShopDTO> getShopsByFilter(LocalDate startDate, LocalDate endDate, Float minimumValue) {
        List<Shop> shops = shopRepository.getShopByFilters(startDate, endDate, minimumValue);

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }


    public ShopReportDTO getReportByDate(LocalDate startDate, LocalDate endDate) {
        return shopRepository.getReportByDate(startDate, endDate);
    }
}
