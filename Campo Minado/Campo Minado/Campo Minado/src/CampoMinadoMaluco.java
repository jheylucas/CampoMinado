import java.util.Random;


// -------------------------------------------------------------------------------------//
//                  CAMPO MINADO - Jheymerson Santos e Maria Almeida 
// -------------------------------------------------------------------------------------//


interface TabuleiroInterativo {
    boolean ganhou();
    void abrirVizinhas();
    boolean setPosicao() throws ValorAtributoInvalidoException;
    void exibe();

}


class ValorAtributoInvalidoException extends Exception {
    public static final char[] GetMessage = null;

    public ValorAtributoInvalidoException(String mensagem) {
        super(mensagem);
    }
}


class CampoMinadoMaluco {

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.jogar();
    }
}

// Classe base para representar uma célula no tabuleiro
abstract class Celula {
    protected boolean aberta;
    protected boolean bandeirada;
    protected boolean maluca;

    public Celula() {
        this.aberta = false;
        this.bandeirada = false;
        this.maluca = false;
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

	public boolean isBandeirada() {
        return bandeirada;
    }

    public void setBandeirada(boolean bandeirada) {
        this.bandeirada = bandeirada;
    }

    public boolean isMaluca() {
        return maluca;
    }

    public void setMaluca(boolean maluca) {
        this.maluca = maluca;
    }

    public abstract boolean modificarBomba();

}

class CelulaMaluca extends Celula {
    private double chanceMudancaBomba;

    public CelulaMaluca(double chanceMudancaBomba) {
        super();
        this.chanceMudancaBomba = chanceMudancaBomba;
    }

    public boolean modificarBomba() {
        Random random = new Random();
        return random.nextDouble() < chanceMudancaBomba;
    }
}

// Classe derivada (herança) que representa uma célula com uma bomba
class CelulaComBomba extends CelulaMaluca {
    public CelulaComBomba(double chanceMudancaBomba) {
        super(chanceMudancaBomba);

    }

}

class JogoMaluco extends Jogo {
    public JogoMaluco() {
        super();
    }

}

// Classe derivada (herança) que representa uma célula vizinha a uma bomba
class CelulaVizinha extends Celula {
    private int numeroBombasVizinhas;

    public CelulaVizinha(int numeroBombasVizinhas) {
        super();
        this.numeroBombasVizinhas = numeroBombasVizinhas;
    }

    public int getNumeroBombasVizinhas() {
        return numeroBombasVizinhas;
    }

    @Override
    public boolean modificarBomba() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarBomba'");
    }
}

// Classe derivada (herança) que representa uma célula vazia (sem bomba e sem vizinhos com bomba)
class CelulaVazia extends Celula {
    public CelulaVazia() {
        super();
    }

    @Override
    public boolean modificarBomba() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarBomba'");
    }
}

class Jogador {
    private String nome;
    private int pontuacao;

    public Jogador(String nome, int pontuacao) {
    this.nome = nome;
    this.pontuacao = pontuacao;

    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

}
