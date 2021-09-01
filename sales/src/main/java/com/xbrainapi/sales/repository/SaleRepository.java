package com.xbrainapi.sales.repository;

import com.xbrainapi.sales.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findAllByDateBetween(
            Date saleDateStart,
            Date saleDateEnd
    );

}
