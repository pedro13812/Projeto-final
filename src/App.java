import java.util.Scanner;

import model.Product;
import repository.ProductRrepository;
import service.CollectProductDataFromTerminal;
import service.ITerminal;
import service.TerminalService;

public class App {
  public static void main(String[] args) throws Exception {
    var scanner = new Scanner(System.in);
    var terminalService = new TerminalService(scanner);
    var productRrepository = new ProductRrepository();
    var collectProductDataFromTerminal = new CollectProductDataFromTerminal(terminalService);
    var app = new App(terminalService, productRrepository, collectProductDataFromTerminal);

    app.run();
  }

  private final String menu = """
      1- Cadastrar um produto.

      0- Sair
      """;

  private ITerminal terminalService;
  private ProductRrepository productRrepository;
  private CollectProductDataFromTerminal collectProductDataFromTerminal;

  public App(
      ITerminal terminalService,
      ProductRrepository productRrepository,
      CollectProductDataFromTerminal collectProductDataFromTerminal) {
    this.terminalService = terminalService;
    this.productRrepository = productRrepository;
    this.collectProductDataFromTerminal = collectProductDataFromTerminal;

  }

  public void run() {
    var option = 1;

    while (option > 0) {
      terminalService.showMesseage(menu);
      option = terminalService.readLineAsInt();

      switch (option) {

        case 1 -> registerProduct();

      }

    }

  }

  private void registerProduct() {
    var collectProduct = collectProductDataFromTerminal.collect();
    var product = productRrepository.save(collectProduct);

    System.out.println(" Registro salvo %s".formatted(product));

  }
}
