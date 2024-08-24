package br.com.infnet.hoteis.disponibilidade.service;

import br.com.infnet.hoteis.disponibilidade.dto.ReservaDto;
import br.com.infnet.hoteis.disponibilidade.model.Hotel;
import br.com.infnet.hoteis.disponibilidade.repository.HotelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    public Optional<Hotel> findById(Long id){
        return hotelRepository.findById(id);
    }
    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }

    public List<Hotel> findHotelsByDto(ReservaDto dto){
        return hotelRepository.findHotels(dto);
    }
}
