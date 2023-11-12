package br.edu.infnet.project.db.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "SalaAula")
public class SalaAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSalaAula")
    private Integer idSalaAula;

    @Column(name = "IdAluno", nullable = false, insertable=false, updatable=false)
    private Integer idAluno;

    @Column(name = "IdProfessor", nullable = false, insertable=false, updatable=false)
    private Integer idProfessor;

    public SalaAula() {
    }

    public SalaAula(Integer idSalaAula, Integer idAluno, Integer idProfessor) {
        this.idSalaAula = idSalaAula;
        this.idAluno = idAluno;
        this.idProfessor = idProfessor;
    }

    public Integer getIdSalaAula() {
        return idSalaAula;
    }

    public void setIdSalaAula(Integer idSalaAula) {
        this.idSalaAula = idSalaAula;
    }

    public Integer getIdAluno() {
        return this.idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public Integer getIdProfessor() {
        return this.idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return "SalaAula{" +
            "idSalaAula=" + idSalaAula +
            ", idAluno=" + idAluno +
            ", idProfessor=" + idProfessor +
            '}';
        //
    }
}
