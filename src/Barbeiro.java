import java.util.concurrent.ThreadLocalRandom;

public class Barbeiro extends Pessoa implements Runnable {
    private Barbearia barbearia;

    public Barbeiro(int id, Barbearia barbearia) {
        super(id);
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Cliente cliente = barbearia.proximoCliente();
                if (cliente != null) {
                    System.out.println("Barbeiro " + getId() + " cortando cabelo do Cliente " + cliente.getId());
                    // Simula o tempo de corte de cabelo (1 a 3 segundos)
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
                    barbearia.corteTerminado(cliente);
                    System.out.println("Cliente " + cliente.getId() + " terminou o corte... saindo da barbearia!");
                } else {
                    System.out.println("Barbeiro " + getId() + " indo dormir... não há clientes na barbearia.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
