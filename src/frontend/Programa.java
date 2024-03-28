package frontend;

import backend.Administrador;
import backend.Aluno;
import backend.Curso;
import backend.Professor;
import backend.Sistema;
import backend.Sumario;
import backend.UnidadeCurricular;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A classe Programa representa a interface do sistema de gestão académica.
 */
public class Programa {

    private final Consola consola = new Consola();
    private final Sistema sistema = new Sistema();

    /**
     * Método principal que inicia a execução do programa.
     *
     * @param args Argumentos da linha de comando (não utilizados).
     * @throws Exception Se ocorrer uma exceção durante a execução.
     */
    public static void main(String[] args) throws Exception {
        Programa programa = new Programa();

        int opcao;
        String[] opcoes = {
            "Criar Administrador ",
            "Menu Administrador",
            "Menu Professor",
            "Sair",};
        do {
            opcao = programa.consola.lerInteiro(opcoes);
            switch (opcao) {
                case 1:
                    programa.criarAdministrador();
                    break;
                case 2:
                    //programa.carregarFicheiroObjectos();
                    programa.menuAdministrador();
                    break;
                case 3:
                    programa.autenticarProfessor();
                    break;
                case 4:
                    programa.guardarFicheiroObjectos();
                    break;

            }

        } while (opcao != opcoes.length);

    }

    /**
     * Autentica um professor no sistema.
     */
    private void autenticarProfessor() {
        System.out.println("==================== Autenticação de Professor ====================");

        boolean autenticado = false;
        Professor professorAutenticado = null;

        while (!autenticado) {
            String username = consola.lerString("Username: ");
            String password = consola.lerString("Password: ");

            professorAutenticado = sistema.professores.autenticarProfessor(username, password);

            if (professorAutenticado != null) {
                System.out.println("Autenticação bem-sucedida.");
                menuProfessor(professorAutenticado);
                autenticado = true; // Terminar o loop após a autenticação bem-sucedida
            } else {
                System.out.println("Credenciais inválidas. Tente novamente.");
            }
        }
    }

    /**
     * Apresenta o menu principal para um professor autenticado.
     *
     * @param professor O professor autenticado.
     */
    public void menuProfessor(Professor professor) {
        int opcao;

        String[] opcoes = {
            "Criar Sumário",
            "Consultar Lista de Sumários por UC e por Tipo de Aula",
            "Consultar Serviço Docente",
            "Menu Regente",
            "Menu Diretor de Curso",
            "Sair"
        };

        do {
            opcao = consola.lerInteiro(opcoes);

            switch (opcao) {
                case 1:
                    criarSumario(professor);
                    break;
                case 2:
                    consultarListaSumarios();
                    break;
                case 3:
                    consultarServicoDocente(professor);
                    break;
                case 4:
                    menuRegente(professor);
                    break;
                case 5:
                    menuDiretorCurso(professor);
                    break;

            }
        } while (opcao != opcoes.length);
    }

    // Método criarSumario 
    /**
     * Cria um sumário para uma aula ministrada pelo professor.
     *
     * @param professor O professor que cria o sumário.
     */
    private void criarSumario(Professor professor) {
        System.out.println("==================== Criar Sumário ====================");

        // Pedir informações necessárias para criar um sumário
        String designacaoUC = consola.lerString("Designação da UC: ");
        String titulo = consola.lerString("Título do Sumário: ");
        String tipo = consola.lerString("Tipo de Aula(tp, pl, t): ");
        String sumarioTexto = consola.lerString("Texto do Sumário: ");

        // Obter a lista de alunos da UC
        UnidadeCurricular uc = sistema.ucs.obterUC(designacaoUC);
        List<Aluno> alunosUC = uc.getAlunos();

        // Pedir informações sobre os alunos presentes
        List<Aluno> alunosPresentes = new ArrayList<>();
        for (Aluno aluno : alunosUC) {
            String presenca = consola.lerString("O aluno " + aluno.getNome() + " estava presente? (S/N): ");
            if (presenca.equalsIgnoreCase("S")) {
                alunosPresentes.add(aluno);
            }
        }

        // Chamar o método criarSumario passando a lista de alunos presentes
        professor.criarSumario(designacaoUC, titulo, tipo, sumarioTexto, sistema, alunosPresentes);
    }

