# prova1sw4
 ProvaSw4
Universidade Tecnológica Federal do Paraná - 
Campus Toledo
Curso de Tecnologia em Sistemas para Internet
TSI34C – Sítios Web 4
Prof. Roberto Milton Scheffel
1ª Avaliação – Servlets / JSP / JSF
1) (4,0 pontos) Escreva um servlet que receba o nome de um aluno e suas notas
em duas avaliações (N1 e N2). O servlet deverá calcular a média ponderada do aluno,
considerando a primeira avaliação com peso 2 e a segunda com peso 3, ou seja,
media = (N1 * 2 + N2 * 3) / 5. Considerando a média de aprovação 7,0, o servlet
deverá inserir os dados do aluno numa lista de aprovados ou numa lista de
reprovados. Deve ser possível “reiniciar” os lançamentos, limpando ambas as listas,
ao clicar no botão “Limpar Dados”. Fazer uma interface utilizando JSP + JSTL (sem
scriptlets, <% … %>), e os servlets não devem gerar HTML, usar apenas
response.sendRedirect(). Sugestão: utilize uma classe Aluno para armazenar os
dados, como o exemplo abaixo:
Exemplo da classe Aluno:
public class Aluno {
private String nome;
private double nota1, nota2;
public double getMedia() { … } ;
…
}
Exemplo de interface:
2) (6,0 pontos) Escreva uma aplicação JSF que faça uma prova automática de
tabuada. Na página inicial, o aluno deve digitar o seu número de matrícula e seu
nome, e clicar em “Iniciar Prova”. Se sua matrícula já estiver na lista de alunos da
aplicação, não deve ser possível repetir a prova. Senão, o aplicação deverá gerar
aleatoriamente 20 questões de tabuada: 10 mais fáceis (os números entre 1 e 5) e 10
mais difíceis (os números entre 5 e 9). Cada acerto somará 0.5 pontos à nota do
aluno. Ao terminar a 20ª questão, deve ser mostrada a nota do aluno, e um
lista de todos os alunos que já fizeram a prova, com as notas em ordem
decrescente. 
Observações:
a) Crie um bean de Aplicação, ou uma classe com o padrão Singleton, que
manterá um lista de todos os aluno que já fizeram a prova. Use uma lista de
objetos do tipo Prova, como o exemplo abaixo (ou outra estrutura
semelhante) para validar se o aluno já fez a prova.
b) Mantenha num bean de Sessão o progresso do aluno durante a prova, e ao
terminar a prova insira os dados na lista da aplicação. Se o aluno fechar o
browser antes de terminar as 20 questões, terá que fazer tudo novamente.
c) Para gerar as “questões”, use o gerador de numéros randomicos:
Random rnd = new Random( System.currentTimeMilis() );
…
int num1 = rnd.nextInt(5) + 1; // Gera um número entre 1 e 5
… 
int num2 = rnd.nextInt(5) + 5 // Gera um número entre 5 e 9
… 
Para ver se a resposta está certa, basta comparar se o valor informado pelo
usuário é igual a (num1 * num2)
public class Prova {
private String numeorMatricul;
private String nomeAluno;
private Date dataProva;
private double nota;
…
}
Exemplos de interface ( melhorar o visual, por favor =D )
1) Tela inicial, para informar a matrícula, nome e iniciar a prova. Se já
fez a prova, uma mensagem deve ser mostrada.
2) Tela de questão, se o número não for um inteiro entre 1 e 100, mostrar
uma mensagem de erro, e permanecer na mesma pergunta. Senão, registrar o
erro ou acerto e ir para a próxima pergunta.
3) Imprima uma tela de resultado
