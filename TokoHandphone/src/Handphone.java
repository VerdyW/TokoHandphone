
public class Handphone {
	
		private String merekHandphone;
		private String typeHandphone;
		private String harga;
		private String stock;
		
		public Handphone(String merekHandphone, String typeHandphone, String harga, String stock) {
			super();
			this.merekHandphone = merekHandphone;
			this.typeHandphone = typeHandphone;
			this.harga = harga;
			this.stock = stock;
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

		public String getHarga() {
			return harga;
		}

		public void setHarga(String harga) {
			this.harga = harga;
		}

		public String getStock() {
			return stock;
		}

		public void setStock(String stock) {
			this.stock = stock;
		}
		
		
		
}
