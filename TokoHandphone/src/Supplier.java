
public class Supplier {

	private String merekHandphone;
	private String typeHandphone;	
	private String namaSupplier;
	private String alamatSupplier;

	public Supplier(String merekHandphone, String typeHandphone, String namaSupplier, String alamatSupplier) {
		super();
		this.merekHandphone = merekHandphone;
		this.typeHandphone = typeHandphone;
		this.namaSupplier = namaSupplier;
		this.alamatSupplier = alamatSupplier;
	}
	
	
	
	public String getMerekHandphone() {
		return merekHandphone;
	}



	public void setMerekHandphone(String merekHandphone) {
		this.merekHandphone = merekHandphone;
	}



	public String getTypeHandphone() {
		return typeHandphone;
	}



	public void setTypeHandphone(String typeHandphone) {
		this.typeHandphone = typeHandphone;
	}



	public String getNamaSupplier() {
		return namaSupplier;
	}
	public void setNamaSupplier(String namaSupplier) {
		this.namaSupplier = namaSupplier;
	}
	public String getAlamatSupplier() {
		return alamatSupplier;
	}
	public void setAlamatSupplier(String alamatSupplier) {
		this.alamatSupplier = alamatSupplier;
	}
	
	
	
}
