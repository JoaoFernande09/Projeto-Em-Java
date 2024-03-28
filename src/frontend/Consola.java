package frontend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe que fornece métodos para interação com o utilizador através da
 * consola.
 */
public class Consola {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Escreve uma mensagem na consola.
     *
     * @param mensagem A mensagem a ser escrita.
     */
    public void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    /**
     * Escreve uma mensagem de erro na consola.
     *
     * @param mensagem A mensagem de erro a ser escrita.
     */
    public void escreverErro(String mensagem) {
        System.err.println(mensagem);
    }

    /**
     * Lê uma string da consola.
     *
     * @param mensagem A mensagem a ser apresentada ao utilizador.
     * @return A string lida.
     */
    public String lerString(String mensagem) {
        escrever(mensagem);
        return scanner.nextLine();
    }

    /**
     * Lê um número inteiro da consola.
     *
     * @param mensagem A mensagem a ser apresentada ao utilizador.
     * @return O número inteiro lido.
     */
    public int lerInteiro(String mensagem) {
        Integer numero = null;
        String texto;

        do {
            escrever(mensagem);
            texto = scanner.nextLine();

            try {
                numero = Integer.valueOf(texto);
            } catch (NumberFormatException e) {
                escreverErro(texto + " Não é um número inteiro válido.");
            }

        } while (numero == null);

        return numero;
    }

    /**
     * Lê um número inteiro da consola com base nas opções fornecidas.
     *
     * @param opcoes As opções a serem apresentadas ao utilizador.
     * @return O número inteiro correspondente à opção escolhida.
     */
    public int lerInteiro(String[] opcoes) {
        Integer numero = null;
        String texto = "";

        do {
            escrever("\n==================== Selecione uma das seguintes opcões: ====================");
            for (int i = 0; i < opcoes.length; i++) {
                escrever((i + 1) + " - " + opcoes[i]);

            }

            try {
                texto = scanner.nextLine();
                numero = Integer.valueOf(texto);
            } catch (NumberFormatException e) {
                escreverErro(texto + " Não é uma opção válida!");
            }

            if (numero == null || numero <= 0 || numero > opcoes.length) {
                numero = null;
                escreverErro(texto + " Não é uma opção válida!");
            }

        } while (numero == null);

        return numero;
    }

    /**
     * Lê um número decimal da consola.
     *
     * @param mensagem A mensagem a ser apresentada ao utilizador.
     * @return O número decimal lido.
     */
    public double lerDecimal(String mensagem) {
        Double numero = null;
        String texto;

        do {
            escrever(mensagem);
            texto = scanner.nextLine();

            try {
                numero = Double.valueOf(texto);
            } catch (NumberFormatException e) {
                escreverErro(texto + " Não é um número decimal válido.");
            }
        } while (numero == null);

        return numero;
    }

    /**
     * Lê uma data da consola no formato "dd/mm/aaaa".
     *
     * @param mensagem A mensagem a ser apresentada ao utilizador.
     * @return A data lida.
     */
    public LocalDate lerData(String mensagem) {
        LocalDate data = null;
        String texto;

        do {
            escrever(mensagem + " (dd/mm/aaaa)");
            texto = scanner.nextLine();

            try {
                data = LocalDate.parse(texto, DateTimeFormatter.ofPattern("d/M/yyyy"));
            } catch (DateTimeParseException e) {
                escreverErro(texto + " Não é uma data no formato dd/mm/aaaa.");
            }

        } while (data == null);

        return data;
    }

    /**
     * Lê uma data e hora da consola no formato "dd/mm/aaaa hh:mm".
     *
     * @param mensagem A mensagem a ser apresentada ao utilizador.
     * @return A data e hora lidas.
     */
    public LocalDateTime lerDataHora(String mensagem) {
        LocalDateTime data = null;
        String texto;

        do {
            escrever(mensagem + " (dd/mm/aaaa hh:mm)");
            texto = scanner.nextLine();

            try {
                data = LocalDateTime.parse(texto,
                        DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
            } catch (DateTimeParseException e) {
                escreverErro(texto + " Não é uma data no formato dd/mm/aaaa hh:mm");
            }

        } while (data == null);

        return data;
    }
}
