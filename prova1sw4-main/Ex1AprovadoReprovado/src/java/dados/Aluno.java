
package dados;


public class Aluno {
  private String nome;
  private double nota1,nota2; 

    public Aluno(String nome, double nota1, double nota2) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public Aluno() {
        
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }
  
    public double getMedia(){
       double media = ((this.nota1 * 2) + (this.nota2 * 3))/5;
       
       return media;
    }
  
    public static String format(double x) {
    return String.format("%.2f", x);
    }
  
}
