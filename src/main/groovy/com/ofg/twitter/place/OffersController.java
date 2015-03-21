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

import com.mongodb.BasicDBObject;
import com.ofg.twitter.model.LoanDecision;
import com.ofg.twitter.model.MarketingOffer;

@Slf4j
@RestController
@RequestMapping("/api")
public class OffersController {

	private OffersDao offersDao = new OffersDao();

	private Logger log = LoggerFactory.getLogger(OffersController.class);

	@RequestMapping(value = "/marketing/{loanApplicationId}", method = PUT)
	public MarketingOffer generateOfferings(@PathVariable @NotNull long loanApplicationId, @RequestBody LoanDecision loanDecision) {
		log.debug("New offer for loanApplicationId: " + loanApplicationId);
		MarketingOffer mo = offersDao.save(loanDecision.getPerson(), "Oddawaj kasę!");
		log.info("Gennereted offer for loanApplicationId: " + loanApplicationId);
		return mo;

	}

	@RequestMapping(value = "/marketing/{firstName}_{lastName}", method = GET)
	public MarketingOffer getOfferings(@PathVariable @NotNull String firstName, @PathVariable @NotNull String lastName) {
		try {
			return offersDao.get(firstName + "_" + lastName);
		} catch (Exception e) {
			return null;
		}

	}

}
