package backend;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Classe que representa um repositório de cursos no sistema. Um
 * RepositorioCursos contém uma árvore de cursos, onde a chave é a designação
 * única do curso e o valor é a instância do curso correspondente.
 */
public class RepositorioCursos implements Serializable {

    private TreeMap<String, Curso> listaCursos;

    /**
     * Construtor que inicializa um RepositorioCursos com uma árvore vazia.
     */
    public RepositorioCursos() {
        this.listaCursos = new TreeMap<>();
    }

    /**
     * Obtém a árvore de cursos associada ao repositório.
     *
     * @return A árvore de cursos.
     */
    public TreeMap<String, Curso> getListaCursos() {
        return listaCursos;
    }

    /**
     * Define a árvore de cursos associada ao repositório.
     *
     * @param listaCursos Nova árvore de cursos.
     */
    public void setListaCursos(TreeMap<String, Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    /**
     * Insere um curso no repositório.
     *
     * @param designacao Designação única do curso.
     * @param curso Instância do curso a ser inserida.
     */
    public void inserirCurso(String designacao, Curso curso) {
        listaCursos.put(designacao, curso);
    }

    /**
     * Remove um curso do repositório.
     *
     * @param designacao Designação única do curso a ser removido.
     */
    public void removerCurso(String designacao) {
        listaCursos.remove(designacao);
    }

    /**
     * Verifica se um curso existe no repositório com base na designação.
     *
     * @param designacao Designação única do curso a ser verificado.
     * @return Verdadeiro se o curso existir, falso caso contrário.
     */
    public boolean existeCurso(String designacao) {
        return listaCursos.containsKey(designacao);
    }

    /**
     * Atribui um diretor de curso a um curso específico.
     *
     * @param designacaoCurso Designação única do curso.
     * @param diretorCurso Professor a ser atribuído como diretor do curso.
     */
    public void atribuirDiretorCurso(String designacaoCurso, Professor diretorCurso) {
        if (existeCurso(designacaoCurso)) {
            Curso curso = listaCursos.get(designacaoCurso);
            curso.atribuirDirecaoCurso(diretorCurso);
            System.out.println("Diretor de curso atribuído com sucesso.");
        } else {
            System.out.println("Curso não encontrado. Não é possível atribuir o diretor de curso.");
        }
    }

}
