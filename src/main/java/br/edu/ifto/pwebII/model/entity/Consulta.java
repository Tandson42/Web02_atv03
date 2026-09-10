package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entidade Consulta. Representa a tabela tb_consulta no banco de dados.
 *
 * Relacionamentos (conforme o diagrama de classes da atividade):
 * - Cada Consulta pertence obrigatoriamente a exatamente 1 Paciente
 *   (muitos para 1 -> @ManyToOne agindo como lado "dono").
 * - Cada Consulta é realizada obrigatoriamente por exatamente 1 Medico
 *   (muitos para 1 -> @ManyToOne agindo como lado "dono").
 */
@Entity
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;
    private double valor;
    private String observacao;

    // Lado dono do relacionamento 0..* -> 1 (Paciente).
    // A FK "paciente_id" é persistida na tabela Consulta.
    // @OnDelete(CASCADE) gera a constraint com ON DELETE CASCADE no banco,
    // garantindo a exclusão em cascata mesmo fora do contexto JPA.
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Paciente paciente;

    // Lado dono do relacionamento 0..* -> 1 (Medico).
    // A FK "medico_id" é persistida na tabela Consulta.
    // @OnDelete(CASCADE) gera a constraint com ON DELETE CASCADE no banco.
    @ManyToOne
    @JoinColumn(name = "medico_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Medico medico;


    public String dados() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Consulta em " + (data != null ? data.format(fmt) : "sem data")
                + " | Valor: R$ " + valor
                + " | Obs: " + observacao
                + " | Paciente: " + (paciente != null ? paciente.getNome() : "-")
                + " | Médico: " + (medico != null ? medico.getNome() : "-");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
