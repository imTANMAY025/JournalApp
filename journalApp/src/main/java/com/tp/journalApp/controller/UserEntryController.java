package com.tp.journalApp.controller;

import com.tp.journalApp.entity.UserEntity;
import com.tp.journalApp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user-service")
public class UserEntryController {

    @Autowired
   private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserEntryController.class);

  @PostMapping()
  public ResponseEntity<?> saveUserEntity(@RequestBody UserEntity userEntity){
      try{
          if(userEntity!=null){
              UserEntity result = userService.saveUserData(userEntity);
              return new ResponseEntity<>(result, HttpStatus.OK);
          }else{
              return new ResponseEntity<>("Provide Proper Details",HttpStatus.BAD_REQUEST);
          }
      }catch (Exception e){
          logger.error(e.getMessage());
          return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping
  public ResponseEntity<?>  getAllUsers() {
      try{
          List<UserEntity> results = userService.getAllUsers();
          if(results!=null && !results.isEmpty()){
              return new ResponseEntity<>(results,HttpStatus.OK);
          }else{
              return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
          }
      }catch (Exception e){
          return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @GetMapping("/{username}")
    public ResponseEntity<?> getJournalsById(@PathVariable String username) {
        try{

            if(!String.valueOf(username).equalsIgnoreCase("")){

                UserEntity UserEntity = userService.getUsersById(username);
                Optional<UserEntity> result = Optional.ofNullable(UserEntity);

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
  public ResponseEntity<?> updateJournal(@RequestBody UserEntity userEntity, @PathVariable String username){
      try{

          if(userEntity!=null && !String.valueOf(username).equalsIgnoreCase("")){

              Optional<UserEntity> result = Optional.ofNullable(userService.updateUser(userEntity,username));

                  return new ResponseEntity<>(result,HttpStatus.OK);

          }else{
              return new ResponseEntity<>("Provide Proper Details",HttpStatus.BAD_REQUEST);
          }

      }catch (Exception e){
          return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteJournal(@PathVariable String username){
      try{

          if(!String.valueOf(username).equalsIgnoreCase("")){
              boolean result = userService.deleteUser(username);

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
