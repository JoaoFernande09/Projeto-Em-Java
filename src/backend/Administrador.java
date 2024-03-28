package backend;

import java.util.Map;

/**
 * Representa um Administrador no sistema. Um Administrador tem a capacidade de
 * adicionar, remover ou alterar informações sobre professores, cursos,
 * unidadescurriculares (UCs) e listar informações do sistema.
 */
public class Administrador extends Utilizador {

    /**
     * Construtor para criar um novo Administrador com um nome de usuário e
     * senha.
     *
     * @param username O nome de usuário do Administrador.
     * @param password A senha do Administrador.
     */
    public Administrador(String username, String password) {
        super(username, password);
    }

    /**
     * Autentica o Administrador com um nome de usuário e senha.
     *
     * @param username O nome de usuário a ser verificado.
     * @param password A senha a ser verificada.
     * @return true se a autenticação for bem-sucedida, false caso contrário.
     */
    public boolean autenticar(String username, String password) {
        return super.getUsername().equals(username) && super.getPassword().equals(password);
    }

    // Métodos para adicionar, remover ou alterar informações de professores
    /**
     * Adiciona um novo professor ao sistema.
     *
     * @param repositorioProfessores Repositório de professores no qual o
     * professor será adicionado.
     * @param professor Professor a ser adicionado.
     */
    public void adicionarProfessor(RepositorioProfessores repositorioProfessores, Professor professor) {
        repositorioProfessores.inserirProfessor(professor.getNumeroMecanografico(), professor);
        System.out.println("Professor adicionado com sucesso.");
    }

    /**
     * Remove um professor do sistema.
     *
     * @param repositorioProfessores Repositório de professores no qual o
     * professor será removido.
     * @param username Nome de usuário do professor a ser removido.
     */
    public void removerProfessor(RepositorioProfessores repositorioProfessores, String username) {
        if (repositorioProfessores.existeProfessor(username)) {
            repositorioProfessores.removerProfessor(username);
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    // Métodos para adicionar, remover ou alterar informações de cursos
    /**
     * Adiciona um novo curso ao sistema.
     *
     * @param repositorioCursos Repositório de cursos no qual o curso será
     * adicionado.
     * @param curso Curso a ser adicionado.
     */
    public void adicionarCurso(RepositorioCursos repositorioCursos, Curso curso) {
        repositorioCursos.inserirCurso(curso.getDesignacao(), curso);
        System.out.println("Curso adicionado com sucesso.");
    }

    /**
     * Remove um curso do sistema.
     *
     * @param repositorioCursos Repositório de cursos no qual o curso será
     * removido.
     * @param designacao Designação do curso a ser removido.
     */
    public void removerCurso(RepositorioCursos repositorioCursos, String designacao) {
        if (repositorioCursos.existeCurso(designacao)) {
            repositorioCursos.removerCurso(designacao);
            System.out.println("Curso removido com sucesso.");
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    // Métodos para adicionar, remover ou alterar informações de UCs
    /**
     * Adiciona uma nova Unidade Curricular (UC) ao sistema.
     *
     * @param repositorioUC Repositório de UCs no qual a UC será adicionada.
     * @param uc UC a ser adicionada.
     */
    public void adicionarUC(RepositorioUC repositorioUC, UnidadeCurricular uc) {
        repositorioUC.inserirUC(uc.getDesignacao(), uc);
        System.out.println("UC adicionada com sucesso.");
    }

    /**
     * Remove uma Unidade Curricular (UC) do sistema.
     *
     * @param repositorioUC Repositório de UCs no qual a UC será removida.
     * @param designacao Designação da UC a ser removida.
     */
    public void removerUC(RepositorioUC repositorioUC, String designacao) {
        if (repositorioUC.existeUC(designacao)) {
            repositorioUC.removerUC(designacao);
            System.out.println("UC removida com sucesso.");
        } else {
            System.out.println("UC não encontrada.");
        }
    }

    // Métodos para listar informações
    /**
     * Lista todos os cursos no sistema.
     *
     * @param repositorioCursos Repositório de cursos a ser listado.
     */
    public void listarCursos(RepositorioCursos repositorioCursos) {
        Map<String, Curso> cursos = repositorioCursos.getListaCursos();
        for (Curso curso : cursos.values()) {
            System.out.println(curso);
        }
    }

    /**
     * Lista todas as UCs no sistema.
     *
     * @param repositorioUC Repositório de UCs a ser listado.
     */
    public void listarUCs(RepositorioUC repositorioUC) {
        Map<String, UnidadeCurricular> ucs = repositorioUC.getListaUCs();
        for (UnidadeCurricular uc : ucs.values()) {
            System.out.println(uc);
        }
    }

    /**
     * Lista todos os alunos no sistema.
     *
     * @param repositorioAlunos Repositório de alunos a ser listado.
     */
    public void listarAlunos(RepositorioAlunos repositorioAlunos) {
        Map<String, Aluno> alunos = repositorioAlunos.getListaAlunos();
        for (Aluno aluno : alunos.values()) {
            System.out.println(aluno);
        }
    }

    /**
     * Lista todos os professores no sistema.
     *
     * @param repositorioProfessores Repositório de professores a ser listado.
     */
    public void listarProfessores(RepositorioProfessores repositorioProfessores) {
        Map<String, Professor> professores = repositorioProfessores.getListaProfessores();
        for (Professor professor : professores.values()) {
            System.out.println(professor);
        }
    }

}
