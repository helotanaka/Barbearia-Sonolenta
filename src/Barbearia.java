import java.util.*;
import java.util.concurrent.locks.*;

public class Barbearia {
    private final int n;  // Número de barbeiros
    private final int m;  // Número de cadeiras de espera
    private final List<Cliente> filaClientes;
    private final Lock lock;
    private final Condition condCliente;
    private final Condition condBarbeiro;

    public Barbearia(int n, int m) {
        this.n = n;
        this.m = m;
        this.filaClientes = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.condCliente = lock.newCondition();
        this.condBarbeiro = lock.newCondition();
    }

    // Chamada pelos clientes
    public boolean cortaCabelo(Cliente c) throws InterruptedException {
        lock.lock();
        try {
            if (filaClientes.size() >= m) {
                return false;  // Barbearia lotada
            }
            filaClientes.add(c);
            condBarbeiro.signal();  // Notifica barbeiros que há clientes
            while (!filaClientes.contains(c)) {
                condCliente.await();  // Espera sua vez na fila
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    // Chamada pelos barbeiros
    public Cliente proximoCliente() throws InterruptedException {
        lock.lock();
        try {
            while (filaClientes.isEmpty()) {
                condBarbeiro.await();  // Espera até ter clientes
            }
            return filaClientes.remove(0);
        } finally {
            lock.unlock();
        }
    }

    public void corteTerminado(Cliente c) {
        lock.lock();
        try {
            condCliente.signalAll();  // Acorda todos os clientes na fila
        } finally {
            lock.unlock();
        }
    }
}
