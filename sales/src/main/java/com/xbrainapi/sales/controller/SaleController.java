package com.xbrainapi.sales.controller;


import com.xbrainapi.sales.model.Sale;
import com.xbrainapi.sales.model.SaleReport;
import com.xbrainapi.sales.repository.SaleRepository;
import com.xbrainapi.sales.service.SalesReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path="/sales")
public class SaleController {

    private SellerController sellerController;
    private SaleRepository saleRepository;
    private SalesReportService salesReportService;

    public SaleController(SaleRepository saleRepository, SellerController sellerController, SalesReportService salesReportService){
        super();
        this.saleRepository = saleRepository;
        this.sellerController = sellerController;
        this.salesReportService = salesReportService;
    }

    //Post Method to save a new Sale
    @PostMapping
    public ResponseEntity<Sale> save(@RequestBody Sale sale){

        sale.setSellerName(Objects.requireNonNull(sellerController.getSellerById(sale.getSellerId()).getBody()).get().getName());
        saleRepository.save(sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    //Get Method to bring all the created Sales
    @GetMapping
    public ResponseEntity<List<Sale>> getAll(){
        List<Sale> sales;
        sales = saleRepository.findAll();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    //Get Method to bring a Sale by it's id
    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Sale>> getById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(saleRepository.findById(id));
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Delete Method to remove a Sale by it's id
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Optional<Sale>> deleteById(@PathVariable Integer id){
        try{
            saleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Put Method to update a Sale by it's id
    @PutMapping(value="/{id}")
    public ResponseEntity<Sale> update(@PathVariable Integer id, @RequestBody Sale newSale){
        return saleRepository.findById(id)
                .map(sale -> {
                    sale.setDate(newSale.getDate());
                    sale.setValue(newSale.getValue());
                    sale.setSellerId(newSale.getSellerId());
                    sale.setSellerName(newSale.getSellerName());
                    Sale saleUpdated = saleRepository.save(sale);
                    return ResponseEntity.ok().body(saleUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //Get Method to create a Sales Report
    @GetMapping(path = "/report/{dateStart}/{dateEnd}")
    public ResponseEntity<List<SaleReport>> getSalesReport(@PathVariable Date dateStart, @PathVariable Date dateEnd){
        try{
            return ResponseEntity.ok().body(salesReportService.getSalesReport(saleRepository.findAllByDateBetween(dateStart, dateEnd), dateStart, dateEnd));
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
