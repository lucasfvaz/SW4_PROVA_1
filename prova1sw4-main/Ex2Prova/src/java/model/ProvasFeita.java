package model;

import java.util.Date;
import java.util.Objects;

public class ProvasFeita {
    private String numeroMatricula ;
    private String nomedoAluno;
    private Date dataProva;
    private double nota;
   

    public ProvasFeita() {
    }

    public ProvasFeita(String numeroMatricula, String nomedoAluno, Date dataProva, double nota) {
        this.numeroMatricula = numeroMatricula;
        this.nomedoAluno = nomedoAluno;
        this.dataProva = dataProva;
        this.nota = nota;
    }
    
    
    
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getNomedoAluno() {
        return nomedoAluno;
    }

    public void setNomedoAluno(String nomedoAluno) {
        this.nomedoAluno = nomedoAluno;
    }

    public Date getDataProva() {
        return dataProva;
    }

    public void setDataProva(Date dataProva) {
        this.dataProva = dataProva;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.numeroMatricula);
        hash = 79 * hash + Objects.hashCode(this.nomedoAluno);
        hash = 79 * hash + Objects.hashCode(this.dataProva);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.nota) ^ (Double.doubleToLongBits(this.nota) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProvasFeita other = (ProvasFeita) obj;
        if (Double.doubleToLongBits(this.nota) != Double.doubleToLongBits(other.nota)) {
            return false;
        }
        if (!Objects.equals(this.numeroMatricula, other.numeroMatricula)) {
            return false;
        }
        if (!Objects.equals(this.nomedoAluno, other.nomedoAluno)) {
            return false;
        }
        return Objects.equals(this.dataProva, other.dataProva);
    }

    
    
}
    
