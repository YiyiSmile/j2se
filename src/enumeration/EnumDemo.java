package enumeration;

public class EnumDemo {

	public static void main(String[] args) {
		

	}

}

class Pizza {

	private PizzaStatus status;

	public enum PizzaStatus {
		ORDERED, READY, DELIVERED;
	}

	public boolean isDeliverable() {
		if (status == PizzaStatus.READY) {
			return true;
		}
		return false;
	}

	public int getDeliveryTimeInDays() {
		switch (status) {
		case ORDERED:
			return 5;
		case READY:
			return 2;
		case DELIVERED:
			return 0;
		}
		return 0;
	}

	public PizzaStatus getStatus() {
		return status;
	}

	public void setStatus(PizzaStatus status) {
		this.status = status;
	}
}
