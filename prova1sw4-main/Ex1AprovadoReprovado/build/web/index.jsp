<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="listaAprovados" scope="session" class="java.util.ArrayList"/>
<jsp:useBean id="listaReprovados" scope="session" class="java.util.ArrayList"/>
<jsp:useBean id="msgErro" scope="session" class="java.lang.String"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.min.css" rel="stylesheet" >
        <title>Alunos Aprovado ou Reprovado</title>
    </head>
    <body>
        <div class="container">
        <h1 >Dados do aluno</h1>
        <form action="aprovadosereprovados" method="POST">
            Nome <input type="text"   class="form-control w-25" name="nome" size="80" value="" /><br/>
            Nota 1: <input type="text"  class="form-control w-25" name="nota1" size="3" value=""/>
            Nota 2: <input type="text"  class="form-control w-25" name="nota2" size="3" value=""/><br/>
            <input type="submit" value="Lançar" class="btn btn-success"/>
        </form>
        <c:if test="${not empty msgErro}">
            <p style="color: red">${msgErro}</p>
        </c:if>
           
            <c:if test="${not empty listaAprovados || not empty listaReprovados}">
               <h1>Resultados: </h1> 
            </c:if>
               <div style="display: flex; gap:20%;">   
        <c:if test="${not empty listaAprovados}">
            <div style="width: 30%; ">
            <h3>Aprovados</h3>
            <table  style="width: 100%; " class="table table-striped">
                <tr>
                    <th style="padding:1px; ">Nome</th>
                    <th style="padding:1px; ">Nota 1</th>
                    <th style="padding:1px; ">Nota 2</th>
                    <th style="padding:1px; ">Media</th>
                </tr>
                
                <c:forEach items="${listaAprovados}" var="aluno">
                    <tr>
                        <td>${aluno.nome}</td>
                        <td>${aluno.nota1}</td>
                        <td>${aluno.nota2}</td>
                        <td>${aluno.format(aluno.getMedia())}</td>
                    </tr>
                </c:forEach>
            </table>
             </div>
          </c:if>
            
            <c:if test="${not empty listaReprovados}">
            <div style="width: 30%; ">
            <h3>Reprovados</h3>
            <table  style="width: 100%; " class="table table-striped">
                <tr>
                    <th style="padding:1px; ">Nome</th>
                    <th style="padding:1px; ">Nota 1</th>
                    <th style="padding:1px; ">Nota 2</th>
                    <th style="padding:1px; ">Media</th>
                </tr>
                
                <c:forEach items="${listaReprovados}" var="aluno">
                    <tr>
                        <td>${aluno.nome}</td>
                        <td>${aluno.nota1}</td>
                        <td>${aluno.nota2}</td>
                        <td>${aluno.format(aluno.getMedia())}</td>
                    </tr>
                </c:forEach>
            </table>
            </div>
          </c:if> 
          </div>
               <br/> 
               <br/>
            <c:if test="${not empty listaAprovados || not empty listaReprovados}">
                <form action="aprovadosereprovados" method="POST">
                <input type="hidden" name="acao" value="limpar" />
                 <input type="submit" value="Limpar Listas" class="btn btn-danger"/>
                  </form>
            </c:if>
          <c:remove var="msgErro" scope="session"/>
          
         </div>
    </body>
</html>
