public class BarbeariaApp {
    public static void main(String[] args) {
        int nBarbeiros = Integer.parseInt(args[0]);
        int mCadeiras = Integer.parseInt(args[1]);
        int totalClientes = Integer.parseInt(args[2]);

        Barbearia barbearia = new Barbearia(nBarbeiros, mCadeiras);

        // Inicia barbeiros
        for (int i = 0; i < nBarbeiros; i++) {
            Barbeiro barbeiro = new Barbeiro(i + 1, barbearia);
            new Thread(barbeiro).start();
        }

        // Inicia clientes
        for (int i = 0; i < totalClientes; i++) {
            Cliente cliente = new Cliente(i + 1, barbearia);
            new Thread(cliente).start();
        }
    }
}
