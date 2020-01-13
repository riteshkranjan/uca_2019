package com.uca.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ExternSort {

	public static long estimateBestSizeOfBlocks(File filetobesorted) {
		long sizeoffile = filetobesorted.length();
		// we don't want to open up much more than 1024 temporary files
		final int MAXTEMPFILES = 1024;
		long blocksize = sizeoffile / MAXTEMPFILES;
		// If blocksize is smaller than half the free memory, grow it.
		long freemem = Runtime.getRuntime().freeMemory();
		if (blocksize < freemem / 2)
			blocksize = freemem / 2;
		else {
			if (blocksize >= freemem)
				System.err.println("We expect to run out of memory. ");
		}
		return blocksize;
	}

	public static List<File> sortInBatch(File file, Comparator<String> cmp) throws IOException {
		List<File> files = new ArrayList<File>();
		BufferedReader fbr = new BufferedReader(new FileReader(file));
		long blocksize = estimateBestSizeOfBlocks(file);// in bytes
		try {
			List<String> tmplist = new ArrayList<String>();
			String line = "";
			try {
				while (line != null) {
					long currentblocksize = 0;// in bytes
					while ((currentblocksize < blocksize) && ((line = fbr.readLine()) != null)) {
						// as long as you have 2MB
						tmplist.add(line);
						currentblocksize += line.length() + 2 + 40;
						// 16 bits per character + 40 bytes overhead
					}
					files.add(sortAndSave(tmplist, cmp));
					tmplist.clear();
				}
			} catch (EOFException oef) {
				if (tmplist.size() > 0) {
					files.add(sortAndSave(tmplist, cmp));
					tmplist.clear();
				}
			}
		} finally {
			fbr.close();
		}
		return files;
	}

	public static File sortAndSave(List<String> tmplist, Comparator<String> cmp) throws IOException {
		Collections.sort(tmplist, cmp); //
		File newtmpfile = File.createTempFile("sortInBatch", "flatfile");
		newtmpfile.deleteOnExit();
		BufferedWriter fbw = new BufferedWriter(new FileWriter(newtmpfile));
		try {
			for (String r : tmplist) {
				fbw.write(r);
				fbw.newLine();
			}
		} finally {
			fbw.close();
		}
		return newtmpfile;
	}

	public static int mergeSortedFiles(List<File> files, File outputfile, final Comparator<String> cmp)
			throws IOException {
		PriorityQueue<BinaryFileBuffer> pq = new PriorityQueue<BinaryFileBuffer>(11,
				new Comparator<BinaryFileBuffer>() {
					public int compare(BinaryFileBuffer i, BinaryFileBuffer j) {
						return cmp.compare(i.peek(), j.peek());
					}
				});
		for (File f : files) {
			BinaryFileBuffer bfb = new BinaryFileBuffer(f);
			pq.add(bfb);
		}
		BufferedWriter fbw = new BufferedWriter(new FileWriter(outputfile));
		int rowcounter = 0;
		try {
			while (pq.size() > 0) {
				BinaryFileBuffer bfb = pq.poll();
				String r = bfb.pop();
				fbw.write(r);
				fbw.newLine();
				++rowcounter;
				if (bfb.empty()) {
					bfb.fbr.close();
					bfb.originalfile.delete();// we don't need you anymore
				} else {
					pq.add(bfb); // add it back
				}
			}
		} finally {
			fbw.close();
			for (BinaryFileBuffer bfb : pq)
				bfb.close();
		}
		return rowcounter;
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 2) {
			System.out.println("please provide input and output file names");
			return;
		}
		String inputfile = args[0];
		String outputfile = args[1];
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String r1, String r2) {
				return r1.compareTo(r2);
			}
		};
		List<File> l = sortInBatch(new File(inputfile), comparator);
		mergeSortedFiles(l, new File(outputfile), comparator);
	}
}

class BinaryFileBuffer {
	public static int BUFFERSIZE = 2048;
	public BufferedReader fbr;
	public File originalfile;
	private String cache;
	private boolean empty;

	public BinaryFileBuffer(File f) throws IOException {
		originalfile = f;
		fbr = new BufferedReader(new FileReader(f), BUFFERSIZE);
		reload();
	}

	public boolean empty() {
		return empty;
	}

	private void reload() throws IOException {
		try {
			if ((this.cache = fbr.readLine()) == null) {
				empty = true;
				cache = null;
			} else {
				empty = false;
			}
		} catch (EOFException oef) {
			empty = true;
			cache = null;
		}
	}

	public void close() throws IOException {
		fbr.close();
	}

	public String peek() {
		if (empty())
			return null;
		return cache.toString();
	}

	public String pop() throws IOException {
		String answer = peek();
		reload();
		return answer;
	}

}