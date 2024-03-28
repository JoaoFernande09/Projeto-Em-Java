package backend;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Classe que representa um repositório de Unidades Curriculares (UCs). Mantém
 * uma coleção de UCs indexadas por sua designação.
 */
public class RepositorioUC implements Serializable {

    private TreeMap<String, UnidadeCurricular> listaUCs;

    /**
     * Construtor padrão que inicializa um novo RepositorioUC.
     */
    public RepositorioUC() {
        this.listaUCs = new TreeMap<>();
    }

    /**
     * Obtém a lista de UCs no repositório.
     *
     * @return Um TreeMap contendo as UCs indexadas por designação.
     */
    public TreeMap<String, UnidadeCurricular> getListaUCs() {
        return listaUCs;
    }

    /**
     * Define a lista de UCs no repositório.
     *
     * @param listaUCs O TreeMap contendo as UCs indexadas por designação.
     */
    public void setListaUCs(TreeMap<String, UnidadeCurricular> listaUCs) {
        this.listaUCs = listaUCs;
    }

    /**
     * Obtém uma UC específica com base na sua designação.
     *
     * @param designacao A designação da UC a ser obtida.
     * @return A UC correspondente à designação fornecida, ou null se não
     * existir.
     */
    public UnidadeCurricular obterUC(String designacao) {
        return listaUCs.get(designacao);
    }

    /**
     * Insere uma nova UC no repositório.
     *
     * @param designacao A designação da UC a ser inserida.
     * @param uc A UC a ser inserida no repositório.
     */
    public void inserirUC(String designacao, UnidadeCurricular uc) {
        listaUCs.put(designacao, uc);
    }

    /**
     * Remove uma UC do repositório com base na sua designação.
     *
     * @param designacao A designação da UC a ser removida.
     */
    public void removerUC(String designacao) {
        listaUCs.remove(designacao);
    }

    /**
     * Verifica se uma UC com uma determinada designação existe no repositório.
     *
     * @param designacao A designação da UC a ser verificada.
     * @return true se a UC existir, false caso contrário.
     */
    public boolean existeUC(String designacao) {
        return listaUCs.containsKey(designacao);
    }
}
