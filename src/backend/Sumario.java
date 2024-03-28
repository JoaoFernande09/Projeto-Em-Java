package backend;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa um sumário de aula.
 *
 * Um sumário contém informações sobre o título, tipo, texto do sumário, data e
 * hora em que foi criado, lista de presenças dos alunos e a unidade curricular
 * associada.
 */
public class Sumario implements Serializable {

    private String titulo;
    private String tipo; //tp,pl,t
    private String sumario;
    private LocalDateTime dataHora;
    private RepositorioAlunos presencas;
    private UnidadeCurricular unidadeCurricular;

    /**
     * Obtém o título do sumário.
     * 
     * @return O título do sumário.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * Define o título do sumário.
     * 
     * @param titulo O novo título do sumário.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtém o tipo do sumário (tp, pl, t).
     * 
     * @return O tipo do sumário.
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Define o tipo do sumário (tp, pl, t).
     * 
     * @param tipo O novo tipo do sumário.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o texto do sumário.
     * 
     * @return O texto do sumário.
     */
    public String getSumario() {
        return this.sumario;
    }

    /**
     * Define o texto do sumário.
     * 
     * @param sumario O novo texto do sumário.
     */
    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

     /**
     * Obtém a data e hora em que o sumário foi criado.
     * 
     * @return A data e hora do sumário.
     */
    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    /**
     * Define a data e hora do sumário.
     * 
     * @param dataHora A nova data e hora do sumário.
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Obtém a lista de presenças dos alunos associada ao sumário.
     * 
     * @return A lista de presenças dos alunos.
     */
    public RepositorioAlunos getPresencas() {
        return this.presencas;
    }

    /**
     * Define a lista de presenças dos alunos associada ao sumário.
     * 
     * @param presencas A nova lista de presenças dos alunos.
     */
    public void setPresencas(RepositorioAlunos presencas) {
        this.presencas = presencas;
    }

    /**
     * Obtém a unidade curricular associada ao sumário.
     * 
     * @return A unidade curricular associada ao sumário.
     */
    public UnidadeCurricular getUnidadeCurricular() {
        return this.unidadeCurricular;
    }

    /**
     * Define a unidade curricular associada ao sumário.
     * 
     * @param unidadeCurricular A nova unidade curricular associada ao sumário.
     */
    public void setUnidadeCurricular(UnidadeCurricular unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

}
