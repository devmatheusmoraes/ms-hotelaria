package br.com.infnet.hoteis.disponibilidade.service;

import br.com.infnet.hoteis.disponibilidade.model.Reserva;
import br.com.infnet.hoteis.disponibilidade.repository.ReservaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    public Optional<Reserva> findById(Long id){
        return reservaRepository.findById(id);
    }
    public List<Reserva> findAll(){
        return reservaRepository.findAll();
    }

    public void save(Reserva reserva){
        reservaRepository.save(reserva);
    }
}
