import java.util.List;

/*
code style方面他没有用camel case命名法，也没有constructor去initialize list variables，efficiency方面他没有存储item的个数，而是用for loop一个一个item加进去或者拿出来，我说可以在item里面加个count变量，也可以另存一个list表示count，还可以直接用HashMap，key是item，value是count。
面的比较晚，不知道还有没有坑位了
*/


public class Inventory {
	public class Item{
		
	}
	public List<Item> items; //in our current game design, each item is either a quest item or a normal item
	public List<Item> questItems; // for convenience, a subset of items, containing only quest items
	public List<Item> normalItems; // for convenience, a subset of items, containing only normal items
	
	private Item lastItemCollected;
	public Item LastItemCollected() {
		return lastItemCollected;
	}
	public void getitem(Item i, int quantity) {
		for(int x=0; x<quantity; x++) {
			items.add(i);
			if(i.IsQuestItem()) {
				questItems.add(i);
			}else {
				normalItems.add(i);
			}
		}
		AchievementSystem.instance.DidModifyItem("gain", i.identifier, quantity);
		didpickupitem(i);
	}
	
	public void loseitem(Item i, int quantity) {
		for(int x=0; x<quantity; x++) {
			items.remove(i);
			if(i.IsQuestItem()) {
				questItems.remove(i);
			}else {
				normalItems.remove(i);
			}
		}
		AchievementSystem.instance.DidModifyItem("lose", i.identifier, quantity);
	}
	
	public void didpickupitem(Item i) {
		lastItemCollected = i;
	}
	
}
