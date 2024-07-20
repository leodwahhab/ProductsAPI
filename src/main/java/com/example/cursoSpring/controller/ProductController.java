package com.example.cursoSpring.controller;

import com.example.cursoSpring.dto.ProductDTO;
import com.example.cursoSpring.model.ProductModel;
import com.example.cursoSpring.repository.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping()
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDTO, productModel); //copia todas as propriedades do objeto dto para o objeto model
        return ResponseEntity //classe que representa toda uma resposta http (codigo de status, header, body)
                .status( //cria um construtor com um determinado status
                        HttpStatus. //representa um código de status de resposta HTTP
                                CREATED //codigo 201 Created.
                ).body( //retorna uma dada entidade como body da requisição
                        productRepository.save(productModel) //salva uma dada entidade e retorna ela mesma
                );
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity
                .status(
                        HttpStatus.OK
                ).body(
                        productRepository.findAll()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductDTO productDTO) {
        Optional<ProductModel> product = productRepository.findById(id); //procura a instancia do produto (registro) no banco de dados que possui o id passado por uri
        if(product.isEmpty()) //se o id nao existir, retorna NOT_FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");

        //se o id existir:
        var productModel = product.get(); //recebe a instancia desse produto
        BeanUtils.copyProperties(productDTO, productModel); //copia as propriedades do objeto dto para o objeto model
        return ResponseEntity //classe que representa toda uma resposta http (codigo de status, header, body)
                .status( //cria um construtor com um determinado status
                        HttpStatus. //representa um código de status de resposta HTTP
                                OK //determina que sera retornado codigo OK.
                ).body( //determina o body da resposta
                        productRepository.save(productModel) //atualiza o registro e retorna uma instancia do produto
                );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");
    }
}
