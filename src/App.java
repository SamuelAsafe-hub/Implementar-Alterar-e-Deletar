import csv.ArquivoProdutoCSV;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Produto;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        ArquivoProdutoCSV arquivo = new ArquivoProdutoCSV();
        int op;
        
        do { 
            
            System.out.println("Escolha a Opção:");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produto");
            System.out.println("3 - Alterar Produto");
            System.out.println("4 - Deletar Produto");
            System.out.println("5 - Sair");
            System.out.print("Opção: ");
            op = teclado.nextInt();
            switch (op) {
                case 1:
                    Produto produto = new Produto();
                    System.out.println("Cadastrar Produto");
                    System.out.print("Id: ");
                    produto.setId(teclado.nextInt());
                    System.out.print("Nome: ");
                    teclado.nextLine();
                    produto.setNome(teclado.nextLine());
                    System.out.print("Preço: ");
                    produto.setPreco(teclado.nextDouble());
                    arquivo.inserir(produto);
                    break;
                case 2:
                    ArrayList<Produto> produtos = arquivo.listar();
                    System.out.println("====================");
                        System.out.println("Listagem de Produtos");
                        System.out.println("====================");
                    for (Produto prod : produtos) {
                        
                        System.out.println(prod.getId()+"    "+prod.getNome()+"    R$"+prod.getPreco());
                        System.out.println("---------------------");
                    }
                    teclado.nextLine();
                    teclado.nextLine();
                    break;
                case 3 :
                    System.out.println("====================");
                    System.out.println("  Alterar Produtos  ");
                    System.out.println("====================");

                    System.out.print("Digite o id do produto a alterar: ");
                    int idAp = teclado.nextInt();
                    teclado.nextLine(); // limpa o Enter que sobrou no buffer

                    System.out.print("Novo nome: ");
                    String novoNome = teclado.nextLine();

                    System.out.print("Novo preço: ");
                    double novoPreco = teclado.nextDouble();

            
                    if(arquivo.alterar(idAp, novoNome, novoPreco)){ 
                        System.out.println("Produto Alterado com sucesso");
                    }else{
                        System.out.println("Produto não encontrado ou erro ao salvar");
                    }
                        
                    break;
                case 4 :
                    System.out.println("====================");
                    System.out.println("  Deletar Produtos  ");
                    System.out.println("====================");

                    System.out.print("Informe ID do produto a ser deletado:");
                    int id = teclado.nextInt();
                    if(arquivo.deletar(id)){ 
                        System.out.println("Produto deletado com sucesso");
                    }else{
                        System.out.println("Produto não encontrado ou erro ao salvar");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            
        } while (op!=5);

        
    }
}
