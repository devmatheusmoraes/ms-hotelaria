package br.com.infnet.hoteis.disponibilidade.repository;

import br.com.infnet.hoteis.disponibilidade.dto.ReservaDto;
import br.com.infnet.hoteis.disponibilidade.model.Hotel;

import java.util.List;

public interface HotelRepositoryConvert {
    List<Hotel> findHotels(ReservaDto reservaDto);
}