    /**
     * Consulta a lista de sumários por designação de UC e tipo de aula.
     */
    private void consultarListaSumarios() {
        System.out.println("==================== Consultar Lista de Sumários ====================");

        // Pedir informações necessárias para a consulta
        String designacaoUC = consola.lerString("Designação da UC: ");
        String tipoAula = consola.lerString("Tipo de Aula (tp, pl, t): ");

        // Verificar se a UC existe no sistema
        UnidadeCurricular uc = sistema.ucs.obterUC(designacaoUC);
        if (uc != null) {
            // Obter a lista de sumários por UC e tipo de aula
            List<Sumario> sumarios = sistema.sumarios.getSumariosPorUCeTipoAula(uc, tipoAula);

            // Exibir os sumários encontrados
            if (!sumarios.isEmpty()) {
                System.out.println("=================== Lista de Sumários ===================");
                for (Sumario sumario : sumarios) {
                    // Exibir informações relevantes do sumário
                    System.out.println("Título: " + sumario.getTitulo());
                    System.out.println("Tipo de Aula: " + sumario.getTipo());
                    System.out.println("Texto do Sumário: " + sumario.getSumario());
                    System.out.println("---------------------------------");
                }
            } else {
                System.out.println("Nenhum sumário encontrado para a UC e tipo de aula especificados.");
            }
        } else {
            System.out.println("UC não encontrada.");
        }
    }

    //Administrador
    // Método para criar administrador
    /**
     * Cria um novo administrador e autentica-o no sistema.
     */
    private void criarAdministrador() {
        System.out.println("==================== Criar Administrador ====================");
        String username = consola.lerString("Username do Administrador: ");
        String password = consola.lerString("Password do Administrador: ");

        // Crie uma instância de Administrador com as informações fornecidas
        Administrador administrador = new Administrador(username, password);

        // Autenticar o administrador no sistema
        sistema.autenticarUtilizador(username, password);

        // Atribuir a instância de Administrador ao atributo adequado do seu sistema
        sistema.administrador = administrador;

        System.out.println("Administrador criado com sucesso!");
    }

    /**
     * Apresenta o menu principal para um administrador autenticado.
     */
    public void menuAdministrador() {

        int opcao;

        // Autenticar o administrador
        boolean autenticado = false;
        while (!autenticado) {
            String username = consola.lerString("Username:");
            String password = consola.lerString("Password: ");
            autenticado = sistema.administrador.autenticar(username, password);

            if (!autenticado) {
                System.out.println("Credenciais inválidas. Tente novamente.");

            }
        }

        String[] opcoes = {
            "Adicionar Professor",
            "Remover Professor",
            "Adicionar UC",
            "Adicionar Curso",
            "Alterar Informação do Curso",
            "Alterar Informação da UC",
            "Listar Professores",
            "Listar UCs",
            "Listar Cursos",
            "Adicionar Regente a UC",
            "Adicionar Diretor de Curso",
            "Sair"

        };

        do {
            opcao = consola.lerInteiro(opcoes);

            switch (opcao) {
                case 1:
                    adicionarProfessor();
                    break;
                case 2:
                    removerProfessor();
                    break;
                case 3:
                    adicionarUc();
                    break;
                case 4:
                    adicionarCurso();
                    break;
                case 5:
                    alterarInformacaoCurso();
                    break;
                case 6:
                    alterarInformacaoUC();
                    break;
                case 7:
                    listarProfessores();
                    break;
                case 8:
                    listarUcs();
                    break;
                case 9:
                    listarCursos();
                    break;
                case 10:
                    atribuirRegenciaUC();
                    break;
                case 11:
                    atribuirDiretorCurso();
                    break;
            }
        } while (opcao != opcoes.length);
    }

