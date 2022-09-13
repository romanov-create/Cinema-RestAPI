package com.example.cinema.service.Impl;

import com.example.cinema.domain.data.Movie;
import com.example.cinema.domain.data.OrderTicket;
import com.example.cinema.domain.dto.order.CreateOrderTicketDTO;
import com.example.cinema.domain.dto.order.OrderTicketDTO;
import com.example.cinema.domain.dto.order.SearchOrderTicketDTO;
import com.example.cinema.domain.dto.order.UpdateOrderTicketDTO;
import com.example.cinema.domain.mapper.OrderTicketMapper;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.OrderTicketRepository;
import com.example.cinema.service.OrderTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderTicketServiceImpl implements OrderTicketService {

    @Autowired
    private OrderTicketRepository orderTicketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderTicketMapper orderTicketMapper;

    @Override
    public OrderTicketDTO create(CreateOrderTicketDTO createOrderTicketDTO) {
        OrderTicket orderTicket = orderTicketMapper.toCreateEntity(createOrderTicketDTO);

        Movie movie = movieRepository.getReferenceById(createOrderTicketDTO.getMovieId());
        orderTicket.setMovie(movie);

        return orderTicketMapper.toDto(orderTicketRepository.save(orderTicket));
    }

    @Override
    public OrderTicketDTO update(Long id, UpdateOrderTicketDTO updateOrderTicketDTO) {
        OrderTicket orderTicket = orderTicketRepository.getReferenceById(id);

        orderTicket = orderTicketMapper.toUpdateEntity(orderTicket, updateOrderTicketDTO);

        Long movieId = updateOrderTicketDTO.getMovieId();

        if (movieId != null) {
            Movie movie = movieRepository.getById(movieId);
            orderTicket.setMovie(movie);
        }

        return orderTicketMapper.toDto(orderTicketRepository.save(orderTicket));
    }

    @Override
    public OrderTicketDTO getById(Long id) {
        return orderTicketMapper.toDto(orderTicketRepository.getReferenceById(id));
    }

    @Override
    public List<OrderTicketDTO> searchByCriteria(SearchOrderTicketDTO searchOrderTicketDTO, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderTicket> criteriaQuery = criteriaBuilder.createQuery(OrderTicket.class);
        Root<OrderTicket> orderTicketRoot = criteriaQuery.from(OrderTicket.class);

        List<Predicate> predicates = initializePredicates(searchOrderTicketDTO, criteriaBuilder, orderTicketRoot);
        CriteriaQuery<OrderTicket> result = criteriaQuery.select(orderTicketRoot).where(predicates.toArray(new Predicate[0]));

        TypedQuery<OrderTicket> typedQuery = entityManager.createQuery(result);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(pageable.getPageSize());

        List<OrderTicket> orderTicketList = typedQuery.getResultList();

        return orderTicketMapper.toDto(orderTicketList);
    }

    @Override
    public void delete(Long id) {
        orderTicketRepository.deleteById(id);
    }

    private ArrayList<Predicate> initializePredicates(SearchOrderTicketDTO searchOrderTicketDTO, CriteriaBuilder criteriaBuilder, Root<OrderTicket> orderTicketRoot) {
        Double minPrice = searchOrderTicketDTO.getMinPrice();
        Double maxPrice = searchOrderTicketDTO.getMaxPrice();
        Integer row = searchOrderTicketDTO.getRow();
        Integer seat = searchOrderTicketDTO.getSeat();
        Long movieId = searchOrderTicketDTO.getMovieId();

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (row != null) {
            predicates.add(criteriaBuilder.equal(orderTicketRoot.get("rowPlace"), row));
        }
        if (seat != null) {
            predicates.add(criteriaBuilder.equal(orderTicketRoot.get("seat"), seat));
        }
        if (minPrice != null && maxPrice != null) {
            predicates.add(criteriaBuilder.between(orderTicketRoot.get("price"), minPrice, maxPrice));
        }
        if (movieId != null) {
            predicates.add(criteriaBuilder.equal(orderTicketRoot.get("movie"), movieId));
        }

        return predicates;
    }

}
