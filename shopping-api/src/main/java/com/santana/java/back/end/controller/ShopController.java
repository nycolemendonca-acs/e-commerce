package com.santana.java.back.end.controller;

import com.santana.java.back.end.dto.ShopDTO;
import com.santana.java.back.end.dto.ShopReportDTO;
import com.santana.java.back.end.model.Shop;
import com.santana.java.back.end.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() { return shopService.getAll(); }

    @GetMapping("/shopping/shoppingByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier); }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO); }

    @PostMapping("/shopping")
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(@Valid @RequestBody ShopDTO shopDTO) { return shopService.save(shopDTO); }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "startDate", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime endDate,
            @RequestParam(name = "minimumValue", required = false) Float minimumValue) {

        return shopService.getShopsByFilter(startDate, endDate, minimumValue);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "startDate", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime startDate,
            @RequestParam(name = "endDate", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime endDate {

                return shopService.getReportByDate(startDate, endDate);
    }
    )
}
