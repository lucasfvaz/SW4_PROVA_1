/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controle;

import dados.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ertel
 */
@WebServlet(name = "Gerenciador", urlPatterns = {"/aprovadosereprovados"})
public class Gerenciador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession sessao = request.getSession();
        ArrayList<Aluno> listaAprovados = (ArrayList<Aluno>) sessao.getAttribute("listaAprovados");
        ArrayList<Aluno> listaReprovados = (ArrayList<Aluno>) sessao.getAttribute("listaReprovados");
        String acao = request.getParameter("acao");
        if (listaAprovados == null) {
            listaAprovados = new ArrayList<Aluno>();
            sessao.setAttribute("listaAprovados", listaAprovados);
        }
        if(listaReprovados == null){
           listaReprovados = new ArrayList<Aluno>();
            sessao.setAttribute("listaReprovados", listaReprovados); 
        }
        if ("limpar".equals(acao)) {
            listaAprovados.clear();
            listaReprovados.clear();
            sessao.invalidate(); 
            response.sendRedirect("index.jsp");
            return;
          }
        try {
            String nome = request.getParameter("nome");
            String reqnota1 = request.getParameter("nota1");
            String reqnota2 = request.getParameter("nota2");
            Pattern p=Pattern.compile("[0-9]+");
      
            if(nome.equals("") || reqnota1.equals("") || 
                   reqnota2.equals("")){
              throw new Exception("Faltou prencher um campo"); 
            }
            if(p.equals(nome)){
               throw new Exception("O nome deve ser  letras e não numeros");  
            }
            DecimalFormat df = new DecimalFormat();
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.forLanguageTag("pt"));
            double nota1 = df.parse(reqnota1).doubleValue();  
            double nota2 = df.parse(reqnota2).doubleValue();
            Aluno aluno = new Aluno(nome,nota1,nota2);
           
           
            
           if(nota1 > 10 || nota2 > 10){
               throw new Exception("As notas vão até 10,tem que ser menor que 10.</br>E use virgulas em vez de ponto para numeros fracionários");
               
           }else{
            
             if(aluno.getMedia() >= 7.0){
             listaAprovados.add(aluno);
             }else{
                listaReprovados.add(aluno);  
             }
           }
           

        }catch(ParseException ex){
          sessao.setAttribute("msgErro", "Digite um numero");
        
        }catch (Exception ex){
            sessao.setAttribute("msgErro", ex.getMessage());
        }
        response.sendRedirect("index.jsp");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
