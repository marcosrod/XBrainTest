package com.xbrainapi.sales.controller;

import com.xbrainapi.sales.model.Seller;
import com.xbrainapi.sales.repository.SellerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/sellers")
public class SellerController {

    private SellerRepository sellerRepository;

    public SellerController(SellerRepository sellerRepository){
        super();
        this.sellerRepository = sellerRepository;
    }

    @PostMapping
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller){
        sellerRepository.save(seller);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Seller>> getSellerById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(sellerRepository.findById(id));
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
