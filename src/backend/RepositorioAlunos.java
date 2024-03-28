package backend;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um repositório de alunos no sistema. Um
 * RepositorioAlunos contém um mapa de alunos, onde a chave é o número
 * mecanográfico único do aluno e o valor é a instância do aluno correspondente.
 */
public class RepositorioAlunos implements Serializable {

    private Map<String, Aluno> listaAlunos;

    /**
     * Obtém o mapa de alunos associado ao repositório.
     *
     * @return O mapa de alunos.
     */
    public Map<String, Aluno> getListaAlunos() {
        return listaAlunos;
    }

    /**
     * Define o mapa de alunos associado ao repositório.
     *
     * @param listaAlunos Novo mapa de alunos.
     */
    public void setListaAlunos(Map<String, Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    /**
     * Construtor que inicializa um RepositorioAlunos com um mapa vazio.
     */
    public RepositorioAlunos() {
        this.listaAlunos = new HashMap<>();
    }

    /**
     * Insere um aluno no repositório.
     *
     * @param numeroMecanografico Número mecanográfico único do aluno.
     * @param aluno Instância do aluno a ser inserida.
     */
    public void inserirAluno(String numeroMecanografico, Aluno aluno) {
        listaAlunos.put(numeroMecanografico, aluno);
    }

    /**
     * Remove um aluno do repositório.
     *
     * @param numeroMecanografico Número mecanográfico único do aluno a ser
     * removido.
     */
    public void removerAluno(String numeroMecanografico) {
        listaAlunos.remove(numeroMecanografico);
    }

    /**
     * Verifica se um aluno existe no repositório com base no número
     * mecanográfico.
     *
     * @param numeroMecanografico Número mecanográfico único do aluno a ser
     * verificado.
     * @return Verdadeiro se o aluno existir, falso caso contrário.
     */
    public boolean existeAluno(String numeroMecanografico) {
        return listaAlunos.containsKey(numeroMecanografico);
    }
}
