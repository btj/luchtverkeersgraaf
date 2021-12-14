package luchtverkeersgraaf;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @invar | getVliegtuigen() != null
 * @invar | getVliegtuigen().stream().allMatch(v -> v != null && v.getLVC() == this)
 */
public abstract class LVC {
	
	/**
	 * @invar | vliegtuigen != null
	 * @invar | vliegtuigen.stream().allMatch(v -> v != null && v.lvc == this)
	 * 
	 * @representationObject
	 * @peerObjects
	 */
	Set<Vliegtuig> vliegtuigen = new HashSet<>();

	/**
	 * @creates | result
	 * @peerObjects
	 */
	public Set<Vliegtuig> getVliegtuigen() { return Set.copyOf(vliegtuigen); }
	
	/**
	 * @pre | vliegtuig != null
	 * @inspects | this, vliegtuig
	 * @post | vliegtuig.getSnelheid() < 300 || !result
	 */
	public abstract boolean getMagLanden(Vliegtuig vliegtuig);
	
	/**
	 * @pre | lvc != null
	 * @inspects | this, lvc
	 * @post | lvc != this || result
	 */
	public abstract boolean isGelijkaardigAan(LVC lvc);
	
	public Iterator<Vliegtuig> getSnelleVliegtuigenIterator() {
		return new Iterator<>() {
			Iterator<Vliegtuig> iterator = vliegtuigen.iterator();
			Vliegtuig nextSnelVliegtuig;
			private void advance() {
				for (;;) {
					nextSnelVliegtuig = null;
					if (!iterator.hasNext())
						break;
					Vliegtuig v = iterator.next();
					if (300 <= v.getSnelheid()) {
						nextSnelVliegtuig = v;
						break;
					}
				}
			}
			{ advance(); } // initializer block; runs at construction time
			@Override
			public boolean hasNext() {
				return nextSnelVliegtuig != null;
			}
			@Override
			public Vliegtuig next() {
				Vliegtuig result = nextSnelVliegtuig;
				advance();
				return result;
			}
		};
	}
	
	public void forEachSnelVliegtuig(Consumer<? super Vliegtuig> consumer) {
		for (Vliegtuig vliegtuig : vliegtuigen)
			if (300 <= vliegtuig.getSnelheid())
				consumer.accept(vliegtuig);
	}
	
	public Stream<Vliegtuig> getSnelleVliegtuigenStream() {
		return vliegtuigen.stream().filter(v -> 300 <= v.getSnelheid());
	}

}
