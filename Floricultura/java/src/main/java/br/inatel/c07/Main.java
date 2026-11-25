package br.inatel.c07;

import java.util.List;

// Já pronto: serve para testar os seus métodos.
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Floricultura (Java + MySQL) ===");
        PlantaDAO dao = new PlantaDAO();

        mostrar("1. Plantas cadastradas (esperado: 5):", dao.listarPlantas());

        boolean inseriu = dao.inserirPlanta(new Planta("Lavanda", "Lavanda", 15.00, 25));
        System.out.println("\n2. Planta inserida? (esperado: true): " + inseriu);

        mostrar("3. Plantas da espécie Rosa (esperado: 2):", dao.buscarPorEspecie("Rosa"));

        System.out.println("\n4. Preço do Girassol atualizado? (esperado: true): " + dao.atualizarPreco(3, 7.50));
        System.out.println("5. Samambaia removida? (esperado: true): " + dao.removerPlanta(5));
        System.out.println("6. Planta 99 removida? (esperado: false): " + dao.removerPlanta(99));

        System.out.println("\n7. Valor total do estoque (esperado: 1705.0): " + dao.valorTotalEstoque());
        mostrar("8. Lista final (esperado: 5 plantas):", dao.listarPlantas());
    }

    private static void mostrar(String titulo, List<Planta> plantas) {
        System.out.println("\n" + titulo);
        for (Planta p : plantas) {
            System.out.println("  " + p);
        }
    }
}
