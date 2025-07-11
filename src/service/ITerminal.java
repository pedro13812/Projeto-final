package service;

public interface ITerminal {
    void showMesseage(String message);

    String readLine();

    int readLineAsInt();

    double readLineAsDouble();
}
