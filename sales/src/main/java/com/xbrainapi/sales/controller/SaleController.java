package com.xbrainapi.sales.controller;


import com.xbrainapi.sales.model.Sale;
import com.xbrainapi.sales.repository.SaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path="/sales")
public class SaleController {

    private SellerController sellerController;
    private SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository, SellerController sellerController){
        super();
        this.saleRepository = saleRepository;
        this.sellerController = sellerController;
    }

    @PostMapping
    public ResponseEntity<Sale> save(@RequestBody Sale sale){
        sale.setSellerName(sellerController.getSellerById(sale.getSellerId()).getBody().get().getName());
        saleRepository.save(sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAll(){
        List<Sale> sales;
        sales = saleRepository.findAll();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Sale>> getById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok().body(saleRepository.findById(id));
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Optional<Sale>> deleteById(@PathVariable Integer id){
        try{
            saleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
}
