package slp.core.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import slp.core.lexing.runners.LexerRunner;
import slp.core.util.Pair;

public class Writer {

	public static void writeContent(File file, String content) throws IOException {
		try (BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
			fw.append(content);
		}
	}

	public static void writeLines(File file, List<String> lines) throws IOException {
		try (BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
			for (String line : lines) {
				fw.append(line);
				fw.append('\n');
			}
		}
	}
	
	public static <T> void writeAny(File file, List<List<T>> lines) throws IOException {
		writeAny(file, lines.stream().map(List::stream));
	}
	
	public static <T> void writeAny(File file, Stream<Stream<T>> lines) throws IOException {
		try (BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
			lines.forEach(l -> {
				try {
					List<T> line = l.collect(Collectors.toList());
					for (int j = 0; j < line.size(); j++) {
						fw.append(line.get(j).toString());
						if (j < line.size() - 1) fw.append('\t');
					}
					fw.append('\n');
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}
	
	public static void writeTokenized(File file, List<List<String>> lines) throws IOException {
		writeTokenized(file, lines.stream().map(List::stream));
	}
	
	public static void writeTokenized(File file, Stream<Stream<String>> lines) throws IOException {
		try (BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
			lines.forEach(l -> {
				try {
					List<String> line = l.collect(Collectors.toList());
					for (int j = 0; j < line.size(); j++) {
						String token = line.get(j);
						fw.append(token.replaceAll("\n", "\\n").replaceAll("\t", "\\t"));
						if (j < line.size() - 1) fw.append('\t');
					}
					fw.append('\n');
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	 //writes tokens and their entropies to a file
	public static Pair<File, List<List<Double>>> writeEntropies(LexerRunner lexerRunner, Pair<File, List<List<Double>>> p, boolean flag_MRR) throws IOException {
//		String logFile = p.left.getAbsolutePath().replace(".JP",".entropy").replace("new-test-set","cache-res");
//		if (flag_MRR){
//			logFile = logFile.replace(".entropy",".MRR");
//		}
//
//		FileWriter logger =  new FileWriter(logFile);
//
//		//System.out.println("Log file: "+p.left.getAbsolutePath().replace(".java",".entropy").replace("new-test-set","cache-res"));
//
//		List<List<String>> tokens = lexerRunner.lexFile(p.left)
//				.map(l -> l.collect(Collectors.toList())).collect(Collectors.toList());
//		//logger.append(p.left.getAbsolutePath());
//		//logger.append('\n');
//		List<List<Double>> right = p.right;
//		for (int i = 0; i < right.size(); i++) {
//			List<Double> line = right.get(i);
//			for (int j = 0; j < line.size(); j++) {
//				logger.append(String.format("%s" + (char) 31 + "%.6f", tokens.get(i).get(j), line.get(j)));
//				if (j < line.size() - 1) logger.append('\t');
//			}
//			logger.append('\n');
//		}
//		logger.close();
		return p;
	}


}
