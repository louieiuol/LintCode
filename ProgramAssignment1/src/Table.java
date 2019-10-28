

public interface Table {
		public boolean insert(String key, String value);
		public boolean sendMessage(String key, String msg) ;
		public String lookUp(String key);
		public boolean deleteContact(String key);
		public boolean update(String key, String newValue);
		public boolean markToStart();
		public boolean advanceMark();
		public String keyAtMark();
		public String valueAtMark();
		public int displayAll();
		public boolean deleteMessage(String key);
		public boolean DisplayAllMessage(String key);
}
