package com.santana.java.back.end.repository;

import com.santana.java.back.end.dto.ShopReportDTO;
import com.santana.java.back.end.model.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.management.Query;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl extends ReportRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(
            LocalDate startDate,
            LocalDate endDate,
            Float minimumValue) {
        StringBuilder sb = new StringBuilder();

        sb.append("select s");
        sb.append("from shop s");
        sb.append("where s.date >= :startDate ");

        if (endDate != null) sb.append("and s.date <= :endDate ")
        if (minimumValue != null) sb.append("and s.total <= :minimumValue");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("startDate", startDate.atTime((0, 0)));

        if (endDate != null) query.setParameter("endDate", endDate.atTime(23, 59));
        if (minimumValue != null) query.setParameter("minimumValue", minimumValue);

        return query.getResultList;
    }

    @Override
    public ShopReportDTO getReportByDate(
            LocalDate startDate,
            LocalDate endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(sp.id, sum(sp.total), avg(sp.total) ");
        sb.append("from shopping.shop sp ");
        sb.append("where sp.date >= :startDate ");
        sb.append("and sp.date <= :endDate ");

        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("startDate", startDate.atTime(0,0));
        query.setParameter("endDate", endDate.atTime(23, 59));

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigInteger) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[2]);
        return shopReportDTO;
    }
}
