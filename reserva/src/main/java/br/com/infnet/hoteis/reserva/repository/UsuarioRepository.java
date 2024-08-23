package br.com.infnet.hoteis.reserva.repository;

import br.com.infnet.hoteis.reserva.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
