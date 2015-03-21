package com.ofg.twitter.place;

import java.util.HashMap;
import java.util.Map;

import com.ofg.twitter.model.MarketingOffer;
import com.ofg.twitter.model.Person;


public class OffersDao {

	private static final Map<String,MarketingOffer> offers = new HashMap<String,MarketingOffer>();
	
	public MarketingOffer save(Person p, String newOffer){
		return offers.put(p.getId(), new MarketingOffer(newOffer));
	}
	
	
	public MarketingOffer get(String userId){
		return offers.get(userId);
	}
	
	
}
