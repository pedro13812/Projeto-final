package service;


/*
 * Interface to read user-provide data.
 */

public interface ITerminal {
    /*
     * To write message for the user
     * @param menssage
     */
    void showMesseage(String message);
    /*
     * to read input message by user.
     * @return
     */

    String readLine();

    /*
     * to read input number by user.
     * @rutern
     */

    int readLineAsInt();

    /*
     * to read input double number by user.
     * @retunr
     */

    double readLineAsDouble();
}
