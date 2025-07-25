package service;

import model.Product;

/*
 * To interat with terminal to ask  information to mount product data.
 */

public class CollectProductDataFromTerminal {
    private ITerminal terminalService;

    public CollectProductDataFromTerminal(ITerminal terminalService) {

        this.terminalService = terminalService;
    }

    /*
     * to to initialize data collector
     * @return product with user-proived data
     */

    public Product collect() {
        terminalService.showMesseage("Informe o nome do produto");
        var name = terminalService.readLine();
        terminalService.showMesseage("Informe o preço do produto");
        var value = terminalService.readLineAsDouble();
        terminalService.showMesseage("Informe o estoque do produto");
        var stock = terminalService.readLineAsInt();
        /* multipy to prevent float number */
        value *= 100;

        var intValue = (int) value;
        var product = new Product();
        product.setName(name);
        product.setValue(intValue);
        product.setStock(stock);

        return product;

    }
}
