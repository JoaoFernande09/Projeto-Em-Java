package backend;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Professor no sistema.
 * Um Professor possui um nome, um número mecanográfico, uma data de início de funções,
 * um repositório de UCs de serviço docente, uma UC de regência (opcional), e informações
 * sobre se é diretor de curso.
 */
public class Professor extends Utilizador implements Serializable {

    private String nome;
    private String numeroMecanografico;
    private LocalDate inicioFuncoes;
    private RepositorioUC servicoDocente;
    private UnidadeCurricular regenciaUC;
    private boolean diretorCurso;

     /**
     * Construtor que inicializa um Professor com as informações fornecidas.
     *
     * @param username        Nome de utilizador do Professor.
     * @param password        Palavra-passe do Professor.
     * @param nome            Nome do Professor.
     * @param numeroMecanografico Número mecanográfico único do Professor.
     * @param inicioFuncoes   Data de início de funções do Professor.
     * @param servicoDocente  Repositório de UCs de serviço docente associado ao Professor.
     */
    public Professor(String username, String password, String nome, String numeroMecanografico, LocalDate inicioFuncoes, RepositorioUC servicoDocente) {
        super(username, password);
        this.nome = nome;
        this.numeroMecanografico = numeroMecanografico;
        this.inicioFuncoes = inicioFuncoes;
        this.servicoDocente = servicoDocente;
    }

    /**
     * Obtém o nome do Professor.
     *
     * @return O nome do Professor.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do Professor.
     *
     * @param nome Novo nome do Professor.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o número mecanográfico único do Professor.
     *
     * @return O número mecanográfico do Professor.
     */
    public String getNumeroMecanografico() {
        return numeroMecanografico;
    }

    /**
     * Define o número mecanográfico único do Professor.
     *
     * @param numeroMecanografico Novo número mecanográfico do Professor.
     */
    public void setNumeroMecanografico(String numeroMecanografico) {
        this.numeroMecanografico = numeroMecanografico;
    }

    /**
     * Obtém a data de início de funções do Professor.
     *
     * @return A data de início de funções do Professor.
     */
    public LocalDate getInicioFuncoes() {
        return inicioFuncoes;
    }

    /**
     * Define a data de início de funções do Professor.
     *
     * @param inicioFuncoes Nova data de início de funções do Professor.
     */
    public void setInicioFuncoes(LocalDate inicioFuncoes) {
        this.inicioFuncoes = inicioFuncoes;
    }

    /**
     * Obtém o repositório de UCs de serviço docente associado ao Professor.
     *
     * @return O repositório de UCs de serviço docente do Professor.
     */
    public RepositorioUC getServicoDocente() {
        return servicoDocente;
    }

    /**
     * Define o repositório de UCs de serviço docente associado ao Professor.
     *
     * @param servicoDocente Novo repositório de UCs de serviço docente do Professor.
     */
    public void setServicoDocente(RepositorioUC servicoDocente) {
        this.servicoDocente = servicoDocente;
    }

    // Método criarSumario 
    /**
     * Cria um sumário para uma UC especificada, com título, tipo, texto e alunos presentes.
     *
     * @param designacaoUC    Designação da UC para a qual criar o sumário.
     * @param titulo          Título do sumário.
     * @param tipo            Tipo do sumário.
     * @param sumarioTexto    Texto do sumário.
     * @param sistema         Instância do sistema.
     * @param alunosPresentes Lista de alunos presentes no momento.
     */
    
    public void criarSumario(String designacaoUC, String titulo, String tipo, String sumarioTexto, Sistema sistema, List<Aluno> alunosPresentes) {
        UnidadeCurricular uc = this.servicoDocente.obterUC(designacaoUC);

        if (uc != null) {
            Sumario novoSumario = new Sumario();
            novoSumario.setTitulo(titulo);
            novoSumario.setTipo(tipo);
            novoSumario.setSumario(sumarioTexto);
            novoSumario.setDataHora(LocalDateTime.now());
            novoSumario.setPresencas(new RepositorioAlunos());
            novoSumario.setUnidadeCurricular(uc);

            sistema.sumarios.inserirSumario(novoSumario);

            for (Aluno aluno : alunosPresentes) {
                aluno.adicionarPresenca(novoSumario);
            }

            System.out.println("Sumário criado com sucesso.");
        } else {
            System.out.println("UC não encontrada. Não é possível criar o sumário.");
        }
    }

    /**
     * Verifica se o Professor é regente de alguma UC.
     *
     * @return Verdadeiro se o Professor for regente, falso caso contrário.
     */
    public boolean isRegente() {
        return regenciaUC != null;
    }

    /**
     * Verifica se o Professor é diretor de curso.
     *
     * @return Verdadeiro se o Professor for diretor de curso, falso caso contrário.
     */
    public boolean isDiretorCurso() {
        return diretorCurso;
    }
    
    /**
     * Define se o Professor é diretor de curso.
     *
     * @param diretorCurso Novo valor para indicar se o Professor é diretor de curso.
     */
    public void setDiretorCurso(boolean diretorCurso) {
        this.diretorCurso = diretorCurso;
    }

    /**
     * Define a UC que o Professor regente.
     *
     * @param regenciaUC UC a ser atribuída como regência ao Professor.
     */
    public void setRegenciaUC(UnidadeCurricular regenciaUC) {
        this.regenciaUC = regenciaUC;
    }

    /**
     * Obtém a lista de UCs associadas ao Professor.
     *
     * @return Lista de UCs associadas ao Professor.
     */
    public List<UnidadeCurricular> getUcsAssociadas() {
        List<UnidadeCurricular> ucsAssociadas = new ArrayList<>();

        for (UnidadeCurricular uc : servicoDocente.getListaUCs().values()) {
            if (uc.getEquipa().existeProfessor(this.getNumeroMecanografico())) {
                ucsAssociadas.add(uc);
            }
        }

        return ucsAssociadas;
    }

}
