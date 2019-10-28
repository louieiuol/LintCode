public class SymbolTable<T> {
	private TableEntry<String,T>[] hashtable;
	//create a inner table entry instance
	private int num;
	//size of table
	class TableEntry<K,V> {
		private K key;
		private V value;
		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() { 
			return key; 
		}
		public V getValue() { 
			return value;
		}
		public String toString() {
			return key.toString()+":"+value.toString(); 
		}
	}
	//provided class

	@SuppressWarnings("unchecked")
	public SymbolTable() {
		this.hashtable=new TableEntry[2];
		//create new size two hash table
		for(int i=0; i<this.hashtable.length ; i++) {
			this.hashtable[i]=new TableEntry<String, T>(null, null);
		}
		//Initialize all cells in hash table
		this.num=0;
		//set size as 0
	}

	public int getCapacity() {
		return this.hashtable.length;
	}
	//return hash table capacity

	public int size() {
		return this.num;
	}
	//return hash table size

	public void put(String k, T v) {
		if(k != null && v!=null) {
			if(this.get(k) != null) {
				//check k existed in table
				for(int i=hashing(k); this.hashtable[i]!=null ; i= (i+1) % this.hashtable.length) {
					//traversal through all cells in array
					if(this.hashtable[i].getKey().equals(k)) {
						//find target replace the element with new v
						this.hashtable[i].value=v;
						return;
					}
				}
			}else {
				if((this.num+1) > 0.8*(this.hashtable.length)) {
					rehash(this.hashtable.length*2);
					//if add a new element added greater than 80% of total capacity
				}	
				for(int i=hashing(k); this.hashtable[i]!=null ; i= (i+1) % this.hashtable.length) {
					//traversal through all cells in the array find the next available cell 
					if((this.hashtable[i].getKey()==null) || this.hashtable[i].getKey().equals("#")) {
						this.hashtable[i].key=k;
						this.hashtable[i].value=v;
						this.num++;
						return;
					}
				}
			}
		}
	}

	public T remove (String k) {
		if(k !=null) {
			if(this.get(k)!=null) {
				//check table contains the element
				for(int i=this.hashing(k); this.hashtable[i].getKey()!=null; i=(i+1) % this.hashtable.length) {
					//traversal all available cells through array
					if(this.hashtable[i].getKey().equals(k)) {
						//set the value as # to mark the remove
						this.hashtable[i].key="#";
						T ele=this.hashtable[i].value;
						this.hashtable[i].value=null;
						//decrease the size and return target
						this.num--;
						return ele;
					}
				}
			}
		}
		return null;
	}

	public T get(String k) {
		if(k != null) {
			for(int i=0; i<this.getCapacity(); i++) {
				//traversal though the array and find the available cell
				if(this.hashtable[i].getKey()!=null) {
					if(this.hashtable[i].getKey().equals(k)) {
						//return the target
						return this.hashtable[i].getValue();
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		this.num=0;
		this.hashtable=(TableEntry<String, T>[]) new TableEntry[2];
		//reset the size and create new table
		for(int i=0; i<this.hashtable.length ; i++) {
			this.hashtable[i]=new TableEntry<String, T>(null, null);
		}
		//initialize all cells in new table
	}

	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		if(this.num < size) {
			//check size is valid
			SymbolTable<T> newtable=new SymbolTable<T>();
			newtable.hashtable=new TableEntry[size];
			//create a new table with new size
			//traversal through all new array cells
			for(int i=0; i<size; i++) {
				//use table entry constructor to fill in cells with new table entry
				newtable.hashtable[i]=new TableEntry<String, T>(null, null);
			}
			for(int i=0; i<this.hashtable.length; i++) {
				//put all previous elements into new table 
				if(this.hashtable[i]!=null) {
					newtable.put(this.hashtable[i].getKey(), this.hashtable[i].getValue());
				}
			}
			this.hashtable=newtable.hashtable;
			//set new table as our table
			return true;
		}
		return false;
	}

	private int hashing(String key) {
		return (key.hashCode() & 0x7fffffff) % this.hashtable.length;
		//create a hash code with unique positive number
	}
}
