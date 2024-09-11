package br.com.infnet.hoteis.reserva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private Integer numeroApartamento;

    private Integer numeroAdultosApartamento;

    private Integer numeroCriancasApartamento;

    private Integer numeroComodosApartamento;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private Set<Reserva> reservas;

    private BigDecimal percentHotel;

}
