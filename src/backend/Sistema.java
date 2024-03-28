package backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe que representa o sistema da aplicação. Mantém informações sobre as
 * entidades principais do sistema, como cursos, alunos, professores, etc.
 * Fornece funcionalidades para autenticação de utilizadores e manipulação de
 * dados.
 */
public class Sistema implements Serializable {

    public RepositorioUC ucs;
    public RepositorioAlunos alunos;
    public RepositorioCursos cursos;
    public RepositorioProfessores professores;
    public RepositorioSumarios sumarios;
    public RepositorioUtilizadores utilizadores;
    public Administrador administrador;
    private Utilizador utilizadorLigado;

    /**
     * Construtor padrão que inicializa as coleções e o administrador do
     * sistema.
     */
    public Sistema() {
        this.ucs = new RepositorioUC();
        this.alunos = new RepositorioAlunos();
        this.cursos = new RepositorioCursos();
        this.professores = new RepositorioProfessores();
        this.sumarios = new RepositorioSumarios();
        this.utilizadores = new RepositorioUtilizadores();
        this.administrador = new Administrador("admin", "senhaAdmin");

    }

    /**
     * Obtém o administrador do sistema.
     *
     * @return O administrador do sistema.
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Obtém o repositório de Unidades Curriculares (UCs) do sistema.
     *
     * @return O repositório de UCs.
     */
    public RepositorioUC getUcs() {
        return ucs;
    }

    /**
     * Obtém o repositório de alunos do sistema.
     *
     * @return O repositório de alunos.
     */
    public RepositorioAlunos getAlunos() {
        return alunos;
    }

    /**
     * Obtém o repositório de cursos do sistema.
     *
     * @return O repositório de cursos.
     */
    public RepositorioCursos getCursos() {
        return cursos;
    }

    /**
     * Obtém o repositório de professores do sistema.
     *
     * @return O repositório de professores.
     */
    public RepositorioProfessores getProfessores() {
        return professores;
    }

    /**
     * Obtém o repositório de sumários do sistema.
     *
     * @return O repositório de sumários.
     */
    public RepositorioSumarios getSumarios() {
        return sumarios;
    }

    /**
     * Define o administrador do sistema.
     *
     * @param administrador O novo administrador do sistema.
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Define o utilizador ligado ao sistema.
     *
     * @param utilizadorLigado O utilizador que está atualmente autenticado no
     * sistema.
     */
    public void setUtilizadorLigado(Utilizador utilizadorLigado) {
        this.utilizadorLigado = utilizadorLigado;
    }

    /**
     * Obtém o utilizador atualmente ligado ao sistema.
     *
     * @return O utilizador atualmente autenticado no sistema.
     */
    public Utilizador getUtilizadorLigado() {
        return utilizadorLigado;
    }

    /**
     * Autentica um utilizador no sistema com base no nome de utilizador e na
     * palavra-passe.
     *
     * @param username O nome de utilizador.
     * @param password A palavra-passe.
     * @return O utilizador autenticado ou null se as credenciais forem
     * inválidas.
     */
    public Utilizador autenticarUtilizador(String username, String password) {
        if (utilizadores.existeUtilizador(username)) {
            Utilizador utilizador = utilizadores.getUtilizador(username);
            if (utilizador.autenticar(username, password)) {
                return utilizador;
            }
        }
        return null; // Credenciais inválidas ou utilizador não encontrado
    }

    /**
     * Remove um professor do sistema com base no nome de utilizador.
     *
     * @param username O nome de utilizador do professor a ser removido.
     */
    public void removerProfessor(String username) {
        if (this.professores.existeProfessor(username)) {
            this.professores.removerProfessor(username);
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    /**
     * Guarda o estado atual do sistema num ficheiro de objetos.
     *
     * @param nomeFicheiro O nome do ficheiro onde o estado será guardado.
     * @throws Exception Se ocorrer um erro durante a operação de escrita do
     * objeto.
     */
    public void guardarFicheiroObjetos(String nomeFicheiro) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(nomeFicheiro); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        }
    }

    /**
     * Carrega o estado de um sistema a partir de um ficheiro de objetos.
     *
     * @param nomeFicheiro O nome do ficheiro de onde o estado será carregado.
     * @return O sistema carregado a partir do ficheiro.
     * @throws IOException Se ocorrer um erro de entrada/saída durante a
     * operação de leitura do objeto.
     * @throws ClassNotFoundException Se a classe do objeto lido não for
     * encontrada.
     */
    public static Sistema carregarEstado(String nomeFicheiro) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            return (Sistema) ois.readObject();
        }
    }
}
