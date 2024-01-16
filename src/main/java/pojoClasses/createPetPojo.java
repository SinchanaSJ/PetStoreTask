package pojoClasses;

public class createPetPojo {

	int id;
	String name;
	String [] photoUrls;
	String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String[] getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public createPetPojo(int id, String name, String[] photoUrls, String status) {
		super();
		this.id = id;
		this.name = name;
		this.photoUrls = photoUrls;
		this.status = status;
	}
	
	
	
	
}
