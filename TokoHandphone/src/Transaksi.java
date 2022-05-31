
public class Transaksi {
	private String idTransaksi;
	private String idMember;
	private String tanggalTransaksi;

	public Transaksi(String idTransaksi, String idMember, String tanggalTransaksi) {
		super();
		this.idTransaksi = idTransaksi;
		this.idMember = idMember;
		this.tanggalTransaksi = tanggalTransaksi;
	}
	public String getIdTransaksi() {
		return idTransaksi;
	}
	public void setIdTransaksi(String idTransaksi) {
		this.idTransaksi = idTransaksi;
	}
	public String getIdMember() {
		return idMember;
	}
	public void setIdMember(String idMember) {
		this.idMember = idMember;
	}
	public String getTanggalTransaksi() {
		return tanggalTransaksi;
	}
	public void setTanggalTransaksi(String tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}
	
	
}
