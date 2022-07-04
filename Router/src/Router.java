
public class Router {
    private int maxConnections;
    Semaphore semaphore;
    Device[] connections;
    int lastConnection;

    Router() {
        maxConnections = 0;
        semaphore = null;
        connections = null;
        lastConnection = 0;
    }

    Router(int maxConnections) {
        this.maxConnections = maxConnections;
        semaphore = new Semaphore(maxConnections);
        connections = new Device[maxConnections];
        lastConnection = 0;
    }

    public void printMessage(String message) {
        System.out.println(message);
       
    }

    public void connect(Device device) {
        for (int i = 0; i < maxConnections; i++) {
            if (connections[i] == null) {
                connections[i] = device;
                lastConnection = i + 1;
                
                break;
            }
            else {
            	 printMessage(device.name + " (" + device.type + ") arrived and waiting");
            }
        }
        printMessage("Connection " + lastConnection + ": " + device.name + " (" + device.type + ") Login");
    }

    public void disconnect(Device device) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null && connections[i].name == device.name) {
                lastConnection = i + 1;
                connections[i] = null;
                break;
            }
        }
        printMessage("Connection " + lastConnection + ": " + device.name + " (" + device.type + ") Logout.");
    }

}