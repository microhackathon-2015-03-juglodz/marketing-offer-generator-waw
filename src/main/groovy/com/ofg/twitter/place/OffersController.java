package com.ofg.twitter.place;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofg.twitter.model.LoanDecision;
import com.ofg.twitter.model.MarketingOffer;

@RestController
@RequestMapping("/api")
public class OffersController {

	@RequestMapping(value = "/marketing/{loanApplicationId}", method = PUT)
	public String generateOfferings(@PathVariable @NotNull long loanApplicationId, @RequestBody LoanDecision loanDecision) {
		return loanDecision.getDecision();
	}

	@RequestMapping(value = "/marketing/{firstName}_{lastName}", method = GET)
	public MarketingOffer getOfferings(@PathVariable @NotNull String firstName, @PathVariable @NotNull String lastName) {
		return new MarketingOffer("New offer for " + firstName + "<>" + lastName + "!");
	}

}
