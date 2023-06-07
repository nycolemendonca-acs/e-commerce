package com.santana.java.back.end.repository;

import com.santana.java.back.end.dto.ShopReportDTO;
import com.santana.java.back.end.model.Shop;

import java.time.LocalDate;
import java.util.List;

@Entity
public class ReportRepository {
    public List<Shop> getShopByFilters(
            LocalDate startDate,
            LocalDate endDate
            Float minimunValue)

    public ShopReportDTO getReportByDate(
            LocalDate startDate,
            LocalDate endDate)
}
