
public class DetailTransaksi {
		private String idTransaksi;
		private String idHandphone;
		private String quantity;
		private String harga;
		
		public DetailTransaksi(String idTransaksi, String idHandphone, String quantity, String harga) {
			super();
			this.idTransaksi = idTransaksi;
			this.idHandphone = idHandphone;
			this.quantity = quantity;
			this.harga = harga;
		}
		
		public String getIdTransaksi() {
			return idTransaksi;
		}
		public void setIdTransaksi(String idTransaksi) {
			this.idTransaksi = idTransaksi;
		}
		public String getIdHandphone() {
			return idHandphone;
		}
		public void setIdHandphone(String idHandphone) {
			this.idHandphone = idHandphone;
		}
		public String getQuantity() {
			return quantity;
		}
		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}
		public String getHarga() {
			return harga;
		}
		public void setHarga(String harga) {
			this.harga = harga;
		}
		
		
}
