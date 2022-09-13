package com.example.cinema.service;

import com.example.cinema.domain.dto.order.CreateOrderTicketDTO;
import com.example.cinema.domain.dto.order.OrderTicketDTO;
import com.example.cinema.domain.dto.order.SearchOrderTicketDTO;
import com.example.cinema.domain.dto.order.UpdateOrderTicketDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderTicketService {

    OrderTicketDTO create(CreateOrderTicketDTO createOrderTicketDTO);

    OrderTicketDTO update(Long id, UpdateOrderTicketDTO updateOrderTicketDTO);

    OrderTicketDTO getById(Long id);

    List<OrderTicketDTO> searchByCriteria(SearchOrderTicketDTO searchOrderTicketDTO, Pageable pageable);

    void delete(Long id);

}
