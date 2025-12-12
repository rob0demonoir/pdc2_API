package com.duoc.principedecolores.api.controller;

import com.duoc.principedecolores.api.dto.AnadirAlCarritoRequest; // <--- NOMBRE NUEVO
import com.duoc.principedecolores.api.dto.CarritoDto;
import com.duoc.principedecolores.api.model.Cliente;
import com.duoc.principedecolores.api.model.ItemCarrito;
import com.duoc.principedecolores.api.model.Product;
import com.duoc.principedecolores.api.repository.CarritoRepository;
import com.duoc.principedecolores.api.repository.ClienteRepository;
import com.duoc.principedecolores.api.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoRepository carritoRepository;
    private final ProductRepository productRepository;
    private final ClienteRepository clienteRepository;

    public CarritoController(CarritoRepository cRepo, ProductRepository pRepo, ClienteRepository clRepo) {
        this.carritoRepository = cRepo;
        this.productRepository = pRepo;
        this.clienteRepository = clRepo;
    }

    @GetMapping
    public ResponseEntity<CarritoDto> obtenerCarrito(@RequestParam Integer clienteId) {
        List<ItemCarrito> items = carritoRepository.findByCliente_Id(clienteId);
        return ResponseEntity.ok(new CarritoDto(items));
    }

    @PostMapping("/agregar")

    public ResponseEntity<?> agregarAlCarrito(@RequestBody AnadirAlCarritoRequest request) {

        Product producto = productRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Optional<ItemCarrito> itemExistente = carritoRepository.findByCliente_IdAndProducto_Id(
                request.getIdCliente(),
                request.getIdProducto()
        );

        ItemCarrito item;
        if (itemExistente.isPresent()) {
            item = itemExistente.get();
            item.setCantidad(item.getCantidad() + request.getCantidad());
        } else {
            item = new ItemCarrito();
            item.setProducto(producto);
            item.setCliente(cliente);
            item.setCantidad(request.getCantidad());
        }

        carritoRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto agregado");
    }

    @PostMapping("/pagar")
    @Transactional
    public ResponseEntity<String> pagarCarrito(@RequestParam Integer clienteId) {
        List<ItemCarrito> items = carritoRepository.findByCliente_Id(clienteId);

        if (items.isEmpty()) return ResponseEntity.badRequest().body("Carrito vacío");

        for (ItemCarrito item : items) {
            Product prod = item.getProducto();
            int nuevaCantidad = prod.getStock() - item.getCantidad();
            if (nuevaCantidad < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sin stock: " + prod.getName());

            prod.setStock(nuevaCantidad);
            productRepository.save(prod);
        }

        carritoRepository.deleteByCliente_Id(clienteId);
        return ResponseEntity.ok("Compra exitosa");
    }
}