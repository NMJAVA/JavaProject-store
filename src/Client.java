import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class Client{
//User client connect to the server
	public static void main(String[] args) {
		Socket socket = null;
		DataInputStream fromNetInputStram;
		DataInputStream consoleInput;
		PrintStream toNetOutputStream;
		String line="";
		String serverIp="LocalHost";
		String Option="";
		boolean chatOn=true;
		
		

		try {
			socket=new Socket(serverIp,7000);
			System.out.println(new Date()+"-->connected to server at "+ serverIp+":"+socket.getLocalPort());
			fromNetInputStram= new DataInputStream(socket.getInputStream());
			toNetOutputStream=new PrintStream(socket.getOutputStream());
			consoleInput= new DataInputStream(System.in);
			System.out.println(new Date()+"-->Recived from server: "+ fromNetInputStram.readLine());
			
			
			LoginForm login =new LoginForm(socket,fromNetInputStram,toNetOutputStream);
			while(true){
			
					

			}
		}catch (Exception e) {System.err.println(e);
		}finally {
			try {
				socket.close();
				System.out.println("Client said goddbye...");
			}catch(IOException e) {}
		}
	}
}
