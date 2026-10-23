package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelo.Produto;

public class ArquivoProdutoCSV {
    private String nomeArquivo = "produtos.csv";

    public void inserir(Produto produto){
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter escritor = new PrintWriter(arquivo);
            escritor.println(produto.getId()+","+produto.getNome()+","+produto.getPreco());
            escritor.close();

        
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao acessar arquivo "+e.getMessage());
        }
        
    }

    public ArrayList<Produto> listar(){
        ArrayList<Produto> produtos = new ArrayList<>();
        File arquivo = new File(nomeArquivo);
        //se não existe arquivo em disco é porque não há produtos cadastrados
        if(!arquivo.exists()){
            return produtos;
        }
        //try with resources - neste caso o leitor.close() fica implícito
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))){
            String linha = leitor.readLine();
            while (linha!=null) {
                String[] dados = linha.split(",");
                Produto produto = new Produto();
                produto.setId(Integer.parseInt(dados[0]));
                produto.setNome(dados[1]);
                produto.setPreco(Double.parseDouble(dados[2]));
                produtos.add(produto);
                linha = leitor.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao acessar arquivo "+e.getMessage());
       }

       return produtos;
    }

    public boolean deletar(int id) {
        ArrayList<Produto> produtos = listar();

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produtos.remove(produtos);
                return salvar(produtos);
            }    
        }        
        return false;
    }

    public boolean salvar(ArrayList<Produto> produtos) {
        try {
            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter escritor = new PrintWriter(arquivo);
            for (Produto produto : produtos) {
                escritor.println(produto.getId()+","+produto.getNome()+","+produto.getPreco());
            }
            escritor.close();
            return true;

        
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao acessar arquivo "+e.getMessage());
            return false;
        }

    }

    public boolean alterar(int idAp, String novoNome, double novoPreco) {
        ArrayList<Produto> produtos = listar();

        for (Produto produto : produtos) {
            if (produto.getId() == idAp) {
                produto.setNome(novoNome);
                produto.setPreco(novoPreco);
                return salvar(produtos);
            }
        }
        return false;
    }
}
