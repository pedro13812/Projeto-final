import java.util.Scanner;

import repository.ProductRepository;
import service.CollectProductDataFromTerminal;
import service.ITerminal;
import service.ProductService;
import service.TerminalService;

public class App implements Runnable {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        var terminalService = new TerminalService(scanner);
        var productRepository = new ProductRepository();
        var productService = new ProductService(productRepository);
        var collectProductDataFromTerminal = new CollectProductDataFromTerminal(terminalService);
        var app = new App(terminalService, productService, collectProductDataFromTerminal);

        new Thread(app).start();
    }

    private final String menu = """
            1 - Cadastrar um produto.
            2 - Listar produtos.
            3 - Buscar por ID.
            4 - Atualizar produto por ID.

            0 - Sair.
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

    @Override
    public void run() {
        var option = 1;

        while (option > 0) {
            try {
                terminalService.showMessage(menu);
                option = terminalService.readLineAsInt();

                switch (option) {
                    case 1 -> registerProduct();
                    case 2 -> showProducts();
                    case 3 -> showProductById();
                    case 4 -> updateProductById();
                }

            } catch (NumberFormatException e) {
                terminalService.showMessage("OPÇÃO INVÁLIDA DEVIDO Á ERRO: %s"
                        .formatted(e.getMessage()));
                terminalService.showMessage("DIGITE UMA OPÇÃO VÁLIDA", 2000);
            }
        }
    }

    private void registerProduct() {
        var collectedProduct = collectProductDataFromTerminal.collect();
        if (collectedProduct.isPresent()) {
            var product = productService.insert(collectedProduct.get());
            terminalService.showMessage("Registro salvo: %s".formatted(product), 2000);
        }
    }

    private void showProducts() {
        var products = productService.getAll();

        terminalService.showMessage("=== INICIO: LISTA DE PRODUTOS ===");
        products.stream().forEach(p -> {
            terminalService.showMessage("""
                    (cod %s) %s
                    """.formatted(p.getId(), p.getName()));
        });
        terminalService.showMessage("=== FIM: LISTA DE PRODUTOS ===", 2000);
    }

    private void showProductById() {
        try {
            terminalService.showMessage("DIGITE O ID DA BUSCA:");
            var id = terminalService.readLineAsInt();
            var product = productService.findById(id);
            if (product.isEmpty()) {
                terminalService.showMessage("NENHUM PRODUTO COM ESTE ID ENCONTRADO.");
            } else {
                var prod = product.get();
                terminalService.showMessage("=== PRODUTO ===");
                terminalService.showMessage("""
                        ID: %s
                        NOME: %s
                        PREÇO: %s
                        ESTOQUE: %s
                        """.formatted(
                        prod.getId(),
                        prod.getName(),
                        ((float) prod.getValue() / 100.0f),
                        prod.getStock()));
            }
        } catch (NumberFormatException ex) {
            terminalService.showMessage("OPERAÇÃO CANCELADA DEVIDO À ERRO: %s"
                    .formatted(ex.getMessage()));
        }
        terminalService.showMessage("=== ===", 2000);
    }

    private void updateProductById() {
        try {
            terminalService.showMessage("DIGITE O ID DA BUSCA:");
            var id = terminalService.readLineAsInt();
            var product = productService.findById(id);
            if (product.isEmpty()) {
                terminalService.showMessage("NENHUM PRODUTO COM ESTE ID ENCONTRADO.");
            } else {
                var prod = product.get();
                var collectedProduct = collectProductDataFromTerminal.collectByProduct(prod);
                if (collectedProduct.isPresent()) {
                    productService.update(collectedProduct.get());
                }
            }
        } catch (NumberFormatException ex) {
            terminalService.showMessage("OPERAÇÃO CANCELADA DEVIDO À ERRO: %s"
                    .formatted(ex.getMessage()));
        }
    }
}
