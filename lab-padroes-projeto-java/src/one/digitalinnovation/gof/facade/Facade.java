package one.digitalinnovation.gof.facade;

import subsystem1.crm.CrmService;
import subsystem2.cep.ZipCodeApi;

public class Facade {
	
	public void migrateCustomer(String name, String zipCode) {
		
		String city = ZipCodeApi.getInstance().retrieveCity(zipCode);
		String state = ZipCodeApi.getInstance().retrieveState(zipCode);
		
		CrmService.saveCustomer(name, zipCode, city, state);		
	}
}
