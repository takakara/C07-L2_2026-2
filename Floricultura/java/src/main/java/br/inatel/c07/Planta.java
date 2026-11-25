package br.inatel.c07;

// Classe modelo (já pronta): cada objeto Planta representa uma linha da tabela planta.
public class Planta {

    private int id;
    private String nome;
    private String especie;
    private double preco;
    private int estoque;

    // Construtor usado ao LER do banco (já temos o id)
    public Planta(int id, String nome, String especie, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Construtor usado ao INSERIR (o id é gerado pelo AUTO_INCREMENT)
    public Planta(String nome, String especie, double preco, int estoque) {
        this(0, nome, especie, preco, estoque);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " (" + especie + ") - R$ " + String.format("%.2f", preco)
                + " - estoque: " + estoque;
    }
}
