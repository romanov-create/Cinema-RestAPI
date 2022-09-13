package com.example.cinema.domain.mapper;

import com.example.cinema.domain.data.OrderTicket;
import com.example.cinema.domain.dto.order.CreateOrderTicketDTO;
import com.example.cinema.domain.dto.order.OrderTicketDTO;
import com.example.cinema.domain.dto.order.UpdateOrderTicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderTicketMapper {

    @Autowired
    private MovieMapper movieMapper;

    public OrderTicket toCreateEntity(CreateOrderTicketDTO createOrderTicketDTO) {
        OrderTicket orderTicket = new OrderTicket();

        orderTicket.setRowPlace(createOrderTicketDTO.getRow());
        orderTicket.setSeat(createOrderTicketDTO.getSeat());
        orderTicket.setPrice(createOrderTicketDTO.getPrice());

        return orderTicket;
    }

    public OrderTicket toUpdateEntity(OrderTicket orderTicket, UpdateOrderTicketDTO updateOrderTicketDTO) {
        orderTicket.setRowPlace(updateOrderTicketDTO.getRow());
        orderTicket.setSeat(updateOrderTicketDTO.getSeat());
        orderTicket.setPrice(updateOrderTicketDTO.getPrice());

        return orderTicket;
    }

    public OrderTicketDTO toDto(OrderTicket orderTicket) {
        OrderTicketDTO orderTicketDTO = new OrderTicketDTO();

        orderTicketDTO.setId(orderTicket.getId());
        orderTicketDTO.setRow(orderTicket.getRowPlace());
        orderTicketDTO.setSeat(orderTicket.getSeat());
        orderTicketDTO.setPrice(orderTicket.getPrice());
        orderTicketDTO.setMovie(movieMapper.toDto(orderTicket.getMovie()));

        return orderTicketDTO;
    }

    public List<OrderTicketDTO> toDto(List<OrderTicket> orderTicketList) {
        List<OrderTicketDTO> orderTicketDTOList = new ArrayList<>();

        orderTicketList.forEach(orderTicket -> {
            OrderTicketDTO orderTicketDTO = new OrderTicketDTO();

            orderTicketDTO.setId(orderTicket.getId());
            orderTicketDTO.setRow(orderTicket.getRowPlace());
            orderTicketDTO.setSeat(orderTicket.getSeat());
            orderTicketDTO.setPrice(orderTicket.getPrice());
            orderTicketDTO.setMovie(movieMapper.toDto(orderTicket.getMovie()));

            orderTicketDTOList.add(orderTicketDTO);
        });

        return orderTicketDTOList;
    }

}
