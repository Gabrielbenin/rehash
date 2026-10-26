public class TabelaRehash {

    private int[] tabela;
    private boolean[] ocupado;
    private int tamanho;
    private int quantidade;
    private int limite;

    private int totalColisoes;
    private int[] valoresInseridos;
    private int[] colisoesPorValor;
    private int qtdInseridos;

    public TabelaRehash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new int[tamanho];
        this.ocupado = new boolean[tamanho];
        this.quantidade = 0;
        this.limite = tamanho * 90 / 100;
        this.totalColisoes = 0;
        this.valoresInseridos = new int[tamanho];
        this.colisoesPorValor = new int[tamanho];
        this.qtdInseridos = 0;
    }

    private int hash(int valor) {
        return valor % tamanho;
    }

    private int rehash(int posicao) {
        return (posicao + 1) % tamanho;
    }

    public boolean inserir(int valor) {
        if (quantidade >= limite) {
            return false;
        }

        int posicao = hash(valor);
        int colisoes = 0;

        while (ocupado[posicao]) {
            colisoes++;
            posicao = rehash(posicao);
        }

        tabela[posicao] = valor;
        ocupado[posicao] = true;
        quantidade++;

        totalColisoes += colisoes;
        valoresInseridos[qtdInseridos] = valor;
        colisoesPorValor[qtdInseridos] = colisoes;
        qtdInseridos++;

        return true;
    }

    public int buscar(int valor) {
        int posicao = hash(valor);
        int tentativas = 0;

        while (ocupado[posicao] && tentativas < tamanho) {
            if (tabela[posicao] == valor) {
                return posicao;
            }
            posicao = rehash(posicao);
            tentativas++;
        }
        return -1;
    }

    public int[] tresMaisColidiram() {
        int[] indices = {-1, -1, -1};

        for (int k = 0; k < 3; k++) {
            int maior = -1;
            for (int i = 0; i < qtdInseridos; i++) {
                if (i != indices[0] && i != indices[1]) {
                    if (maior == -1 || colisoesPorValor[i] > colisoesPorValor[maior]) {
                        maior = i;
                    }
                }
            }
            indices[k] = maior;
        }
        return indices;
    }

    public int getValor(int indice) {
        return valoresInseridos[indice];
    }

    public int getColisoes(int indice) {
        return colisoesPorValor[indice];
    }

    public void imprimirPosicoesLivres() {
        int cont = 0;
        for (int i = 0; i < tamanho; i++) {
            if (!ocupado[i]) {
                System.out.print(i + " ");
                cont++;
            }
        }
        System.out.println();
        System.out.println("Total de posicoes livres: " + cont);
    }

    public int getTotalColisoes() {
        return totalColisoes;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
