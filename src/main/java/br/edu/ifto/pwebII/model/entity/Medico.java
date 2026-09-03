package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade Medico. Representa a tabela tb_medico no banco de dados.
 *
 * Relacionamento (conforme o diagrama de classes da atividade):
 * Um {@code Medico} pode realizar zero ou várias (0..* -> 1) consultas.
 * A Consulta é o lado "dono" do relacionamento (guarda a FK), por isso
 * usamos @OneToMany(mappedBy = "medico") aqui.
 */
@Entity
public class Medico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;

    // Lado fraco (não dono) do relacionamento 0..* -> 1
    // O mappedBy indica que a FK está no atributo "medico" de Consulta.
    // cascade=REMOVE + orphanRemoval: ao excluir o médico, o JPA também
    // exclui as consultas associadas (evita violação de FK).
    @OneToMany(mappedBy = "medico", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Consulta> consultas = new ArrayList<>();

    /**
     * Retorna uma String com a representação textual (dados básicos) do médico.
     *
     * @return String com nome e CRM do médico
     */
    public String dados() {
        return "Médico: " + nome + " | CRM: " + crm;
    }

    /**
     * Retorna uma String com a lista textual de todas as consultas do médico.
     * (Atende ao requisito: "Visualizar todas as consultas realizadas por um médico")
     *
     * @return String com as consultas do médico
     */
    public String consultas() {
        String texto = "Consultas do Dr(a). " + nome + ":\n";
        if (consultas == null || consultas.isEmpty()) {
            texto += "Nenhuma consulta cadastrada.";
        } else {
            for (Consulta c : consultas) {
                texto += " - " + c.dados() + "\n";
            }
        }
        return texto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
