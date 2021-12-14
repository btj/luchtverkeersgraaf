package luchtverkeersgraaf;

/**
 * @invar | 1 <= getAantalGates()
 */
public class LuchthavenLVC extends LVC {
	
	/**
	 * @invar | 1 <= aantalGates
	 */
	private int aantalGates;
	
	int getAantalGatesInternal() { return aantalGates; }
	
	public int getAantalGates() { return getAantalGatesInternal(); }
	
	/**
	 * @pre | 1 <= aantalGates
	 * @post | getVliegtuigen().isEmpty()
	 * @post | getAantalGates() == aantalGates
	 */
	public LuchthavenLVC(int aantalGates) {
		this.aantalGates = aantalGates;
	}
	
	/**
	 * @pre | vliegtuig != null
	 * @inspects | vliegtuig
	 * @post | result == (vliegtuig.getSnelheid() < 300)
	 */
	@Override
	public boolean getMagLanden(Vliegtuig vliegtuig) {
		return vliegtuig.getSnelheid() < 300;
	}
	
	/**
	 * @pre | lvc != null
	 * @inspects | this, lvc
	 * @post | result == (lvc instanceof LuchthavenLVC llvc && getAantalGates() == llvc.getAantalGates())
	 */
	public boolean isGelijkaardigAan(LVC lvc) {
		return lvc instanceof LuchthavenLVC llvc && aantalGates == llvc.aantalGates;
	}


}
