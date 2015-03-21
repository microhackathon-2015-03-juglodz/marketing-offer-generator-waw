package com.ofg.twitter.place;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.concurrent.Callable;

import groovy.util.logging.Slf4j;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import com.mongodb.BasicDBObject;
import com.ofg.twitter.model.LoanDecision;
import com.ofg.twitter.model.MarketingOffer;

@Slf4j
@RestController
@RequestMapping("/api")
public class OffersController {

	private OffersDao offersDao = new OffersDao();

	private Logger log = LoggerFactory.getLogger(OffersController.class);
	
	private final MetricRegistry mr;
	private final Counter generatedOffersCount;
	
	@Autowired
	public OffersController(MetricRegistry metricRegistry) {
		this.mr = metricRegistry;
		this.generatedOffersCount = mr.counter("generatedOffers.count");
	}
	

	@RequestMapping(value = "/marketing/{loanApplicationId}", method = PUT)
	public void generateOfferings(@PathVariable @NotNull long loanApplicationId, @RequestBody LoanDecision loanDecision) {
		log.debug("New offer for loanApplicationId: " + loanApplicationId);
		offersDao.save(loanDecision.getPerson(), getOffer(loanDecision.getDecision()));
		log.info("Gennereted offer for loanApplicationId: " + loanApplicationId);
		generatedOffersCount.inc();
	}

	private String getOffer(String decision) {
		if (decision.equalsIgnoreCase("failure")) {
			return "Conntact your local Kruk agency first.";
		} else if (decision.equalsIgnoreCase("manual")) {
			return "We need some time to process your request. Your understanding will be appreciated.";
		} else if (decision.equalsIgnoreCase("success")) {
			return "Need more money? Now, you can have more money with our special offer!";
		}
		return "Try with Provident!";
	}

	@RequestMapping(value = "/marketing/{firstName}_{lastName}", method = GET)
	public MarketingOffer getOfferings(@PathVariable @NotNull String firstName, @PathVariable @NotNull String lastName) {
		String user = firstName + "_" + lastName;
		log.debug("Trying to get offer for user: " + user);
		try {
			return offersDao.get(user);
		} catch (Exception e) {
			return null;
		}

	}

}
