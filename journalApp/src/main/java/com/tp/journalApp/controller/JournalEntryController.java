package com.tp.journalApp.controller;

import com.tp.journalApp.entity.JournalEntity;
import com.tp.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
   private JournalService journalService;

    Logger logger = LoggerFactory.getLogger(JournalEntryController.class);

  @PostMapping("/{id}")
  public ResponseEntity<?> saveJournalEntity(@RequestBody JournalEntity journalEntity, @PathVariable String id){
      try{
          if(journalEntity!=null){
              logger.info(journalEntity.toString());
              JournalEntity result = journalService.saveJournalData(journalEntity,id);
              return new ResponseEntity<>(result, HttpStatus.OK);
          }else{
              return new ResponseEntity<>("Provide Proper Details",HttpStatus.BAD_REQUEST);
          }
      }catch (Exception e){
          logger.error(e.getMessage());
          return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping
  public ResponseEntity<?>  getAllJournals() {
      try{
          List<JournalEntity> results = journalService.getAllJournals();
          logger.info("result:{}", results);
          if(results!=null && !results.isEmpty()){
              return new ResponseEntity<>(results,HttpStatus.OK);
          }else{
              return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
          }
      }catch (Exception e){
          return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJournalsById(@PathVariable ObjectId id) {
        try{

            if(!String.valueOf(id).equalsIgnoreCase("")){

                JournalEntity journalEntity = journalService.getJournalById(id);
                Optional<JournalEntity> result = Optional.ofNullable(journalEntity);

                if(result.isPresent()){
                    return new ResponseEntity<>(result,HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Journal Details Not Found for this Id",HttpStatus.NOT_FOUND);
                }

            }else{
                return new ResponseEntity<>("Provide Proper Id",HttpStatus.BAD_REQUEST);
            }


        }catch (Exception e){
            return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateJournal(@RequestBody JournalEntity journalEntity, @PathVariable ObjectId id){
      try{

          if(journalEntity!=null && !String.valueOf(id).equalsIgnoreCase("")){

              Optional<JournalEntity> result = Optional.ofNullable(journalService.updateJournals(journalEntity,id));

                  return new ResponseEntity<>(result,HttpStatus.OK);

          }else{
              return new ResponseEntity<>("Provide Proper Details",HttpStatus.BAD_REQUEST);
          }

      }catch (Exception e){
          return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteJournal(@PathVariable String id){
      try{

          if(!String.valueOf(id).equalsIgnoreCase("")){
              boolean result = journalService.deleteJournal(id);

              if(result){
                  return new ResponseEntity<>("Journal Entry Deleted successfully",HttpStatus.OK);
              }else {
                  return new ResponseEntity<>("Failed To Delete the Entry",HttpStatus.INTERNAL_SERVER_ERROR);
              }

          }else{
              return new ResponseEntity<>("Provide Proper Id",HttpStatus.BAD_REQUEST);
          }

      }catch (Exception e){
          return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}
