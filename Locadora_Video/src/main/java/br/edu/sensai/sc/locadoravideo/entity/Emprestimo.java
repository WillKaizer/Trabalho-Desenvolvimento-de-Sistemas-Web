package br.edu.sensai.sc.locadoravideo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Emprestimo {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private LocalDateTime inicio;
    private LocalDateTime termino;
    private String locatario;
    private String cpf;
    private Double pagamento;
    private Double taxa;
    private LocalDateTime devolucao;

    @ManyToOne
    @JoinColumn(name = "video")
    private Video video;
}
