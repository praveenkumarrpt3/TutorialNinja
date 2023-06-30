package experience;

public class GetPropertiesOfSystem {

	public static void main(String[] args) {
		
		System.getProperties().list(System.out);
		
		
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.name"));
		System.out.println(System.getProperty("java.version"));
	}
}
