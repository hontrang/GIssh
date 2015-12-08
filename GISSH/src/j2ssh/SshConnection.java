package j2ssh;

import internaluse.log;

import java.io.IOException;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.configuration.ConfigurationLoader;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.HostKeyVerification;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

public abstract class SshConnection {

	public static SshClient sshClnt = new SshClient();
	public static SessionChannelClient session;

	public abstract boolean executeCommand(String command,
			String[] expectedOutput, double timeout) throws IOException;

	public static boolean connect(String remoteIP) throws IOException {
		try {
			ConfigurationLoader.initialize(false);
			HostKeyVerification hostKey = new IgnoreHostKeyVerification();
			sshClnt.connect(remoteIP, hostKey);
			if (sshClnt.isConnected()) {
				System.out.println("Connected...");
				return true;
			}
			System.out.println("Failed to connect");
			return false;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public static boolean login(String username, String password)
			throws IOException {
		try {
			PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
			auth.setUsername(username);
			auth.setPassword(password);
			int result = sshClnt.authenticate(auth);
			if (result == AuthenticationProtocolState.COMPLETE) {
				session = sshClnt.openSessionChannel();
				session.startShell();
				System.out.println("Logged in and shell opened...");
				return true;
			} else if (result == AuthenticationProtocolState.FAILED) {
				System.out.println("Failed to login");
				return false;
			}
			return false;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}

	public boolean cleanUp() {
		try {
			session.close();
			sshClnt.disconnect();
			if (sshClnt.isConnected() == true) {
				System.out.println("Failed to disconnected");
				return false;
			}
			System.out.println("\nDisconnected");
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
}