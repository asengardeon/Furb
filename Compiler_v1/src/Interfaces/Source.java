package Interfaces;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Source {

	public void save(String path, String source) throws IOException {
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path)));
		try {
			try {
				int count = 0;
				while (count < source.length()) {
					out.write(source.getBytes()[count]);
					count++;
				}
			} catch (IOException e) {
				throw new IOException("Não foi possível salvar o arquivo");
			}
		} finally {
			out.flush();
		}

	}

	public String open(String patch) throws IOException {
		StringBuilder str = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(patch));
		try {
			try {

				while (in.ready()) {
					str.append(in.readLine() + "\n");
				}

			} catch (IOException e) {
				throw new IOException("Não foi possível abrir o arquivo");
			}
		} finally {
			in.close();
		}
		return str.toString();
	}
}
