package com.example.cinema.controller;

import com.example.cinema.domain.dto.order.CreateOrderTicketDTO;
import com.example.cinema.domain.dto.order.OrderTicketDTO;
import com.example.cinema.domain.dto.order.SearchOrderTicketDTO;
import com.example.cinema.domain.dto.order.UpdateOrderTicketDTO;
import com.example.cinema.service.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderTicketController {

    @Autowired
    private OrderTicketService orderTicketService;

    @PostMapping
    public OrderTicketDTO create(@RequestBody CreateOrderTicketDTO createOrderTicketDTO) {
        return orderTicketService.create(createOrderTicketDTO);
    }

    @GetMapping("/{id}")
    public OrderTicketDTO getById(@PathVariable Long id) {
        return orderTicketService.getById(id);
    }

    @GetMapping("/search-by-criteria")
    public List<OrderTicketDTO> search(@RequestBody SearchOrderTicketDTO searchOrderTicketDTO, Pageable pageable) {
        return orderTicketService.searchByCriteria(searchOrderTicketDTO, pageable);
    }

    @PutMapping("/{id}")
    public OrderTicketDTO update(@PathVariable Long id, @RequestBody UpdateOrderTicketDTO updateOrderTicketDTO) {
        return orderTicketService.update(id, updateOrderTicketDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderTicketService.delete(id);
    }
}
