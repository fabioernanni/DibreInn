package br.com.lebrehotel.dibreinn.controller.pessoas;

import br.com.lebrehotel.dibreinn.controller.emails.EnviarEmail;
import br.com.lebrehotel.dibreinn.model.Email;
import br.com.lebrehotel.dibreinn.model.pessoa.Endereco;
import br.com.lebrehotel.dibreinn.model.pessoa.Funcionario;
import br.com.lebrehotel.dibreinn.model.pessoa.Hospede;
import br.com.lebrehotel.dibreinn.model.pessoa.Pessoa;
import br.com.lebrehotel.dibreinn.model.pessoa.PessoaDAO;
import br.com.lebrehotel.dibreinn.model.unidade.UnidadeDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @authors jSilverize, Thiago, Ernanni, Luciano
 */
@WebServlet(name = "PessoaCadastrarServlet", urlPatterns = {"/erp/pessoas/cadastrar", "/erp/pessoas/cadastro"})
public class PessoaCadastrarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pessoaID = request.getParameter("id");

        if (pessoaID != null) {

            PessoaDAO consulta = new PessoaDAO();

            if (consulta.isFuncionario(Integer.parseInt(pessoaID))) {

                Funcionario func = new Funcionario();

                func = consulta.getFuncionario(Integer.parseInt(pessoaID));

                request.setAttribute("pessoa", func);

            } else {

                Hospede hosp = new Hospede();

                hosp = consulta.getHospede(Integer.parseInt(pessoaID));

                request.setAttribute("pessoa", hosp);

            }

        }

        //buscando apenas os quartos disponiveis
        UnidadeDAO consulta = new UnidadeDAO();
        request.setAttribute("lista", consulta.BuscarUnidades("buscartodasunidades", 0));

        RequestDispatcher rd = request.getRequestDispatcher("/erp/pessoas/cadastrar.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idNaURL = request.getParameter("id");

        Funcionario f = null;
        Hospede h = null;
        boolean resultado = false;

        try {
            //verificando qual tipo
            if (request.getParameter("formTipo").equalsIgnoreCase("h")) {

                h = new Hospede();
                h.setTipo("h");
                h.setNome(request.getParameter("formNome"));
                h.setSobrenome(request.getParameter("formSobrenome"));
                h.setSexo(request.getParameter("formSexo"));
                h.setRg(request.getParameter("formRg"));
                h.setCpf(request.getParameter("formCpf"));
                h.setDataNascimento(java.sql.Date.valueOf(request.getParameter("formDataNasc")));
                h.setTelefone(request.getParameter("formTel"));
                h.setCelular(request.getParameter("formCel"));
                h.setEmail(request.getParameter("formEmail"));
                if (request.getParameter("formNewsletter") != null) {
                    h.setNewsletter(Integer.parseInt(request.getParameter("formNewsletter")));
                } else {
                    h.setNewsletter(0);
                }
                h.setnCartao(request.getParameter("formCartao"));
                Endereco end = new Endereco();
                end.setCep((request.getParameter("formCep")));
                end.setLogradouro(request.getParameter("formLogradouro"));
                end.setNumero(request.getParameter("formNumero"));
                end.setComplemento(request.getParameter("formComplemento"));
                end.setBairro(request.getParameter("formBairro"));
                end.setCidade(request.getParameter("formCidade"));
                end.setEstado(request.getParameter("formEstado"));
                end.setEstado(request.getParameter("formPais"));

                PessoaDAO teste = new PessoaDAO();

                // Através do parâmetro ID na URL, iremos verificar se trata-se de um
                // novo cadastrou ou se é uma atualização de um cadastro já existente
                if (idNaURL == null) {
                    System.out.println("Realizando novo cadastro.");
                    h.setId(
                            teste.cadastrarPessoa(h, end)
                    );
                } else {
                    System.out.println("Atualizando cadastro.");
                    // teste.atualizarPessoa(p, end);
                }
                response.sendRedirect("visualizar?id=" + h.getId());
            } else {

                f = new Funcionario();
                f.setTipo("f");
                f.setNome(request.getParameter("formNome"));
                f.setSobrenome(request.getParameter("formSobrenome"));
                f.setSexo(request.getParameter("formSexo"));
                f.setRg(request.getParameter("formRg"));
                f.setCpf(request.getParameter("formCpf"));
                f.setDataNascimento(java.sql.Date.valueOf(request.getParameter("formDataNasc")));
                f.setTelefone(request.getParameter("formTel"));
                f.setCelular(request.getParameter("formCel"));
                f.setEmail(request.getParameter("formEmail"));
                if (request.getParameter("formNewsletter") != null) {
                    f.setNewsletter(Integer.parseInt(request.getParameter("formNewsletter")));
                } else {
                    f.setNewsletter(0);
                }
                if (request.getParameter("formOpUsuario").equalsIgnoreCase("1")) {
                    f.setLogin(request.getParameter("formEmail"));
                    f.setSenha(request.getParameter("formSenha"));
                }
                //verificar se o salario foi informado
                if (!request.getParameter("formSalario").isEmpty()) {
                    f.setSalario(Double.parseDouble(request.getParameter("formSalario")));
                } else {
                    f.setSalario(0.00);
                }
                f.setDepartamento(request.getParameter("formDepartamento"));
                f.setCargo(request.getParameter("formCargo"));
                f.setUnidade(request.getParameter("formUnidade"));
                //Object p rece um Funcioário
                Endereco end = new Endereco();
                end.setCep((request.getParameter("formCep")));
                end.setLogradouro(request.getParameter("formLogradouro"));
                end.setNumero(request.getParameter("formNumero"));
                end.setComplemento(request.getParameter("formComplemento"));
                end.setBairro(request.getParameter("formBairro"));
                end.setCidade(request.getParameter("formCidade"));
                end.setEstado(request.getParameter("formEstado"));

                PessoaDAO teste = new PessoaDAO();

                // Através do parâmetro ID na URL, iremos verificar se trata-se de um
                // novo cadastrou ou se é uma atualização de um cadastro já existente
                if (idNaURL == null) {
                    System.out.println("Realizando novo cadastro.");
                    f.setId(
                            teste.cadastrarPessoa(f, end)
                    );
                } else {
                    System.out.println("Atualizando cadastro.");
                    // teste.atualizarPessoa(p, end);
                }
                response.sendRedirect("visualizar?id=" + f.getId());
            }

            /* Teste se o resultado do cadastro foi positivo. Se for envia o email.
             if (h.getId() != 0) {
             enviaEmail(p);
             }
             */

            /* 
             * Esse redirecionamento acontecerá após o submit dos dados,
             * levando o usuário para uma página com os dados que acabaram de ser
             * cadastrados
             */
        } catch (Exception ex) {
            System.out.println(ex);
            response.sendRedirect("erro.jsp");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    //Funcão para enviar e-mail a pessoa cadastrada
    private void enviaEmail(Hospede h) {
        System.out.println("[DADOS GRAVADOS COM SUCESSO] Novo cadastro: " + h.getNome() + " " + h.getSobrenome());
        Email email = new Email();
        email.setDestinatario(h.getEmail());
        email.setAssunto("Cadastro Efetuado");
        email.setMensagem(h.getNome() + ", seja bem-vindo e obrigado por efetuar o cadastro no Lebre Hotel!");
        EnviarEmail envia = new EnviarEmail();
        envia.EnviarEmail(email);
    }

}
