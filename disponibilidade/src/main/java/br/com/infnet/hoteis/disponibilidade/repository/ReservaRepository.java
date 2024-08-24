package br.com.infnet.hoteis.disponibilidade.repository;

import br.com.infnet.hoteis.disponibilidade.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
