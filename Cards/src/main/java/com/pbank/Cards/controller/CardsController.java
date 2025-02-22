package com.pbank.Cards.controller;

import com.pbank.Cards.constants.CardsConstants;
import com.pbank.Cards.dto.CardsContactInfoDto;
import com.pbank.Cards.dto.CardsDto;
import com.pbank.Cards.dto.ErrorResponseDto;
import com.pbank.Cards.dto.ResponseDto;
import com.pbank.Cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {
    ICardsService iCardsService;

    public CardsController(ICardsService iCardsService) {
        this.iCardsService = iCardsService;
    }

    @Value("${build.version}")
    String buildVersion;

    @Autowired
    Environment environment;

    @Autowired
    CardsContactInfoDto cardsContactInfoDto;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardsDetails(@RequestParam
                                                          @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                          String mobileNumber){
          CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
          return ResponseEntity.ok(cardsDto);
      }



      @PutMapping("/update")
      public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
          boolean isUpdated = iCardsService.updateCard(cardsDto);
          if (isUpdated) {
              return ResponseEntity.status(HttpStatus.OK)
                      .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
          } else {

              return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                      .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
          }
      }


    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                             @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                     String mobileNumber) {
        boolean isUpdated = iCardsService.deleteCard(mobileNumber);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){

        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Get Java Version",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
 /*   @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(System.getProperty("java.version"));
    }*/
      @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion(){
          return ResponseEntity
                .status(HttpStatus.OK)
                  .body(environment.getProperty("Java_HOME"));
                //.body(environment.getSystemProperties());
    }

    @Operation(
            summary = "Get Contact Info",
            description = "Contact Info details that can be reached out in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDto);
    }


}
