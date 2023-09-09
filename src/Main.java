import entities.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Exercício de fixação do curso DevSuperior\nExpressões Lambdas\n\n");

        String path = "D:\\WorkSpace\\Arquivo\\exercicioLambda.txt";
        List<Product> produto = new ArrayList<>();

        try (BufferedReader ler = new BufferedReader(new FileReader(path))) {
            String linha = ler.readLine();

            while (linha != null) {
                String[] vetor = linha.split(",");
                String nome = vetor[0];
                Double preco = Double.parseDouble(vetor[1]);

                produto.add(new Product(nome, preco));
                linha = ler.readLine();
            }

            Double media = produto.stream().map(x -> x.getPrice()).reduce(0.0, (x, y) -> x + y)
                    / produto.size();

            Comparator<String> comp = (x, y) -> x.toUpperCase().compareTo(y.toUpperCase());

            List<String> nomes = produto.stream().filter(x -> x.getPrice() < media).map(x -> x.getName())
                    .sorted(comp.reversed()).toList();

            System.out.printf("\nPreço médio dos produtos: R$ %.2f\n\n", media);

            for (String p : nomes) {
                System.out.println(p);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error => " + e);
            ;
        } catch (IOException e) {
            System.out.println("Error => " + e);
        }

    }
}