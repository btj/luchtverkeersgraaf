package luchtverkeersgraaf.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import luchtverkeersgraaf.GebiedsLVC;
import luchtverkeersgraaf.LuchthavenLVC;
import luchtverkeersgraaf.Vliegtuig;

class LuchtverkeersgraafTest {

	@Test
	void test() {
		LuchthavenLVC zaventem = new LuchthavenLVC(40);
		assertEquals(Set.of(), zaventem.getVliegtuigen());
		assertEquals(40, zaventem.getAantalGates());
		
		GebiedsLVC noordzee = new GebiedsLVC();
		assertEquals(Set.of(), noordzee.getVliegtuigen());
		
		Vliegtuig sn100 = new Vliegtuig(zaventem);
		assertEquals(zaventem, sn100.getLVC());
		assertEquals(0, sn100.getSnelheid());
		assertEquals(Set.of(sn100), zaventem.getVliegtuigen());
		
		sn100.setSnelheid(500);
		assertEquals(500, sn100.getSnelheid());
		
		Vliegtuig ba100 = new Vliegtuig(zaventem);
		assertEquals(Set.of(sn100, ba100), zaventem.getVliegtuigen());
		
		ba100.setSnelheid(400);
		
		sn100.draagOverAan(noordzee);
		assertEquals(noordzee, sn100.getLVC());
		assertEquals(Set.of(sn100), noordzee.getVliegtuigen());
		assertEquals(Set.of(ba100), zaventem.getVliegtuigen());
		
		ba100.draagOverAan(noordzee);
		assertEquals(noordzee, ba100.getLVC());
		assertEquals(Set.of(sn100, ba100), noordzee.getVliegtuigen());
		assertEquals(Set.of(), zaventem.getVliegtuigen());
		
		assertFalse(noordzee.getMagLanden(ba100));
		assertFalse(zaventem.getMagLanden(ba100));
		ba100.setSnelheid(200);
		assertFalse(noordzee.getMagLanden(ba100));
		assertTrue(zaventem.getMagLanden(ba100));
		
		LuchthavenLVC zurich = new LuchthavenLVC(40);
		LuchthavenLVC heathrow = new LuchthavenLVC(100);
		assertTrue(zaventem.isGelijkaardigAan(zurich));
		assertFalse(zaventem.isGelijkaardigAan(heathrow));
		assertFalse(zaventem.isGelijkaardigAan(noordzee));
		
		GebiedsLVC alpen = new GebiedsLVC();
		assertTrue(alpen.isGelijkaardigAan(noordzee));
		assertFalse(alpen.isGelijkaardigAan(zaventem));
		
		Vliegtuig lh100 = new Vliegtuig(heathrow);
		lh100.setSnelheid(450);
		lh100.draagOverAan(noordzee);
		
		Set<Vliegtuig> verwachteVliegtuigen = Set.of(sn100, lh100);
		Set<Vliegtuig> vliegtuigen = new HashSet<>();
		for (Iterator<Vliegtuig> i = noordzee.getSnelleVliegtuigenIterator(); i.hasNext(); ) {
			Vliegtuig v = i.next();
			assertFalse(vliegtuigen.contains(v));
			assertTrue(verwachteVliegtuigen.contains(v));
			vliegtuigen.add(v);
		}
		assertEquals(verwachteVliegtuigen, vliegtuigen);
		
		vliegtuigen.clear();
		noordzee.forEachSnelVliegtuig(v -> {
			assertFalse(vliegtuigen.contains(v));
			assertTrue(verwachteVliegtuigen.contains(v));
			vliegtuigen.add(v);
		});
		assertEquals(verwachteVliegtuigen, vliegtuigen);
		
		List<Vliegtuig> streamContents = noordzee.getSnelleVliegtuigenStream().toList();
		assertEquals(verwachteVliegtuigen.size(), streamContents.size());
		assertEquals(verwachteVliegtuigen, Set.copyOf(streamContents));
	}

}
