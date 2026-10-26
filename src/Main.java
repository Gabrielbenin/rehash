import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int tamanho = 100;
        int elementos = 100;

        TabelaRehash tabela = new TabelaRehash(tamanho);
        Random random = new Random(1090);

        int recusados = 0;
        for (int i = 0; i < elementos; i++) {
            int valor = random.nextInt(100001);
            if (!tabela.inserir(valor)) {
                recusados++;
            }
        }

        System.out.println("===== RE HASH =====");
        System.out.println("Tentativas de insercao: " + elementos);
        System.out.println("Elementos inseridos: " + tabela.getQuantidade());
        System.out.println("Insercoes bloqueadas (90% ocupada): " + recusados);
        System.out.println();

        System.out.println("Total de colisoes: " + tabela.getTotalColisoes());
        System.out.println();

        int[] top = tabela.tresMaisColidiram();
        System.out.println("Elementos que mais colidiram:");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + "o: " + tabela.getValor(top[i]) + " (" + tabela.getColisoes(top[i]) + " colisoes)");
        }
        System.out.println();

        System.out.println("Posicoes livres na matriz:");
        tabela.imprimirPosicoesLivres();
        System.out.println();

        System.out.println("Teste de busca:");
        for (int i = 0; i < 3; i++) {
            int valor = tabela.getValor(top[i]);
            int pos = tabela.buscar(valor);
            if (pos != -1) {
                System.out.println("Valor " + valor + " encontrado na posicao " + pos);
            } else {
                System.out.println("Valor " + valor + " nao encontrado");
            }
        }
    }
}
