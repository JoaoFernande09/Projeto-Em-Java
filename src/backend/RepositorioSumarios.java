package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que representa um repositório de sumários no sistema.
 * Um RepositorioSumarios contém um mapa de sumários, onde a chave é o título do sumário
 * e o valor é a instância do sumário correspondente.
 */
public class RepositorioSumarios implements Serializable {

    private Map<String, Sumario> listaSumarios;

    /**
     * Construtor que inicializa um RepositorioSumarios com um mapa vazio.
     */
    public RepositorioSumarios() {
        this.listaSumarios = new HashMap<>();
    }

    /**
     * Insere um sumário no repositório.
     *
     * @param sumario Instância do sumário a ser inserida.
     */
    public void inserirSumario(Sumario sumario) {
        listaSumarios.put(sumario.getTitulo(), sumario);
    }

    /**
     * Insere um sumário no repositório.
     *
     * @param titulo
     */
    public boolean existeSumario(String titulo) {
        return listaSumarios.containsKey(titulo);
    }

    /**
     * Obtém uma lista de sumários com base em uma Unidade Curricular (UC) e tipo de aula específicos.
     *
     * @param uc       Unidade Curricular (UC) para filtrar os sumários.
     * @param tipoAula Tipo de aula para filtrar os sumários.
     * @return Lista de sumários encontrados para a UC e tipo de aula especificados.
     */
    public List<Sumario> getSumariosPorUCeTipoAula(UnidadeCurricular uc, String tipoAula) {
        List<Sumario> sumariosEncontrados = new ArrayList<>();

        for (Sumario sumario : listaSumarios.values()) {
            if (sumario.getUnidadeCurricular() != null && sumario.getUnidadeCurricular().equals(uc) && sumario.getTipo().equals(tipoAula)) {
                sumariosEncontrados.add(sumario);
            }
        }

        return sumariosEncontrados;
    }

}
