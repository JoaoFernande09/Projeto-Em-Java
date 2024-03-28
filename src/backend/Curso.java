package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Curso no sistema. Um Curso possui uma designação, um
 * diretor de curso, uma lista de alunos, uma lista de UCs e um repositório de
 * professores associados ao curso.
 */
public class Curso implements Serializable {

    private String designacao;
    private Professor diretorCurso;
    private RepositorioAlunos alunos;
    private RepositorioUC Ucs;
    private final RepositorioProfessores professores;

    /**
     * Construtor padrão que inicializa as listas de alunos e professores do
     * curso.
     */
    public Curso() {
        this.alunos = new RepositorioAlunos();
        this.professores = new RepositorioProfessores();
    }

    /**
     * Obtém a designação do curso.
     *
     * @return A designação do curso.
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Define a designação do curso.
     *
     * @param designacao Nova designação do curso.
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Obtém o diretor do curso.
     *
     * @return O diretor do curso.
     */
    public Professor getDiretorCurso() {
        return diretorCurso;
    }

    /**
     * Define o diretor do curso.
     *
     * @param diretorCurso Novo diretor do curso.
     */
    public void setDiretorCurso(Professor diretorCurso) {
        this.diretorCurso = diretorCurso;
    }

    /**
     * Obtém o repositório de alunos associados ao curso.
     *
     * @return O repositório de alunos do curso.
     */
    public RepositorioAlunos getAlunos() {
        return alunos;
    }

    /**
     * Define o repositório de alunos associados ao curso.
     *
     * @param alunos Novo repositório de alunos do curso.
     */
    public void setAlunos(RepositorioAlunos alunos) {
        this.alunos = alunos;
    }

    /**
     * Obtém o repositório de UCs associadas ao curso.
     *
     * @return O repositório de UCs do curso.
     */
    public RepositorioUC getUcs() {
        return Ucs;
    }

    /**
     * Define o repositório de UCs associadas ao curso.
     *
     * @param Ucs Novo repositório de UCs do curso.
     */
    public void setUcs(RepositorioUC Ucs) {
        this.Ucs = Ucs;
    }

    /**
     * Adiciona um aluno ao curso.
     *
     * @param numeroMecanografico Número mecanográfico único do aluno.
     * @param aluno Aluno a ser adicionado ao curso.
     */
    public void adicionarAluno(String numeroMecanografico, Aluno aluno) {
        alunos.inserirAluno(numeroMecanografico, aluno);
    }

    /**
     * Remove um aluno do curso.
     *
     * @param numeroMecanografico Número mecanográfico único do aluno a ser
     * removido.
     */
    public void removerAluno(String numeroMecanografico) {
        // Verifica se o aluno está associado ao curso
        if (alunos.existeAluno(numeroMecanografico)) {
            // Remove o aluno da lista de alunos do curso
            alunos.removerAluno(numeroMecanografico);

            System.out.println("Aluno removido do curso com sucesso.");
        } else {
            System.out.println("Aluno não encontrado no curso.");
        }
    }

    /**
     * Lista todos os alunos associados ao curso.
     *
     * @return Lista de alunos do curso.
     */
    public List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos.getListaAlunos().values());
    }

    /**
     * Atribui um professor como diretor do curso.
     *
     * @param diretorCurso Professor a ser atribuído como diretor do curso.
     */
    public void atribuirDirecaoCurso(Professor diretorCurso) {
        this.setDiretorCurso(diretorCurso);

    }

    /**
     * Obtém o número total de alunos no curso.
     *
     * @return Número total de alunos no curso.
     */
    public int getNumeroAlunos() {
        return alunos.getListaAlunos().size();
    }

    /**
     * Obtém o número total de professores no curso.
     *
     * @return Número total de professores no curso.
     */
    public int getNumeroProfessores() {
        return professores.getListaProfessores().size();
    }

}
