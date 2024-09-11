package br.com.infnet.hoteis.disponibilidade.repository.impl;

import br.com.infnet.hoteis.disponibilidade.dto.ReservaDto;
import br.com.infnet.hoteis.disponibilidade.model.Hotel;
import br.com.infnet.hoteis.disponibilidade.repository.HotelRepositoryConvert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Objects;

public class HotelRepositoryConvertImpl implements HotelRepositoryConvert {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> findHotels(ReservaDto reservaDto) {
        StringBuilder jpql = new StringBuilder("SELECT h FROM Hotel h WHERE ");

        StringBuilder whereClause = new StringBuilder();

        if (Objects.nonNull(reservaDto.numeroAdultos()) && reservaDto.numeroAdultos() > 0) {
            whereClause.append("h.numeroAdultosApartamento <= :numeroAdultos ");
        }

        if (Objects.nonNull(reservaDto.numeroCriancas()) && reservaDto.numeroCriancas() > 0) {
            if (!whereClause.isEmpty()) {
                whereClause.append("AND ");
            }
            whereClause.append("h.numeroCriancasApartamento <= :numeroCriancas ");
        }

        if (Objects.nonNull(reservaDto.numeroComodos()) && reservaDto.numeroComodos() > 0) {
            if (!whereClause.isEmpty()) {
                whereClause.append("AND ");
            }
            whereClause.append("h.numeroComodosApartamento <= :numeroComodos ");
        }

        if (!whereClause.isEmpty()) {
            whereClause.append("AND ");
        }

        whereClause.append(" NOT EXISTS ( SELECT r " +
                    "                  FROM Reserva r " +
                    "                  WHERE r.hotel = h " +
                    "                  AND r.hotel.numeroApartamento = h.numeroApartamento )");

        jpql.append(whereClause);

        TypedQuery<Hotel> query = entityManager.createQuery(jpql.toString(), Hotel.class);

        if (Objects.nonNull(reservaDto.numeroAdultos()) && reservaDto.numeroAdultos() > 0) {
            query.setParameter("numeroAdultos", reservaDto.numeroAdultos());
        }
        if (Objects.nonNull(reservaDto.numeroCriancas()) && reservaDto.numeroCriancas() > 0) {
            query.setParameter("numeroCriancas", reservaDto.numeroCriancas());
        }
        if (Objects.nonNull(reservaDto.numeroComodos()) && reservaDto.numeroComodos() > 0) {
            query.setParameter("numeroComodos", reservaDto.numeroComodos());
        }

        return query.getResultList();
    }

}
