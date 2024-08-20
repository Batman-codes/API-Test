package utils;

public enum Endpoints {
	
	createUser("api/user"),
	getUser("api/users");
	
	private String endpoint;
	
	Endpoints(String endpoint) {
		
		this.endpoint = endpoint;
	}
	
	public String getEndPoint() {
		return endpoint;
	}
	
	

}
