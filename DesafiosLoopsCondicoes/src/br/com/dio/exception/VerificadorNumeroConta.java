package br.com.dio.exception;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class VerificadorNumeroConta {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

// FAZER/OK: Inicialize um bloco try-catch para capturar exceções:
        try {

// FAZER/OK: Leia a entrada do usuário como uma string representando o número da conta:
            String numeroConta = scanner.nextLine();

// FAZER/OK: Chame o método verificarNumeroConta, passando o número da conta como argumento:
            verificarNumeroConta(numeroConta);

// Imprime que o número de conta é válido:
            System.out.println("Numero de conta valido.");

// FAZER/OK: Capture a exceção do tipo IllegalArgumentException, que pode ser lançada pelo método verificarNumeroConta:
        } catch (IllegalArgumentException e) {
// Aqui é impresso uma mensagem de erro, mostrando a mensagem associada à exceção:
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Declaração do método verificarNumeroConta, que verifica se o número de conta tem exatamente 8 dígitos:
    private static void verificarNumeroConta(String numeroConta) {

// FAZER/OK: Verifique se o número da conta tem exatamente 8 dígitos:
        if (numeroConta.length() != 8) {
// FAZER/OK: Implemente uma exceção do tipo IllegalArgumentException, caso o número de conta não tenha 8 dígitos:
            throw new IllegalArgumentException("Numero de conta invalido. Digite exatamente 8 digitos.");
        }
    }
}
