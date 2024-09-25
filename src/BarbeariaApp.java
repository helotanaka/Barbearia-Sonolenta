public class BarbeariaApp {
    public static void main(String[] args) {
        int numBarbeiros = Integer.parseInt(args[0]);
        int numCadeiras = Integer.parseInt(args[1]);
        int numClientes = Integer.parseInt(args[2]);

        Barbearia barbearia = new Barbearia(numCadeiras);

        // Criação e inicialização dos barbeiros
        for (int i = 1; i <= numBarbeiros; i++) {
            Barbeiro barbeiro = new Barbeiro(i, barbearia); // Cada barbeiro recebe um ID
            new Thread(barbeiro).start(); // Inicializa a thread para cada barbeiro
        }

        // Criação e inicialização dos clientes
        for (int i = 1; i <= numClientes; i++) {
            Cliente cliente = new Cliente(i, barbearia); // Cada cliente recebe um ID
            new Thread(cliente).start(); // Inicializa a thread para cada cliente
        }
    }
}
