package br.com.infnet.hoteis.reserva.repository;

import br.com.infnet.hoteis.reserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
