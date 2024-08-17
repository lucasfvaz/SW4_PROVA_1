/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controle;


import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.ComparaNota;

import model.Prova;
import model.ProvasFeita;
import model.Questoes;
import model.Resposta;

/**
 *
 * @author aluno
 */
@Named(value = "progressoBean")
@SessionScoped
public class ProgressoBean implements Serializable {
    private  List<Prova> listaProva;
    private List <ProvasFeita> listaProvasFeitas;
    private  ArrayList<Questoes> listaQuestoes;
    private ArrayList<Resposta> listaresposta;
    private static int resposta; 
    private static int indiceresposta;
    private Prova atualprova;
    private ProvasFeita provasFeita;
    private Questoes atualquestoes;
    private Resposta atualresposta;
    private int indiceatual = 1;
   
    public ProgressoBean() {
        listaresposta = new ArrayList<>();
        listaQuestoes = new ArrayList<>();
        listaProva = new ArrayList<>();
        listaProvasFeitas = new ArrayList<>();
        atualprova = new Prova();
        provasFeita = new ProvasFeita();
        atualquestoes = new Questoes();
        atualresposta = new Resposta();
        resposta = 0;
        
        indiceresposta = this.atualquestoes.getIndice();
    }

    

    public void setListaProva(ArrayList<Prova> listaProva) {
        this.listaProva = listaProva;
    }

   
 
   
    public  String validarEincluir(){
           Date agora = new Date();
           atualprova.setDataProva(agora);
          
        
    if (this.provaExiste(listaProvasFeitas,atualprova)) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Você já fez a prova", "Você já fez a prova"));
         return null;
    }else{
        listaProva.add(atualprova);
        this.geraNumerosAleatorios();
        return "prova.xhtml";
     }
 }
    public String responde() {
      resposta = this.atualquestoes.getNum1() * this.atualquestoes.getNum2();
      this.atualquestoes.setResultado(resposta);
       
        
       if(indiceresposta <= 20){
          if(resposta == Integer.parseInt(atualresposta.getResposta())){
          atualresposta.setCertaouerrada(true);
          FacesContext.getCurrentInstance().addMessage("acertouresposta",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Acertou a Resposta", "Acertou a Resposta"));
          }else{
          atualresposta.setCertaouerrada(false);
          FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Errou a Resposta", "Errou a Resposta"));
          }
          
        
        listaresposta.add(atualresposta);
        
        atualresposta = new Resposta();
        
         this.geraNumerosAleatorios();
        
        if(indiceresposta < 20){
        this.atualquestoes.setIndice(indiceresposta + 1);
        }else{
          this.atualquestoes.setIndice(indiceresposta);  
        }
        indiceresposta += 1; 
      }
       if(indiceresposta == 21){
          FacesContext.getCurrentInstance().addMessage("acertouresposta",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "", ""));
          FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "", ""));
          this.atualquestoes.setIndice(1);
          this.atualprova.setNota(this.Nota(listaresposta));
          listaresposta.clear();
          indiceresposta = 1;
          return "resultados.xhtml"; 
       }else{
         return null;   
       }
      
    }

    public List<Prova> getListaProva() {
        return listaProva;
    }

    public void setListaProva(List<Prova> listaProva) {
        this.listaProva = listaProva;
    }

    public ArrayList<Questoes> getListaQuestoes() {
        return listaQuestoes;
    }

    public void setListaQuestoes(ArrayList<Questoes> listaQuestoes) {
        this.listaQuestoes = listaQuestoes;
    }

    public Questoes getAtualquestoes() {
        return atualquestoes;
    }

    public void geraNumerosAleatorios(){
        Random rnd = new Random( System.currentTimeMillis() );
        int num1facil = rnd.nextInt(5) + 1;
        int num2facil = rnd.nextInt(5) + 1;// Gera um número entre 1 e 5
         int num1dificil = rnd.nextInt(5) + 5;
         int num2dificil = rnd.nextInt(5) + 5;
       // Gera um número entre 5 e 9
       
         //Aqui gera as 10 mais fáceis e 10 mais dificfil
         if(this.atualquestoes.getIndice()  < 10){
         atualquestoes.setNum1(num1facil);
         atualquestoes.setNum2(num2facil);
         }else{
            atualquestoes.setNum1(num1dificil);
            atualquestoes.setNum2(num2dificil); 
         }
         listaQuestoes.add(atualquestoes);
         
    }
    public boolean provaExiste(List<ProvasFeita> listaProvas ,Prova atual) {
        for (ProvasFeita p : listaProvas) {
            if (p.getNumeroMatricula().equals(atual.getNumeroMatricula()) || 
                    p.getNomedoAluno().equals(atual.getNomedoAluno())) {
                return true;
            }
        }
        return false;
    }
    public Double Nota(ArrayList<Resposta> listaresposta) {
        double countCerta = 0;
        double nota;
        for (Resposta r : listaresposta) {
            if(r.isCertaouerrada()){ 
               countCerta++;
            }
        }
        nota = countCerta * 0.5;
        return nota;
    }
    public String returnInicio(){
        provasFeita = new ProvasFeita(atualprova.getNumeroMatricula(),
        atualprova.getNomedoAluno(),atualprova.getDataProva(),atualprova.getNota());
        listaProvasFeitas.add(provasFeita);
        ComparaNota comparetor =  new ComparaNota();
        Collections.sort(listaProvasFeitas, comparetor);
        atualprova = new Prova();
        provasFeita = new ProvasFeita();
        return "index.xhtml";
    }
    public void setAtualquestoes(Questoes atualquestoes) {
        this.atualquestoes = atualquestoes;
    }

    public Prova getAtualprova() {
        return atualprova;
    }

    public void setAtualprova(Prova atualprova) {
        this.atualprova = atualprova;
    }
    
    public static int getResposta() {
        return resposta;
    }

    public static void setResposta(int resposta) {
        ProgressoBean.resposta = resposta;
    }
    
     public ArrayList<Resposta> getListaresposta() {
        return listaresposta;
    }

    public void setListaresposta(ArrayList<Resposta> listaresposta) {
        this.listaresposta = listaresposta;
    }
     public Resposta getAtualresposta() {
        return atualresposta;
    }

    public void setAtualresposta(Resposta atualresposta) {
        this.atualresposta = atualresposta;
    }

    public static int getIndiceresposta() {
        return indiceresposta;
    }

    public static void setIndiceresposta(int indiceresposta) {
        ProgressoBean.indiceresposta = indiceresposta;
    }

    public int getIndiceatual() {
        return indiceatual;
    }

    public void setIndiceatual(int indiceatual) {
        this.indiceatual = indiceatual;
    }

    public List<ProvasFeita> getListaProvasFeitas() {
        return listaProvasFeitas;
    }

    public void setListaProvasFeitas(List<ProvasFeita> listaProvasFeitas) {
        this.listaProvasFeitas = listaProvasFeitas;
    }

    public ProvasFeita getProvasFeita() {
        return provasFeita;
    }

    public void setProvasFeita(ProvasFeita provasFeita) {
        this.provasFeita = provasFeita;
    }
    
    
}
