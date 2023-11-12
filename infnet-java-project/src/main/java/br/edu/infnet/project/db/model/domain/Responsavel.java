package br.edu.infnet.project.db.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Responsavel")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResponsavel")
    private Integer idResponsavel;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Aluno",
        joinColumns = {@JoinColumn(name = "IdAluno", insertable = false, updatable = false)}
    )
    private Aluno aluno;

    @Column(name = "NomeResponsavel", nullable = false)
    private String nomeResponsavel;

    @Column(name = "Parentesco", nullable = false)
    private String parentesco;

    public Responsavel() {
    }

    public Responsavel(String nomeResponsavel, String parentesco) {
        this.nomeResponsavel = nomeResponsavel;
        this.parentesco = parentesco;
    }

    public Responsavel(Aluno aluno, String nomeResponsavel, String parentesco) {
        this.aluno = aluno;
        this.nomeResponsavel = nomeResponsavel;
        this.parentesco = parentesco;
    }

    public Integer getIdResponsavel() {
        return this.idResponsavel;
    }

    public void setIdResponsavel(Integer idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getNomeResponsavel() {
        return this.nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getParentesco() {
        return this.parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
            "idResponsavel=" + idResponsavel +
            ", aluno=" + aluno +
            ", nomeResponsavel='" + nomeResponsavel + '\'' +
            ", parentesco='" + parentesco + '\'' +
            '}';
        //
    }
}
