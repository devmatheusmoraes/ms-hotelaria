package br.com.infnet.hoteis.reserva.service;

import br.com.infnet.hoteis.reserva.model.Hotel;
import br.com.infnet.hoteis.reserva.repository.HotelRepository;
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

    public void save(Hotel hotel){
        hotelRepository.save(hotel);
    }
}
