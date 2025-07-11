package service;

import java.util.Scanner;

public class TerminalService implements ITerminal {
    private final Scanner scanner;

    public TerminalService(Scanner scanner) {
        this.scanner = scanner;

    }

    @Override
    public void showMesseage(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public String readLine() {
        return scanner.nextLine().trim();
    }

    @Override
    public int readLineAsInt() throws NumberFormatException {
        var value = readLine();
        return Integer.parseInt(value);
    }

    @Override
    public double readLineAsDouble() throws NumberFormatException {
        var value = readLine().replace(",", ".");
        return Double.parseDouble(value);
    }

}
