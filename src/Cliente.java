import java.util.concurrent.ThreadLocalRandom;

public class Cliente extends Pessoa implements Runnable {
    private Barbearia barbearia;

    public Cliente(int id, Barbearia barbearia) {
        super(id);
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (barbearia.cortaCabelo(this)) {
                    System.out.println("Cliente " + getId() + " cortou o cabelo!");
                } else {
                    System.out.println("Cliente " + getId() + " tentou entrar na barbearia, mas está lotada... indo dar uma voltinha");
                }
                // Espera entre 3 a 5 segundos
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000, 5000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
