public class Barbeiro implements Runnable {
    private int id;
    private Barbearia barbearia;

    public Barbeiro(int id, Barbearia barbearia) {
        this.id = id;
        this.barbearia = barbearia;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Cliente cliente = barbearia.proximoCliente(this); // O barbeiro pega o próximo cliente
                if (cliente != null) {
                    System.out.println("Barbeiro " + getId() + " começou a atender o Cliente " + cliente.getId());
                    synchronized (cliente) {
                        // Simula o tempo de corte de cabelo
                        Thread.sleep((long) (Math.random() * 2000 + 1000));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
