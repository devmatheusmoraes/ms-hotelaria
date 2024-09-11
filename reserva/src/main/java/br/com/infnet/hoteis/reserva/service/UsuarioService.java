package br.com.infnet.hoteis.reserva.service;

import br.com.infnet.hoteis.reserva.model.Usuario;
import br.com.infnet.hoteis.reserva.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRespository;
    public Optional<Usuario> findById(Long id){
        return usuarioRespository.findById(id);
    }
    public List<Usuario> findAll() {
        return usuarioRespository.findAll();
    }

    public void save(Usuario usuario){
        usuarioRespository.save(usuario);
    }
}
