package TelnetServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import Game.UserOutput;

public class TelnetOutput extends UserOutput {
	private OutputStream stream;
	private PrintWriter writer;
	
	public TelnetOutput(Socket socket) throws IOException {
		stream = socket.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8), true);
	}
	
	public void close() {
		writer.close();
	}
	
	@Override
	public void print(String text) {
		writer.write(text + "\r\n");
	}

	public void println(String text) {
		writer.print(text + "\r\n");
	}
}
