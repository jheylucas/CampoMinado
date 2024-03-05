import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import javafx.util.Pair;

class Tabuleiro implements TabuleiroInterativo {
    private Celula[][] tabuleiro;
    private int[][] minas;
    private int linha, coluna;
    private Random random = new Random();
    private Scanner entrada = new Scanner(System.in);

    public Tabuleiro() {
        tabuleiro = new Celula[10][10];
        minas = new int[10][10];
        iniciaTabuleiro();
    }

    public boolean ganhou() {
        int count = 0;
        for (int line = 1; line < 9; line++)
            for (int column = 1; column < 9; column++)
                if (tabuleiro[line][column] instanceof CelulaVazia && !tabuleiro[line][column].isAberta())
                    count++;
        return count == 10;
    }

    public void abrirVizinhas() {
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if (i != 0 || j != 0) {
                    int linhaVizinha = linha + i;
                    int colunaVizinha = coluna + j;
                    if (linhaVizinha >= 1 && linhaVizinha <= 8 && colunaVizinha >= 1 && colunaVizinha <= 8) {
                        Celula vizinha = tabuleiro[linhaVizinha][colunaVizinha];
                        if (!vizinha.isAberta() && !vizinha.isBandeirada()) {
                            vizinha.setAberta(true);
                            if (vizinha instanceof CelulaVazia) {
                                abrirVizinhasVazias(linhaVizinha, colunaVizinha);
                            }
                        }
                    }
                }
    }

    private void abrirVizinhasVazias(int linha, int coluna) {
        Queue<Pair<Integer, Integer>> fila = new LinkedList<>();
        fila.offer(new Pair<>(linha, coluna));

        while (!fila.isEmpty()) {
            Pair<Integer, Integer> coordenadas = fila.poll();
            int linhaAtual = coordenadas.getKey();
            int colunaAtual = coordenadas.getValue();

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    int linhaVizinha = linhaAtual + i;
                    int colunaVizinha = colunaAtual + j;
                    if (linhaVizinha >= 1 && linhaVizinha <= 8 && colunaVizinha >= 1 && colunaVizinha <= 8) {
                        Celula vizinha = tabuleiro[linhaVizinha][colunaVizinha];
                        if (!vizinha.isAberta() && !vizinha.isBandeirada()) {
                            vizinha.setAberta(true);
                            if (vizinha instanceof CelulaVazia) {
                                fila.offer(new Pair<>(linhaVizinha, colunaVizinha));
                            }
                        }
                    }
                }
            }
        }
    }

    public int getPosicao(int linha, int coluna) {
        return minas[linha][coluna];
    }

    public boolean setPosicao() {
        do {
            System.out.print("\nLinha: ");
            linha = entrada.nextInt();
            System.out.print("Coluna: ");
            coluna = entrada.nextInt();

            if ((linha < 1 || linha > 8 || coluna < 1 || coluna > 8 || tabuleiro[linha][coluna] != null)) {
                System.out.println("Esse campo já está sendo exibido ou escolha números de 1 até 8");
            }

        } while (linha < 1 || linha > 8 || coluna < 1 || coluna > 8 || tabuleiro[linha][coluna] != null);

        return getPosicao(linha, coluna) == -1;
    }

    public void exibe() {
        System.out.println("\n Linhas");
        for (int linha = 8; linha > 0; linha--) {
            System.out.println(" " + linha + " ");

            for (int coluna = 1; coluna < 9; coluna++) {
                System.out.print(" " + tabuleiro[linha][coluna]);
            }

            System.out.println();
        }

        
    }

    public void preencheDicas() {
        for (int line = 1; line < 9; line++)
            for (int column = 1; column < 9; column++) {
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if (minas[line][column] != -1)
                            if (minas[line + i][column + j] == -1)
                                minas[line][column]++;
            }
    }

    public void exibeMinas() {
        for (int i = 1; i < 9; i++)
            for (int j = 1; j < 9; j++)
                if (minas[i][j] == -1)
                    tabuleiro[i][j] = null;

        exibe();
    }

    public void iniciaTabuleiro() {
        for (int i = 1; i < tabuleiro.length; i++)
            for (int j = 1; j < tabuleiro.length; j++)
                tabuleiro[i][j] = null;
    }

    public void iniciaMinas() {
        for (int i = 0; i < tabuleiro.length; i++)
            for (int j = 0; j < tabuleiro.length; j++)
                minas[i][j] = 0;
    }

    public void sorteiaMinas() {
        boolean sorteado;
        int linha, coluna;
        for (int i = 0; i < 10; i++) {

            do {
                linha = random.nextInt(8) + 1;
                coluna = random.nextInt(8) + 1;

                if (minas[linha][coluna] == -1)
                    sorteado = true;
                else
                    sorteado = false;
            } while (sorteado);

            minas[linha][coluna] = -1;
        }
    }
}
