package com.microservice.cards.controller;

import com.microservice.cards.constants.CardsConstants;
import com.microservice.cards.dto.CardsContactInfoDto;
import com.microservice.cards.dto.CardsDto;
import com.microservice.cards.dto.ResponseDto;
import com.microservice.cards.entity.Cards;
import com.microservice.cards.service.CardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
@RequestMapping("/cards")
public class CardsController {
    @Autowired
private CardsService cardsService;

    @Autowired
    private Environment environment;
    @Autowired
    private CardsContactInfoDto cardsContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;
    @PostMapping("/createCard")
    public ResponseEntity<ResponseDto>createCards(@RequestParam @Valid  @Pattern(regexp = "\\d{10}", message = "mobileNumber Must be a 10-digit number") String  mobileNumber)
    {
cardsService.createCard(mobileNumber);
return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201,"Data created Successfully"));


    }

    @GetMapping("/fetchCard")
    public ResponseEntity<Cards>fetchCardByMobileNumber(@RequestParam("mobile") @Pattern(regexp = "\\d{10}", message = "Must be a 10-digit number") String mobile)
    {
        Cards cards = cardsService.findByMobileNo(mobile);
        return ResponseEntity.status(HttpStatus.OK).body(cards);

    }

    @PutMapping("/updateCard")
    public ResponseEntity<CardsDto>updateAccount(@Valid @RequestBody CardsDto cardsDto)
    {
        CardsDto cardsDtonew = cardsService.updateAccount(cardsDto);
        return  ResponseEntity.status(HttpStatus.OK).body(cardsDtonew);
    }

    @DeleteMapping("/deleteCard/{cardId}")

    public ResponseEntity<String>deleteCards(@PathVariable("cardId")Long cardId)
    {
        cardsService.deleteCard(cardId);
        return ResponseEntity.status(HttpStatus.OK).body("The Customer with Id"+cardId+"has been deleted ");
    }

    @GetMapping("/build-Version")

    public ResponseEntity<String>getBuildVersion()
    {
        return  ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/environment")

    public ResponseEntity<String>getEnvironmentVersion()
    {
        return  ResponseEntity.ok(environment.getProperty("Maven_Home"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto>getContactInfo()
    {
        return ResponseEntity.ok(cardsContactInfoDto);
    }


}
