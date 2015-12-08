package j2ssh;

import internaluse.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Server extends SshConnection {
	String sshLogDir;
	String sshLogFile;

	public Server(String ipAddr, String username, String password)
			throws Exception {
		SshConnection.connect(ipAddr);
		SshConnection.login(username, password);
	}

	@Override
	public boolean executeCommand(String cmd, String[] expectedOutput,
			double timeout) {
		// TODO Auto-generated method stub
		OutputStream out = session.getOutputStream();
		try {
			cmd += "\n";
			System.out.println("send command: " + cmd);
			out.write(cmd.getBytes());
			InputStreamReader in = new InputStreamReader(
					session.getInputStream());
			BufferedReader buff = new BufferedReader(in);
			StringBuilder build = new StringBuilder();
			String data = null;
			while (true) {
				data = buff.readLine();
				System.out.println("--------");
				// build.append(data);
				System.out.println(buff.ready());
				// data += data;
				if (!buff.ready())
					break;
			}
			System.out.println(build.toString());
			buff.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;

	}
}
