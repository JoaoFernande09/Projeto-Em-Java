package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um repositório de professores no sistema. Um
 * RepositorioProfessores contém um mapa de professores, onde a chave é o nome
 * de usuário (username) e o valor é a instância do professor correspondente.
 */
public class RepositorioProfessores implements Serializable {

    private Map<String, Professor> listaProfessores;

    /**
     * Construtor que inicializa um RepositorioProfessores com um mapa vazio.
     */
    public RepositorioProfessores() {
        this.listaProfessores = new HashMap<>();
    }

    /**
     * Obtém o mapa de professores associado ao repositório.
     *
     * @return O mapa de professores.
     */
    public Map<String, Professor> getListaProfessores() {
        return listaProfessores;
    }

    /**
     * Define o mapa de professores associado ao repositório.
     *
     * @param listaProfessores Novo mapa de professores.
     */
    public void setListaProfessores(Map<String, Professor> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }

    /**
     * Lista todos os professores no repositório.
     *
     * @return Lista de professores.
     */
    public List<Professor> listarProfessores() {
        return new ArrayList<>(listaProfessores.values());
    }

    /**
     * Insere um professor no repositório.
     *
     * @param username Nome de usuário (username) único do professor.
     * @param professor Instância do professor a ser inserida.
     */
    public void inserirProfessor(String username, Professor professor) {
        listaProfessores.put(username, professor);
    }

    /**
     * Remove um professor do repositório com base no nome de usuário
     * (username).
     *
     * @param username Nome de usuário (username) do professor a ser removido.
     */
    public void removerProfessor(String username) {
        listaProfessores.remove(username);
    }

    /**
     * Verifica se um professor existe no repositório com base no nome de
     * usuário (username).
     *
     * @param username Nome de usuário (username) do professor a ser verificado.
     * @return Verdadeiro se o professor existir, falso caso contrário.
     */
    public boolean existeProfessor(String username) {
        return listaProfessores.containsKey(username);
    }

    /**
     * Obtém a instância de um professor com base no nome de usuário (username).
     *
     * @param username Nome de usuário (username) do professor a ser obtido.
     * @return Instância do professor, ou null se não encontrado.
     */
    public Professor getProfessor(String username) {
        return listaProfessores.get(username);
    }

    /**
     * Autentica um professor com base no nome de usuário (username) e na senha
     * fornecidos.
     *
     * @param username Nome de usuário (username) do professor.
     * @param password Senha do professor.
     * @return Instância do professor autenticado, ou null se não autenticado.
     */
    public Professor autenticarProfessor(String username, String password) {
        if (existeProfessor(username)) {
            Professor professor = getProfessor(username);
            if (professor.autenticar(username, password)) {
                return professor;
            }
        }
        return null;
    }

}
