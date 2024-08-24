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
        StringBuilder jpql = new StringBuilder("SELECT h FROM Hotel h ");

        StringBuilder whereClause = new StringBuilder();

        if (Objects.nonNull(reservaDto.numeroAdultos())) {
            whereClause.append("h.numeroAdultosApartamento <= :numeroAdultos ");
        }

        if (Objects.nonNull(reservaDto.numeroCriancas())) {
            if (!whereClause.isEmpty()) {
                whereClause.append("AND ");
            }
            whereClause.append("h.numeroCriancasApartamento <= :numeroCriancas ");
        }

        if (Objects.nonNull(reservaDto.numeroComodos())) {
            if (!whereClause.isEmpty()) {
                whereClause.append("AND ");
            }
            whereClause.append("h.numeroComodosApartamento <= :numeroComodos ");
        }

        if (!whereClause.isEmpty()) {
            jpql.append("WHERE ").append(whereClause);
        }

        jpql.append(" AND NOT EXISTS ( SELECT r " +
                    "                  FROM Reserva r " +
                    "                  WHERE r.hotel = h " +
                    "                  AND r.hotel.numeroApartamento = h.numeroApartamento )");

        TypedQuery<Hotel> query = entityManager.createQuery(jpql.toString(), Hotel.class);

        if (Objects.nonNull(reservaDto.numeroAdultos())) {
            query.setParameter("numeroAdultos", reservaDto.numeroAdultos());
        }
        if (Objects.nonNull(reservaDto.numeroCriancas())) {
            query.setParameter("numeroCriancas", reservaDto.numeroCriancas());
        }
        if (Objects.nonNull(reservaDto.numeroComodos())) {
            query.setParameter("numeroComodos", reservaDto.numeroComodos());
        }

        return query.getResultList();
    }

    /*@Override
    public List<Hotel> findHotels(ReservaDto reservaDto) {
        String jpql = "SELECT h " +
                "FROM Hotel h ";

        String where = "";

        if (Objects.nonNull(reservaDto.numeroAdultos())){
            where = "WHERE h.numeroAdultosApartamento <= :numeroAdultos ";
        }

        if (Objects.nonNull(reservaDto.numeroCriancas())){
            where = where.contains("WHERE") ? where + "AND " : where + "WHERE ";
            where = where + "h.numeroCriancasApartamento <= :numeroCriancas ";
        }

        if (Objects.nonNull(reservaDto.numeroComodos())){
            where = where.contains("WHERE") ? where + "AND " : where + "WHERE ";
            where = "WHERE h.numeroComodosApartamento <= :numeroComodos ";
        }

        where = where.contains("WHERE") ? where + "AND " : "WHERE ";
        where = "NOT EXISTS ( SELECT r " +
                "                  FROM Reserva r " +
                "                  WHERE r.hotel = h " +
                "                  AND r.hotel.numeroApartamento = h.numeroApartamento ) ";

        TypedQuery<Hotel> query = entityManager.createQuery(jpql, Hotel.class);
        query.setParameter("numeroAdultos", reservaDto.numeroAdultos());

        return query.getResultList();
    }*/

}
