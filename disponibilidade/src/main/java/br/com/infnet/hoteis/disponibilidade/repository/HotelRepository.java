package br.com.infnet.hoteis.reserva.repository;

import br.com.infnet.hoteis.reserva.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
