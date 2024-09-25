public class Cliente implements Runnable {
    private int id;
    private Barbearia barbearia;

    public Cliente(int id, Barbearia barbearia) {
        this.id = id;
        this.barbearia = barbearia;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            if (barbearia.cortaCabelo(this)) {
                // O cliente espera até ser chamado pelo barbeiro
                synchronized (this) {
                    System.out.println("Cliente " + getId() + " cortando cabelo...");
                    Thread.sleep(3000); // Simula o tempo de corte de cabelo
                    barbearia.corteTerminado(this); // Cliente termina o corte
                }
            } else {
                Thread.sleep(3000); // O cliente tenta novamente após esperar
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
