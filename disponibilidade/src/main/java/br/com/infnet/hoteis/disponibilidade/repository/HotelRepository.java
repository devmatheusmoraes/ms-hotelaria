package br.com.infnet.hoteis.disponibilidade.repository;

import br.com.infnet.hoteis.disponibilidade.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long>, HotelRepositoryConvert {
}
