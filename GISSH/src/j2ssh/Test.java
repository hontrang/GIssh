package j2ssh;

public class Test {

	public static void main(String[] args) throws Exception {
		Server prov1 = new Server("100.20.56.39", "root", "Escspv_123");
		String cmd = "cd /usr/globaltools/catt/Avaya/endpoint";
		String[] out = { "1", "2" };
		prov1.executeCommand(cmd, out, 1000);
		cmd = "ls";
		prov1.executeCommand(cmd, out, 1000);
		prov1.cleanUp();
		System.out.println("Done");
	}
}
