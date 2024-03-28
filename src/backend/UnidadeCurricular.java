package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma Unidade Curricular (UC) em um curso.
 *
 * Uma UC possui uma designação, um professor regente, uma equipe de professores
 * associada, um repositório de sumários e está associada a um curso.
 */
public class UnidadeCurricular implements Serializable {

    private String designacao;
    private Professor regente;
    private RepositorioProfessores equipa;
    private RepositorioSumarios sumarios;
    private Curso curso;

    /**
     * Cria uma instância de UnidadeCurricular.
     */
    public UnidadeCurricular() {
        this.equipa = new RepositorioProfessores();
    }

    /**
     * Obtém o curso associado à Unidade Curricular.
     *
     * @return O curso associado à Unidade Curricular.
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Define o curso associado à Unidade Curricular.
     *
     * @param curso O novo curso associado à Unidade Curricular.
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Obtém a designação da Unidade Curricular.
     *
     * @return A designação da Unidade Curricular.
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Define a designação da Unidade Curricular.
     *
     * @param designacao A nova designação da Unidade Curricular.
     */
    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    /**
     * Obtém o professor regente da Unidade Curricular.
     *
     * @return O professor regente da Unidade Curricular.
     */
    public Professor getRegente() {
        return regente;
    }

    /**
     * Define o professor regente da Unidade Curricular.
     *
     * @param regente O novo professor regente da Unidade Curricular.
     */
    public void setRegente(Professor regente) {
        this.regente = regente;
    }

    /**
     * Obtém o repositório de professores associado à Unidade Curricular.
     *
     * @return O repositório de professores associado à Unidade Curricular.
     */
    public RepositorioProfessores getEquipa() {
        return equipa;
    }

    /**
     * Define o repositório de professores associado à Unidade Curricular.
     *
     * @param equipa O novo repositório de professores associado à Unidade
     * Curricular.
     */
    public void setEquipa(RepositorioProfessores equipa) {
        this.equipa = equipa;
    }

    /**
     * Obtém o repositório de sumários associado à Unidade Curricular.
     *
     * @return O repositório de sumários associado à Unidade Curricular.
     */
    public RepositorioSumarios getSumarios() {
        return sumarios;
    }

    /**
     * Define o repositório de sumários associado à Unidade Curricular.
     *
     * @param sumarios O novo repositório de sumários associado à Unidade
     * Curricular.
     */
    public void setSumarios(RepositorioSumarios sumarios) {
        this.sumarios = sumarios;
    }

    /**
     * Adiciona um professor à equipe da Unidade Curricular.
     *
     * @param admin O administrador que realiza a operação.
     * @param professor O professor a ser adicionado à equipe.
     */
    public void adicionarProfessor(Administrador admin, Professor professor) {
        if (admin != null) {
            equipa.inserirProfessor(professor.getNome(), professor);
        } else {
            // Adicione um tratamento para a falta de permissão (lançar uma exceção, imprimir uma mensagem, etc.)
            System.out.println("Permissão negada. Apenas administradores podem adicionar professores.");
        }
    }

    /**
     * Remove um professor da equipe da Unidade Curricular.
     *
     * @param admin O administrador que realiza a operação.
     * @param username O username do professor a ser removido.
     */
    public void removerProfessor(Administrador admin, String username) {
        if (admin != null) {
            equipa.removerProfessor(username);
        } else {
            // Adicione um tratamento para a falta de permissão
            System.out.println("Permissão negada. Apenas administradores podem remover professores.");
        }
    }

    /**
     * Lista os professores associados à Unidade Curricular.
     *
     * @param admin O administrador que realiza a operação.
     * @return Uma lista de professores associados à Unidade Curricular.
     */
    public List<Professor> listarProfessores(Administrador admin) {
        if (admin != null) {
            return new ArrayList<>(equipa.getListaProfessores().values());
        } else {
            System.out.println("Permissão negada. Apenas administradores podem listar professores.");
            return new ArrayList<>();
        }
    }

    /**
     * Adiciona um sumário à Unidade Curricular.
     *
     * @param sumario O sumário a ser adicionado.
     */
    public void adicionarSumario(Sumario sumario) {
        sumarios.inserirSumario(sumario);
    }

    /**
     * Verifica se um sumário com um determinado código existe na Unidade
     * Curricular.
     *
     * @param codigo O código do sumário a ser verificado.
     * @return true se o sumário existir, false caso contrário.
     */
    public boolean existeSumario(String codigo) {
        return sumarios.existeSumario(codigo);
    }

    /**
     * Atribui a regência da Unidade Curricular a um professor.
     *
     * @param professor O professor que será o regente.
     */
    public void atribuirRegencia(Professor professor) {
        if (professor != null) {
            this.regente = professor;
            System.out.println("Regência atribuída ao professor " + professor.getNome() + " com sucesso.");
        } else {
            System.out.println("Professor inválido. A regência não foi atribuída.");
        }
    }

    /**
     * Obtém a lista de alunos associados à Unidade Curricular.
     *
     * @return Uma lista de alunos associados à Unidade Curricular.
     */
    public List<Aluno> getAlunos() {
        if (curso != null) {
            return (List<Aluno>) curso.getAlunos().getListaAlunos().values();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Obtém a lista de professores associados à Unidade Curricular.
     *
     * @return Uma lista de professores associados à Unidade Curricular.
     */
    public List<Professor> getProfessores() {
        return new ArrayList<>(equipa.getListaProfessores().values());
    }

}
