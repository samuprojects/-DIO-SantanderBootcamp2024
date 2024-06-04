package subsystem2.cep;

@SuppressWarnings("ALL")
public class ZipCodeApi {
	
	private static ZipCodeApi instance = new ZipCodeApi();
	
	private ZipCodeApi() {
		super();
	}
	
	public static ZipCodeApi getInstance() {
		return instance;
	}
	
	public String retrieveCity(String zipCode) {
		return "São Paulo";		
	}
	
	public String retrieveState(String zipCode) {
		return "SP";		
	}
}
