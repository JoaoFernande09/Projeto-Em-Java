package backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um repositório de Utilizadores. Mantém uma coleção de
 * utilizadores indexados pelos seus nomes de utilizador.
 */
public class RepositorioUtilizadores implements Serializable {

    private Map<String, Utilizador> utilizadores;

    /**
     * Construtor padrão que inicializa um novo RepositorioUtilizadores.
     */
    public RepositorioUtilizadores() {
        this.utilizadores = new HashMap<>();
    }

    /**
     * Insere um novo utilizador no repositório.
     *
     * @param username O nome de utilizador do utilizador a ser inserido.
     * @param utilizador O utilizador a ser inserido no repositório.
     */
    public void inserirUtilizador(String username, Utilizador utilizador) {
        utilizadores.put(username, utilizador);
    }

    /**
     * Obtém um utilizador específico com base no seu nome de utilizador.
     *
     * @param username O nome de utilizador do utilizador a ser obtido.
     * @return O utilizador correspondente ao nome de utilizador fornecido, ou
     * null se não existir.
     */
    public Utilizador getUtilizador(String username) {
        return utilizadores.get(username);
    }

    /**
     * Verifica se um utilizador com um determinado nome de utilizador existe no
     * repositório.
     *
     * @param username O nome de utilizador a ser verificado.
     * @return true se o utilizador existir, false caso contrário.
     */
    public boolean existeUtilizador(String username) {
        return utilizadores.containsKey(username);
    }
}
