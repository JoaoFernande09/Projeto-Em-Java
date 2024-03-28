package backend;

import java.io.Serializable;

/**
 * Representa um Utilizador genérico do sistema.
 *
 * Cada utilizador possui um username e uma password para autenticação no
 * sistema.
 */
public class Utilizador implements Serializable {

    private String username;
    private String password;

    /**
     * Cria uma instância de Utilizador com o username e password fornecidos.
     *
     * @param username O username do utilizador.
     * @param password A password do utilizador.
     */
    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Obtém o username do utilizador.
     *
     * @return O username do utilizador.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o username do utilizador.
     *
     * @param username O novo username do utilizador.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtém a password do utilizador.
     *
     * @return A password do utilizador.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a password do utilizador.
     *
     * @param password A nova password do utilizador.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Autentica o utilizador comparando o username e password fornecidos.
     *
     * @param username O username a ser verificado.
     * @param password A password a ser verificada.
     * @return true se as credenciais estiverem corretas, false caso contrário.
     */
    public boolean autenticar(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
