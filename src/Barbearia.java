import java.util.LinkedList;
import java.util.Queue;

public class Barbearia {
    private int totalCadeiras;
    private Queue<Cliente> filaDeEspera; // Fila de objetos Cliente

    public Barbearia(int totalCadeiras) {
        this.totalCadeiras = totalCadeiras;
        this.filaDeEspera = new LinkedList<>();
    }

    // Operação chamada pelos clientes:
    public synchronized boolean cortaCabelo(Cliente cliente) {
        if (filaDeEspera.size() == totalCadeiras) {
            System.out.println("Cliente " + cliente.getId() + " tentou entrar na barbearia, mas está lotada... indo dar uma voltinha");
            return false;
        } else {
            System.out.println("Cliente " + cliente.getId() + " esperando corte...");
            filaDeEspera.add(cliente); // Adiciona o cliente à fila
            notify();  // Notifica um barbeiro que há um cliente esperando
            return true;
        }
    }

    // Operação chamada pelos barbeiros:
    public synchronized Cliente proximoCliente(Barbeiro barbeiro) throws InterruptedException {
        while (filaDeEspera.isEmpty()) {
            System.out.println("Barbeiro " + barbeiro.getId() + " indo dormir um pouco... não há clientes na barbearia...");
            wait(); // O barbeiro espera por um cliente
        }

        Cliente cliente = filaDeEspera.poll(); // Remove o primeiro cliente da fila
        System.out.println("Barbeiro " + barbeiro.getId() + " acordou! Começando os trabalhos com o Cliente " + cliente.getId());
        return cliente; // O barbeiro começa a atender o cliente
    }

    public synchronized void corteTerminado(Cliente cliente) {
        System.out.println("Cliente " + cliente.getId() + " terminou o corte... saindo da barbearia!");
        notify(); // Notifica outro barbeiro que pode atender o próximo cliente
    }
}
