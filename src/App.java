import java.util.Scanner;

import model.Product;
import repository.ProductRrepository;
import service.CollectProductDataFromTerminal;
import service.ITerminal;
import service.ProductService;
import service.TerminalService;

public class App {
  public static void main(String[] args) throws Exception {
    var scanner = new Scanner(System.in);
    var terminalService = new TerminalService(scanner);
    var ProductRepository = new ProductRrepository();
    var productService = new ProductService(ProductRepository);
    var collectProductDataFromTerminal = new CollectProductDataFromTerminal(terminalService);
    var app = new App(terminalService, productService, collectProductDataFromTerminal);

    app.run();
  }

  private final String menu = """
      1- Cadastrar um produto.
      2 - LIstar produtos.
      3 - Buscar por ID.

      0- Sair
      """;

  private ITerminal terminalService;
  private ProductService productService;
  private CollectProductDataFromTerminal collectProductDataFromTerminal;

  public App(
      ITerminal terminalService,
      ProductService productService,
      CollectProductDataFromTerminal collectProductDataFromTerminal) {
    this.terminalService = terminalService;
    this.productService = productService;
    this.collectProductDataFromTerminal = collectProductDataFromTerminal;

  }

  public void run() {
    var option = 1;

    while (option > 0) {
      terminalService.showMesseage(menu);
      option = terminalService.readLineAsInt();

      switch (option) {

        case 1 -> registerProduct();
        case 2 -> showProducts();
        case 3 -> showProductsById();
      }

    }

  }

  private void registerProduct() {
    var collectProduct = collectProductDataFromTerminal.collect();
    var product = productService.insert(collectProduct);
    System.out.println(" Registro salvo: %s".formatted(product));
  }

  private void showProducts() {
    var producst = productService.getAll();

    terminalService.showMesseage("=== INICIO:LISTA DE PRODUTOS ===");

    producst.stream().forEach(p -> {
      terminalService.showMesseage("""
           ( cod %s) %s
          """.formatted(p.getId(), p.getName()));
    });
    terminalService.showMesseage("=== FIM:LISTA DE PRODUTOS ===");
  }

  private void showProductsById() {
    terminalService.showMesseage(" digite o ID da busca:");
    var id = terminalService.readLineAsInt();
    var product = productService.findById(id);
    if (product.isEmpty()) {
      terminalService.showMesseage(" === PRODUTO ===");
    } else {
      var prod = product.get();
      terminalService.showMesseage("""
          ID:%s
          NOME:%s
          PREÇO:%s
          ESTOQUE:%s
          """.formatted(
          prod.getId(),
          prod.getName(),
          ((float) prod.getValue() / 100.0f),
          prod.getStock()));
      terminalService.showMesseage("=== ===");
    }

  }
}
