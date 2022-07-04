
public class Semaphore {
      private int availConnections; //this refer to number of connections that can be done

      //default constructor
    Semaphore() {
        availConnections = 0;
  
    }

    Semaphore(int availableConnections) {
        this.availConnections = availableConnections;
       
    }

    public synchronized void connect(Device device) {
        device.router.printMessage(device.name + " (" + device.type + ") is arrived.");
        availConnections--;
        if (availConnections < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        device.router.connect(device);

    }

    public synchronized void disconnect(Device device) {
        device.router.disconnect(device);
        availConnections++;
        if (availConnections <= 0) notify();
    }
}
