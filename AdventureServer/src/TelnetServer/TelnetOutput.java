package TelnetServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import Game.UserOutput;

public class TelnetOutput extends UserOutput {
	//private OutputStream stream;
	private PrintWriter writer;
	
	public TelnetOutput(Socket socket) throws IOException {
		OutputStream stream = socket.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(stream, StandardCharsets.UTF_8), true);
	}

	public TelnetOutput(PrintWriter writer) {
		this.writer = writer;
	}

	public void close() {
		writer.close();
	}
	
	@Override
	public void print(String text) {
		System.out.print(text);
		writer.println(text);
	}
}
