package br.com.jjnervosia.gerenciador_encomendas.bloco;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class Bloco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identificacao", nullable = false, length = 10, unique = true)
    private String identificacao;

    protected Bloco(){
        //Uso Exclusivo do JPA
    }

    public Bloco(String identificacao) {

        if (identificacao == null || identificacao.isBlank()){
            throw new IllegalArgumentException("A identificação do bloco é obrigatória.");
        }

        this.identificacao = identificacao;
    }
}
