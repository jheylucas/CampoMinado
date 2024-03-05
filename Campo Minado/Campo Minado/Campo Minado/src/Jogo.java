import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;
    private boolean terminar = false;
    private boolean ganhou = false;
    private int[] jogada;
    private int rodada = 0;
    private List<Jogador> listaDeRecordesDoJogo = new ArrayList<>();

    public Jogo() {
        tabuleiro = new Tabuleiro();
        jogada = new int[2];
    }

    public void jogar() {
        do {
            rodada++;
            System.out.println("Rodada " + rodada);
            tabuleiro.exibe();
            terminar = tabuleiro.setPosicao();
            terminar = tabuleiro.setPosicao();

            if (!terminar) {
                tabuleiro.abrirVizinhas();
                terminar = tabuleiro.ganhou();
            }

        } while (!terminar);

        if (!tabuleiro.ganhou()) {
            System.out.println("Havia uma mina ! Você perdeu!");
            tabuleiro.exibeMinas();
        } else {
            System.out.println("Parabéns, você deixou os 8 campos de minas livres em " + rodada + " rodadas");
            tabuleiro.exibeMinas();
        }
    }

    
    private void registrarRecorde() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite seu nome para mostrarmos sua posição no recorde: ");
            String nomeJogador = scanner.nextLine();
            listaDeRecordesDoJogo.add(new Jogador(nomeJogador, rodada));
        }
        Collections.sort(listaDeRecordesDoJogo, Comparator.comparing(Jogador::getPontuacao));
        if (listaDeRecordesDoJogo.size() > 10) {
            listaDeRecordesDoJogo.remove(10);
        }
    }

    
    private void exibirRecordes() {
        System.out.println("\nRecordes:");
        for (int i = 0; i < listaDeRecordesDoJogo.size(); i++) {
            System.out.println((i + 1) + ". " + listaDeRecordesDoJogo.get(i).getNome() + " - " + listaDeRecordesDoJogo.get(i).getPontuacao() + " rodadas");
        }
    }
}