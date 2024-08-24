package br.com.infnet.hoteis.disponibilidade.model;

import br.com.infnet.hoteis.disponibilidade.dto.ReservaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private BigDecimal valor;

    private Integer numeroAdultos;

    private Integer numeroCriancas;

    private Integer numeroComodos;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private Long usuarioId;

    public Reserva(ReservaDto dto){
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.numeroAdultos = dto.numeroAdultos();
        this.numeroCriancas = dto.numeroCriancas();
        this.numeroComodos = dto.numeroComodos();
    }

}
