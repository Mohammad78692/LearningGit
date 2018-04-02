package com.osi.loganalyzer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osi.loganalyzer.model.LogRead;
import com.osi.loganalyzer.model.LogsSpcification;
import com.osi.loganalyzer.util.TimeZoneConvertion;

/**
 * @author moirfan
 *
 */
@Service
public class CommandService {

	private String formate1;

	@Autowired
	LogsSpcification logs;

	public StringBuilder getLogsBydebug(LogRead logRead) {
		int count = 0;
		StringBuilder output = new StringBuilder("");
		File file = new File(logs.getLogfilelocation());
		Scanner in = null;

		try {
			in = new Scanner(file);
			while (in.hasNext()) {

				String line = in.nextLine();
				formate1 = TimeZoneConvertion.formate1;
				if (line.contains(formate1)) {
					System.out.println("in if ");
					output = getlogs(in, line, count, logRead, output);
				}
				System.out.println("oustside if ");
			}

		} catch (FileNotFoundException e) {
			output.append("requested file is not available " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			output.append("requested data not available " + e.getMessage());
			// e.printStackTrace();
		}
		if (output.toString().isEmpty()) {
			output.append("Requested log is not available");
		}
		return output;

	}

	public StringBuilder getlogs(Scanner in, String line, int count, LogRead logRead, StringBuilder output) {

		line.contains(logRead.getDebug());
		while (in.hasNext()) {
			line = in.nextLine();
			if (line.contains(logRead.getDebug())) {
				count++;

				output.append(line);
				output.append("\r\n");
				System.out.println(line);
				String ss = in.nextLine();

				while (!((ss.contains(logs.getDebug()) | ss.contains(logs.getFatal()) | ss.contains(logs.getWarn())
						| ss.contains(logs.getInfo())))) {

					output.append("\r\n");
					output.append(ss);
					System.out.println(ss);
					try {
						ss = in.nextLine();
					} catch (NoSuchElementException e) {
						System.out.println("no further line found");
						break;
					}
				}

			}

		}

		return output;
	}

	public StringBuilder getcount(LogRead logRead) {
		StringBuilder output = new StringBuilder("");
		File file = new File(logs.getLogfilelocation());

		Scanner in = null;

		try {
			in = new Scanner(file);
			while (in.hasNext()) {
				String line = in.nextLine();
				if (line.contains(formate1)) {
					output = getcount1(in, line);
				}

			}
		} catch (FileNotFoundException e) {
			output.append("requested file is not available " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			output.append("requested data not available " + e.getMessage());
			e.printStackTrace();
		}
		if (output.toString().isEmpty()) {
			output.append("Requested logs is not available");
		}

		return output;
	}

	public StringBuilder getcount1(Scanner in, String line) {

		int count[] = { 0, 0, 0, 0, 0 };
		while (in.hasNext()) {
			line = in.nextLine();

			if (line.contains(logs.getInfo())) {
				count[0]++;
			} else if (line.contains(logs.getError())) {
				count[1]++;
			} else if (line.contains(logs.getDebug())) {
				count[2]++;
			} else if (line.contains(logs.getWarn())) {
				count[3]++;
			} else if (line.contains(logs.getFatal())) {
				count[4]++;
			}
		}
		StringBuilder output1 = new StringBuilder("");
		return output1.append(
				"log Info count is " + count[0] + ",\n log Error count is " + count[1] + ",\n log Debug count is "
						+ count[2] + ",\n log Warn count is" + count[3] + ",\n log Fatal count is " + count[4] + " ");

	}

	public StringBuilder getlogBySearch(LogRead logRead) {

		int count = 0;
		StringBuilder output = new StringBuilder("");
		File file = new File(logs.getLogfilelocation());
		Scanner in = null;

		try {
			in = new Scanner(file);
			while (in.hasNext()) {
				String search = logRead.getSearch();
				String line = in.nextLine();
				if (line.contains(formate1)) {
					searchingbyString(line, search, in, count, output);
				}

			}
		} catch (FileNotFoundException e) {
			output.append("requested file is not available " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			output.append("requested data not available " + e.getMessage());
			e.printStackTrace();
		}

		if (output.toString().isEmpty()) {
			output.append("Requested string is not available, please check case also");
		}
		return output;
	}

	public void searchingbyString(String line, String search, Scanner in, int count, StringBuilder output) {
		while (in.hasNext()) {
			line = in.nextLine();
			if (line.contains(search)) {
				count++;

				output.append(line);
				output.append("\r\n");
				System.out.println(line);

			}

		}
		System.out.println(count);
	}

}
