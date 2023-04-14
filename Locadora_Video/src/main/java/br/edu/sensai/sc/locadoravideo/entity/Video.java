package br.edu.sensai.sc.locadoravideo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private int ano;
    private double preco;
    private String categoria;

    @OneToMany(mappedBy = "video")
    private List<Emprestimo> emprestimos;


}
