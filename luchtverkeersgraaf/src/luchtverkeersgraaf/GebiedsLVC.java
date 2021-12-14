package luchtverkeersgraaf;

public class GebiedsLVC extends LVC {
	
	/**
	 * @post | getVliegtuigen().isEmpty()
	 */
	public GebiedsLVC() {}
	
	/**
	 * @throws IllegalArgumentException | vliegtuig == null
	 * @inspects |
	 * @post | !result
	 */
	@Override
	public boolean getMagLanden(Vliegtuig vliegtuig) {
		if (vliegtuig == null)
			throw new IllegalArgumentException("`vliegtuig` is null");
		return false;
	}
	
	/**
	 * @pre | lvc != null
	 * @inspects |
	 * @post | result == (lvc instanceof GebiedsLVC)
	 */
	@Override
	public boolean isGelijkaardigAan(LVC lvc) {
		return lvc instanceof GebiedsLVC;
	}

}
