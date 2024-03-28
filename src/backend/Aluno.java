package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Aluno no sistema. Um Aluno é um Utilizador com
 * informações adicionais, como nome, número mecanográfico, curso e presenças.
 */
public class Aluno extends Utilizador implements Serializable {

    private String nome;
    private String numeroMecanografico;
    private Curso curso;
    private List<Sumario> presencas;

    /**
     * Construtor para criar uma instância de Aluno com informações específicas.
     *
     * @param username Nome de usuário do Aluno.
     * @param password Senha do Aluno.
     * @param nome Nome completo do Aluno.
     * @param numeroMecanografico Número mecanográfico único do Aluno.
     * @param curso Curso ao qual o Aluno está associado.
     */
    public Aluno(String username, String password, String nome, String numeroMecanografico, Curso curso) {
        super(username, password);
        this.nome = nome;
        this.numeroMecanografico = numeroMecanografico;
        this.curso = curso;
    }

    // Métodos getter e setter para nome, numeroMecanografico e curso...
    /**
     * Define o nome do Aluno.
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o número mecanográfico do Aluno.
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o número mecanográfico do Aluno.
     *
     * @return O número mecanográfico do Aluno.
     */
    public String getNumeroMecanografico() {
        return numeroMecanografico;
    }

    /**
     * Define o número mecanográfico do Aluno.
     *
     * @param numeroMecanografico Novo número mecanográfico do Aluno.
     */
    public void setNumeroMecanografico(String numeroMecanografico) {
        this.numeroMecanografico = numeroMecanografico;
    }

    /**
     * Obtém o curso ao qual o Aluno está associado.
     *
     * @return O curso do Aluno.
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * Define o curso ao qual o Aluno está associado.
     *
     * @param curso Novo curso do Aluno.
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    /**
     * Obtém a lista de presenças do Aluno.
     *
     * @return A lista de presenças do Aluno.
     */
    public List<Sumario> getPresencas() {
        return presencas;
    }

    /**
     * Define a lista de presenças do Aluno.
     *
     * @param presencas Nova lista de presenças do Aluno.
     */
    public void setPresencas(List<Sumario> presencas) {
        this.presencas = presencas;
    }

    /**
     * Adiciona uma nova presença à lista do Aluno.
     *
     * @param sumario Sumário representando a presença do Aluno.
     */
    public void adicionarPresenca(Sumario sumario) {
        if (presencas == null) {
            presencas = new ArrayList<>();
        }
        presencas.add(sumario);
    }

}
