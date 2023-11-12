package br.edu.infnet.project.db.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProfessor")
    private Integer idProfessor;

    @OneToMany(mappedBy = "professores")
    @Column(name = "IdAluno", insertable = false, updatable = false)
    private List<Aluno> alunos;

    @Column(name = "NomeProfessor", nullable = false)
    private String nomeProfessor;

    @Column(name = "NumSalaAula", nullable = false)
    private Integer numSalaAula;

    @Column(name = "Materia", nullable = false)
    private String materia;

    public Professor() {
    }

    public Professor(List<Aluno> alunos, String nomeProfessor, Integer numSalaAula, String materia) {
        this.alunos = alunos;
        this.nomeProfessor = nomeProfessor;
        this.numSalaAula = numSalaAula;
        this.materia = materia;
    }

    public Integer getIdProfessor() {
        return this.idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String getNomeProfessor() {
        return this.nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getNumSalaAula() {
        return this.numSalaAula;
    }

    public void setNumSalaAula(Integer numSalaAula) {
        this.numSalaAula = numSalaAula;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public String toString() {
        return "Professor{" +
            "idProfessor=" + idProfessor +
            ", aluno=" + alunos +
            ", nomeProfessor='" + nomeProfessor + '\'' +
            ", numSalaAula=" + numSalaAula +
            ", materia='" + materia + '\'' +
            '}';
        //
    }
}
