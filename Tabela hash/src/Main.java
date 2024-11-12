import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FirstHashTable<String, Integer> tabelaHash1 = new FirstHashTable<>(20);
        SecondHashTable<String,Integer> tabelaHash2 = new SecondHashTable<>(20);

        String arquivoCSV = "src\\female_names.txt";

        //--------------------------------------------------------------------------------------------------
        //Função Hash 1

        long inicio_incer = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            int contador1 = 1;
            while ((linha = br.readLine()) != null) {

                tabelaHash1.put(linha, contador1);

                contador1++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo TXT: " + e.getMessage());
        }
        long fim_incer = System.nanoTime();

        //--------------------------------------------------------------------------------------------------
        //Função Hash 2

        long inicio_incer2 = System.nanoTime();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            int contador = 1;
            while ((linha = br.readLine()) != null) {

                tabelaHash2.put(linha, contador);

                contador++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo TXT: " + e.getMessage());
        }
        long fim_incer2 = System.nanoTime();


        //---------------------------------------------------------------------------------------------------


        long inicio_busca = System.nanoTime();

        int nome = tabelaHash1.get("Ana");

        long fim_busca = System.nanoTime();



        //---------------------------------------------------------------------------------------------------


        long inicio_busca1 = System.nanoTime();

        int nome2 = tabelaHash2.get("Ana");

        long fim_busca1 = System.nanoTime();


        System.out.println("Tabela Hash 1");
        System.out.println("Quantidade de nomes: "+tabelaHash1.getQuantidade_nomes());
        System.out.println("Numero de colisões: "+ tabelaHash1.getColisoes());
        System.out.println("Tempo de inserção: "+ (fim_incer - inicio_incer)+" ns" );
        System.out.println("Tempo de busca no nome '"+nome+"':  "+ (fim_busca - inicio_busca) + "ns");
        System.out.println();
        System.out.println();
        System.out.println("Tabela Hash 2");
        System.out.println("Quantidade de nomes: "+tabelaHash2.getQuantidade_nomes());
        System.out.println("Numero de colisões: "+ tabelaHash2.getColisoes());
        System.out.println("Tempo de inserção: "+ (fim_incer2 - inicio_incer2)+" ns" );
        System.out.println("Tempo de busca no nome '"+nome2+"':  "+ (fim_busca1 - inicio_busca1) + "ns");
        System.out.println();
        System.out.println("Tabela Hash 1");
        tabelaHash1.printDistribution();
        System.out.println();
        System.out.println();
        System.out.println("Tabela Hash 2");
        tabelaHash2.printDistribution();

    }
}
