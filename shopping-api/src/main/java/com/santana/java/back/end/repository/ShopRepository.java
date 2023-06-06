package com.santana.java.back.end.repository;

import com.santana.java.back.end.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    // Retrieves all purchases for a specific user
    public List<Shop> findAllByUserIdentifier(String userIdentifier);
    // Fetches all purchases that have a total value greater than specified
    public List<Shop> findAllByTotalGreatherThan(Float total);
    // Returns all purchases from a specific date
    public List<Shop> findAllByDateGreatherTran(LocalDateTime date);
}
