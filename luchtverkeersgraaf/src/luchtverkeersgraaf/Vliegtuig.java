package luchtverkeersgraaf;

import logicalcollections.LogicalSet;

/**
 * @invar | getLVC() != null
 * @invar | 0 <= getSnelheid()
 * @invar | getLVC().getVliegtuigen().contains(this)
 */
public class Vliegtuig {
	
	/**
	 * @invar | lvc != null
	 * @invar | 0 <= snelheid
	 */
	private LVC lvc;
	private int snelheid;
	
	/**
	 * @invar | getLVCInternal().getVliegtuigenInternal().contains(this)
	 * 
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	LVC getLVCInternal() { return lvc; }
	
	/**
	 * @post | 0 <= result
	 * @return
	 */
	int getSnelheidInternal() { return snelheid; }
	
	/**
	 * @peerObject
	 */
	public LVC getLVC() { return getLVCInternal(); }
	
	public int getSnelheid() { return getSnelheidInternal(); }
	
	/**
	 * @pre | lvc != null
	 * @mutates_properties | lvc.getVliegtuigen()
	 * @post | getLVC() == lvc
	 * @post | getSnelheid() == 0
	 * @post | getLVC().getVliegtuigen().equals(LogicalSet.plus(old(lvc.getVliegtuigen()), this))
	 */
	public Vliegtuig(LuchthavenLVC lvc) {
		this.lvc = lvc;
		lvc.addVliegtuig(this);
	}
	
	/**
	 * @pre | 0 <= snelheid
	 * @mutates_properties | getSnelheid()
	 * @post | getSnelheid() == snelheid
	 */
	public void setSnelheid(int snelheid) {
		this.snelheid = snelheid;
	}
	
	/**
	 * @pre | lvc != null
	 * @pre | lvc != getLVC()
	 * @mutates_properties | getLVC(), getLVC().getVliegtuigen(), lvc.getVliegtuigen()
	 * @post | getLVC() == lvc
	 * @post | old(getLVC()).getVliegtuigen().equals(LogicalSet.minus(old(getLVC().getVliegtuigen()), this))
	 * @post | getLVC().getVliegtuigen().equals(LogicalSet.plus(old(lvc.getVliegtuigen()), this))
	 */
	public void draagOverAan(LVC lvc) {
		this.lvc.removeVliegtuig(this);
		this.lvc = lvc;
		this.lvc.addVliegtuig(this);
	}
	
}
