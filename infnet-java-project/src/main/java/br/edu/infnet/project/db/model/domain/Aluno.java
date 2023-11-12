package br.edu.infnet.project.db.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAluno", insertable = false, updatable = false)
    private Integer idAluno;

    @Column(name = "NomeAluno", nullable = false)
    private String nomeAluno;

    @Column(name = "NumMatricula", nullable = false)
    private Integer numMatricula;

    @Column(name = "NumSalaAula", nullable = false)
    private Integer numSalaAula;

    @Column(name = "Ativo", nullable = false)
    private Boolean ativo;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "SalaAula",
        joinColumns = {@JoinColumn(name = "IdAluno", insertable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "IdProfessor", insertable = false, updatable = false)}
    )
    private List<Professor> professores;

    public Aluno() {
    }

    public Aluno(String nomeAluno, Integer numeroMatricula, Integer numSalaAula, Boolean ativo) {
        this.nomeAluno = nomeAluno;
        this.numMatricula = numeroMatricula;
        this.numSalaAula = numSalaAula;
        this.ativo = ativo;
    }

    public Aluno(Integer idAluno, String nomeAluno, Integer numMatricula, Integer numSalaAula, Boolean ativo) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.numMatricula = numMatricula;
        this.numSalaAula = numSalaAula;
        this.ativo = ativo;
    }

    public Aluno(Integer idAluno, String nomeAluno, Integer numMatricula, Integer numSalaAula, Boolean ativo, List<Professor> professores) {
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.numMatricula = numMatricula;
        this.numSalaAula = numSalaAula;
        this.ativo = ativo;
        this.professores = professores;
    }

    public Integer getIdAluno() {
        return this.idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return this.nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Integer getNumMatricula() {
        return this.numMatricula;
    }

    public void setNumMatricula(Integer numeroMatricula) {
        this.numMatricula = numeroMatricula;
    }

    public Integer getNumSalaAula() {
        return this.numSalaAula;
    }

    public void setNumSalaAula(Integer numSalaAula) {
        this.numSalaAula = numSalaAula;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    @Override
    public String toString() {
        return "Aluno{" +
            "idAluno=" + idAluno +
            ", nomeAluno='" + nomeAluno + '\'' +
            ", numMatricula=" + numMatricula +
            ", numSalaAula=" + numSalaAula +
            ", ativo=" + ativo +
            ", professores=" + professores +
            '}';
        //
    }
}
