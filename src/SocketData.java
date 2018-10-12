import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class SocketData {

	private Socket socket;
	private DataInputStream inputStream;
	private PrintStream outputStream;
	private String clientAddress;
	
	public SocketData(Socket socket) {
		this.socket=socket;
		try {
			inputStream= new DataInputStream(socket.getInputStream());
			outputStream=new PrintStream(socket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
		clientAddress=socket.getInetAddress()+":"+socket.getPort();
	}
	
	public Socket getSocket() {
		return socket;
	}
	public DataInputStream getInputStream() {
		return inputStream;
	}
	public PrintStream getOutpuStream() {
		return outputStream;
	}
	public String getClientAddress() {
		return clientAddress;
	}
}
