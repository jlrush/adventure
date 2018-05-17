package TelnetServer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import Game.UserOutput;

public class TelnetOutput extends UserOutput {
	private static final String BrightYellow = "\033[93m";
	private static final String Green = "\033[32m";
	private static final String Yellow = "\033[33m";
	private static final String Cyan = "\033[36m";
	private static final String White = "\033[0m";
	private static final String CLS = "\033[2J";
	private static final String CRLF = "\r\n";

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
	public void clearscreen() {
		writer.print(CLS);
		writer.print("\033[0;0H"); //Home cursor
		writer.flush();
	}
	
	@Override
	public void print(String text) {
		System.out.println(text);
		writer.print(text);
		writer.flush();
	}
	
	@Override
	public void heading(String message) { 
		this.activity(message);
	}
	
	@Override
	public void roomName(String message) {
		System.out.println(message);
		writer.print(CRLF + Cyan + message + White + CRLF);
	}
	
	@Override
	public void activity(String message) {
		System.out.println(message);
		writer.print(CRLF + Yellow + message + White + CRLF);
	}

	@Override
	public void warning(String message) {
		System.out.println(message);
		writer.print(CRLF + BrightYellow + message + White + CRLF);
	}
	
	@Override
	public void prompt(String prompt) {
		System.out.println(prompt);
		writer.print(CRLF + Green + prompt + White);
		writer.flush();		
	}
}
