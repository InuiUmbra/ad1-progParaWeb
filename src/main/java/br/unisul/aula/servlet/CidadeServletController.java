package br.unisul.aula.servlet;

import br.unisul.aula.banco.ClienteDAO;
import br.unisul.aula.banco.EnderecoDAO;
import br.unisul.aula.dtocliente.ClienteIdNomeDTO;
import br.unisul.aula.modelo.Cliente;
import br.unisul.aula.modelo.Endereco;
import br.unisul.aula.modelo.RelacaoCidadeUfCliente;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "CidadeServletController", value = "/cidadeServlet")
public class CidadeServletController extends HttpServlet {

    private final Gson gson = new Gson();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=ISO-8859-1");
        response.setCharacterEncoding("ISO-8859-1");
        String resultadoJson;

        String requestCidade = request.getParameter("cidade");
        if(requestCidade != null && !requestCidade.isEmpty()){
            requestCidade = requestCidade.substring(0, 1).toUpperCase() + requestCidade.substring(1).toLowerCase();
            List<Cliente> clientes = clienteDAO.findByCidade(requestCidade);
            Endereco endereco = enderecoDAO.findByCidade(requestCidade);
            List<ClienteIdNomeDTO> clienteIdNomeDTO = new ArrayList<>();
            if (clientes.size() > 0){
                for (Cliente cliente: clientes) {
                    ClienteIdNomeDTO clientesIdNomeDTOTemp = new ClienteIdNomeDTO(cliente.getId(), cliente.getNome());
                    clienteIdNomeDTO.add(clientesIdNomeDTOTemp);
                }
            }
            RelacaoCidadeUfCliente relacaoCidadeUfCliente = new RelacaoCidadeUfCliente();
            relacaoCidadeUfCliente.setCidade(endereco.getCidade());
            relacaoCidadeUfCliente.setUf(endereco.getUf());
            relacaoCidadeUfCliente.setClientesIdNome(clienteIdNomeDTO);

            resultadoJson = gson.toJson(relacaoCidadeUfCliente);
            response.getWriter().println(resultadoJson);
        }
        else if(requestCidade == null || requestCidade.isEmpty()){
            response.getWriter().println("Por favor adicione um parâmetro 'cidade' após o endereço do Servlet \n" +
                    "Exemplo: /cidadeServlet?cidade=desterro");
        }
    }
}