    /**
     * Adiciona um novo professor ao sistema.
     */
    private void adicionarProfessor() {
        System.out.println("==================== Adicionar Professor ====================");

        String username = consola.lerString("Username do Professor: ");
        String password = consola.lerString("Password do Professor: ");
        String nome = consola.lerString("Nome do Professor: ");
        String numeroMecanografico = consola.lerString("Número Mecanográfico do Professor: ");

        String startDateString = consola.lerString("Data de Início de Funções do Professor (YYYY-MM-DD): ");
        LocalDate inicioFuncoes = LocalDate.parse(startDateString);

        // Obter ou criar uma instância de UnidadeCurricular
        String designacaoUC = consola.lerString("Designação da UC associada ao Professor: ");
        UnidadeCurricular uc = sistema.ucs.obterUC(designacaoUC);

        if (uc == null) {
            System.out.println("A UC com a designação '" + designacaoUC + "' não existe. Não é possível adicionar o professor.");
            return;
        }

        // Obter ou criar uma instância de Curso
        String designacaoCurso = consola.lerString("Designação do Curso associado ao Professor: ");

        if (!sistema.cursos.existeCurso(designacaoCurso)) {
            // O curso não existe, imprima uma mensagem e retorne sem adicionar o professor
            System.out.println("O Curso com a designação '" + designacaoCurso + "' não existe. Não é possível adicionar o professor.");
            return;
        }

        // Curso curso = sistema.cursos.getListaCursos().get(designacaoCurso);
        // Agora, criar uma instância de Professor com as informações fornecidas
        Professor professor = new Professor(username, password, nome, numeroMecanografico, inicioFuncoes, sistema.ucs);

        // Adicione o professor ao RepositorioProfessores
        sistema.professores.inserirProfessor(username, professor);

        System.out.println("Professor adicionado com sucesso!");
    }

    /**
     * Lista todos os professores no sistema.
     */
    private void listarProfessores() {
        Map<String, Professor> listaProfessores = sistema.professores.getListaProfessores();

        System.out.println("==================== Lista de Professores ====================");
        if (listaProfessores.isEmpty()) {
            System.out.println("Nenhum professor inserido.");
        } else {
            for (Professor professor : listaProfessores.values()) {
                System.out.println("Nome: " + professor.getNome());
                System.out.println("Número Mecanográfico: " + professor.getNumeroMecanografico());
                System.out.println("---------------------------------");
            }
        }
    }

    /**
     * Remove um professor do sistema.
     */
    private void removerProfessor() {
        String username = consola.lerString("========================================\n Username do Professor a remover: ");
        sistema.removerProfessor(username);
    }

//aluno
    // Método adicionarAluno 
    /**
     * Adiciona um novo aluno ao sistema.
     */
    private void adicionarAluno() {
        System.out.println("==================== Adicionar Aluno: ====================");

        // Pedir informações necessárias para criar um aluno
        String username = consola.lerString("Username do Aluno: ");
        String password = consola.lerString("Password do Aluno: ");
        String nome = consola.lerString("Nome do Aluno: ");
        String numeroMecanografico = consola.lerString("Número Mecanográfico do Aluno: ");
        String designacaoCurso = consola.lerString("Designação do Curso do Aluno: ");

        // Verificar se o curso existe no sistema
        Curso curso = sistema.cursos.getListaCursos().get(designacaoCurso);

        if (curso != null) {
            // Criar um aluno
            Aluno aluno = new Aluno(username, password, nome, numeroMecanografico, curso);

            // Adicionar o aluno ao curso
            curso.adicionarAluno(numeroMecanografico, aluno);

            System.out.println("Aluno criado e adicionado ao curso com sucesso.");
        } else {
            System.out.println("Curso não encontrado. Não é possível adicionar o aluno.");
        }
    }

