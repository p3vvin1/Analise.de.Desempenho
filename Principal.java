import java.io.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        String caminhoArquivo = "arq.txt";
        List<String> linhas = lerArquivo(caminhoArquivo);

        if (linhas.size() < 2) {
            System.out.println("Arquivo inválido.");
            return;
        }

        ListaLigada lista = new ListaLigada();

        
        String[] numeros = linhas.get(0).split(" ");
        for (int i = numeros.length - 1; i >= 0; i--) {
            lista.adicionar(Integer.parseInt(numeros[i]), 0);
        }

        
        int acoes = Integer.parseInt(linhas.get(1));

        
        for (int i = 2; i < linhas.size(); i++) {
            String[] partes = linhas.get(i).split(" ");
            switch (partes[0]) {
                case "A":
                    int valor = Integer.parseInt(partes[1]);
                    int posicao = Integer.parseInt(partes[2]);
                    lista.adicionar(valor, posicao);
                    break;
                case "R":
                    int valRemover = Integer.parseInt(partes[1]);
                    lista.remover(valRemover);
                    break;
                case "P":
                    lista.imprimir();
                    break;
                default:
                    System.out.println("Comando inválido: " + linhas.get(i));
            }
        }
    }

    public static List<String> lerArquivo(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), "UTF-8"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    linhas.add(linha.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return linhas;
    }
}