    // Método para remover aluno
    private void removerAluno() {
        String numeroMecanografico = consola.lerString("========================================\n Número Mecanográfico do Aluno a remover: ");

        if (sistema.alunos.existeAluno(numeroMecanografico)) {
            // Obtém o aluno do sistema
            Aluno aluno = sistema.alunos.getListaAlunos().get(numeroMecanografico);

            // Verifica se o aluno está associado a algum curso
            Curso cursoAssociado = aluno.getCurso();

            // Remove o aluno do sistema
            sistema.alunos.removerAluno(numeroMecanografico);

            if (cursoAssociado != null) {
                // Remove o aluno do curso
                cursoAssociado.removerAluno(numeroMecanografico);
            }

            System.out.println("Aluno removido com sucesso.");
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

// Método para alterar informação do curso
    private void alterarInformacaoCurso() {
        String designacao = consola.lerString("========================================\n Designação do Curso a alterar: ");
        if (sistema.cursos.existeCurso(designacao)) {
            String novaDesignacao = consola.lerString("Nova Designação do Curso: ");
            Curso curso = sistema.cursos.getListaCursos().get(designacao);
            curso.setDesignacao(novaDesignacao);
            System.out.println("Designação do curso alterada com sucesso.");
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

// Método para alterar informação da UC
    private void alterarInformacaoUC() {
        String designacao = consola.lerString("========================================\n Designação da UC a alterar: ");
        if (sistema.ucs.existeUC(designacao)) {
            String novaDesignacao = consola.lerString("Nova Designação da UC: ");
            UnidadeCurricular uc = sistema.ucs.getListaUCs().get(designacao);
            uc.setDesignacao(novaDesignacao);
            System.out.println("Designação da UC alterada com sucesso.");
        } else {
            System.out.println("UC não encontrada.");
        }
    }

    /**
     * Atribui a regência de uma UC a um professor.
     */
    private void atribuirRegenciaUC() {
        String designacaoUC = consola.lerString("========================================\n Designação da UC para atribuir regência: ");
        UnidadeCurricular uc = sistema.ucs.obterUC(designacaoUC);

        if (uc != null) {
            String usernameProfessor = consola.lerString(" Username do Professor para atribuir regência: ");
            Professor professor = sistema.professores.getProfessor(usernameProfessor);

            if (professor != null) {
                uc.atribuirRegencia(professor);
                professor.setRegenciaUC(uc);
            } else {
                System.out.println("Professor não encontrado.");
            }
        } else {
            System.out.println("UC não encontrada.");
        }
    }

    /**
     * Atribui o cargo de diretor de curso a um professor.
     */
    private void atribuirDiretorCurso() {
        String designacaoCurso = consola.lerString("Designação do Curso: ");
        String usernameDiretor = consola.lerString("Username do Diretor do Curso: ");

        // Verificar se o professor existe no sistema
        Professor diretorCurso = sistema.professores.getProfessor(usernameDiretor);

        if (diretorCurso != null) {
            // Atribuir o diretor ao curso
            sistema.cursos.atribuirDiretorCurso(designacaoCurso, diretorCurso);
            diretorCurso.setDiretorCurso(true);
        } else {
            System.out.println("Professor não encontrado. Não é possível atribuir o diretor de curso.");
        }

    }

    /**
     * Adiciona uma nova Unidade Curricular (UC) ao sistema.
     */
    private void adicionarUc() {

        System.out.println("==================== Inserir uma Unidade Curricular: ====================");

        String designacao = consola.lerString("Designação da UC: ");

        // Criar uma instância de UnidadeCurricular
        UnidadeCurricular uc = new UnidadeCurricular();
        uc.setDesignacao(designacao);

        // Adicionar a UC à lista no RepositorioUC
        sistema.getUcs().inserirUC(uc.getDesignacao(), uc);

        System.out.println("\nUnidade Curricular adicionada com sucesso.");
    }

    /**
     * Lista todas as Unidades Curriculares (UCs) no sistema.
     */
    private void listarUcs() {
        System.out.println("==================== Lista de Unidades Curriculares: ====================");

        for (UnidadeCurricular uc : sistema.getUcs().getListaUCs().values()) {
            String regente = (uc.getRegente() != null) ? uc.getRegente().getNome() : "Sem regente";
            System.out.println("\nDesignação: " + uc.getDesignacao() + ",\nRegente: " + regente);
        }
    }

    /**
     * Adiciona um novo curso ao sistema.
     */
    private void adicionarCurso() {

        System.out.println("==================== Adicionar Curso: ====================");

        // Obter informações do curso
        String designacaoCurso = consola.lerString("Designação do Curso:");

        // Criar uma instância de Curso
        Curso curso = new Curso();
        curso.setDesignacao(designacaoCurso);

        // Adicionar o curso ao repositório
        sistema.getCursos().inserirCurso(curso.getDesignacao(), curso);

        System.out.println("Curso criado com sucesso.");
    }

    /**
     * Lista todos os cursos no sistema.
     */
    private void listarCursos() {
        System.out.println("==================== Lista de Cursos:  ====================");

        for (Curso curso : sistema.getCursos().getListaCursos().values()) {
            System.out.println("Designação: " + curso.getDesignacao());
        }
    }

    /**
     * Apresenta o menu para um regente de curso autenticado.
     *
     * @param professor O professor regente.
     */
    public void menuRegente(Professor professor) {
        if (professor.isRegente()) {
            int opcao;

            String[] opcoes = {
                "Adicionar alunos ao curso.",
                "Remover alunos do curso",
                "Consulta assiduidade de determinado aluno.",
                "Sair"
            };

            do {
                opcao = consola.lerInteiro(opcoes);

                switch (opcao) {
                    case 1:
                        adicionarAluno();
                        break;
                    case 2:
                        removerAluno();
                        break;
                    case 3:
                        break;
                }
            } while (opcao != opcoes.length);
        } else {
            System.out.println("Acesso negado. O professor não é regente.");
        }
    }

    /**
     * Apresenta o menu para um diretor de curso autenticado.
     *
     * @param professor O professor diretor de curso.
     */
    public void menuDiretorCurso(Professor professor) {
        if (professor.isDiretorCurso()) {
            int opcao;

            String[] opcoes = {
                "Alterar designação do Curso.",
                "Listar número de alunos por curso.",
                "Listar número de professores por curso",
                "Sair"
            };

            do {
                opcao = consola.lerInteiro(opcoes);

                switch (opcao) {
                    case 1:
                        alterarInformacaoCurso();
                        break;
                    case 2:
                        listarAlunosPorCurso();
                        break;
                    case 3:
                        listarProfessoresPorCurso();
                        break;

                }
            } while (opcao != opcoes.length);
        } else {
            System.out.println("Acesso negado. O professor não é diretor de curso.");
        }
    }

    /**
     * Lista o número de alunos por curso.
     */
    private void listarAlunosPorCurso() {
        String designacaoCurso = consola.lerString("Designação do Curso: ");
        Curso curso = sistema.cursos.getListaCursos().get(designacaoCurso);

        if (curso != null) {
            System.out.println("\nNúmero de Alunos no Curso '" + designacaoCurso + "': " + curso.getNumeroAlunos());
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    /**
     * Lista o número de professores por curso.
     */
    private void listarProfessoresPorCurso() {
        String designacaoCurso = consola.lerString("Designação do Curso: ");
        Curso curso = sistema.cursos.getListaCursos().get(designacaoCurso);

        if (curso != null) {
            System.out.println("\nNúmero de Professores no Curso '" + designacaoCurso + "': " + curso.getNumeroProfessores());
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    /**
     * Consulta o serviço docente de um professor.
     *
     * @param professor O professor para o qual se consulta o serviço docente.
     */
    private void consultarServicoDocente(Professor professor) {
        List<UnidadeCurricular> ucsAssociadas = professor.getUcsAssociadas();

        if (ucsAssociadas.isEmpty()) {
            System.out.println("O professor não está associado a nenhuma Unidade Curricular.");
        } else {
            System.out.println("Unidades Curriculares associadas ao professor:");
            for (UnidadeCurricular uc : ucsAssociadas) {
                System.out.println("- " + uc.getDesignacao());
            }
        }
    }

    /**
     * Guarda o estado atual do sistema num ficheiro de objetos.
     *
     * @throws Exception Se ocorrer uma exceção durante a operação.
     */
    private void guardarFicheiroObjectos() throws Exception {
        String nomeFicheiro = consola.lerString("Indique o nome do ficheiro");
        sistema.guardarFicheiroObjetos(nomeFicheiro);
        consola.escrever("FICHEIRO GUARDADO");
    }

    /**
     * Carrega o estado do sistema a partir de um ficheiro de objetos.
     *
     * @throws Exception Se ocorrer uma exceção durante a operação.
     */
    private void carregarFicheiroObjectos() throws Exception {
        String nomeFicheiro = consola.lerString("Indique o nome do ficheiro");
        sistema.carregarEstado(nomeFicheiro);
        consola.escrever("FICHEIRO CARREGADO");
    }

}
