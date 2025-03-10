package hailingservice.domain;

import hailingservice.domain.DriverRegistered;
import hailingservice.DriverApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name="Driver_table")
@Data

//<<< DDD / Aggregate Root
public class Driver  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    private Long id;
    
    
    
    
    private String email;
    
    
    
    
    private String driverLicenseNumber;
    
    
    
    
    private Boolean isApproved;
    
    
    
    
    private Boolean isHailing;
    
    
    
    
    private String driverLocation;
    
    
    
    
    private String operationRequestForm;
    
    
    
    
    private String operationInfo;
    
    
    
    
    private Long operationRequestId;

    @PostPersist
    public void onPostPersist(){


        DriverRegistered driverRegistered = new DriverRegistered(this);
        driverRegistered.publishAfterCommit();

    
    }

    public static DriverRepository repository(){
        DriverRepository driverRepository = DriverApplication.applicationContext.getBean(DriverRepository.class);
        return driverRepository;
    }



//<<< Clean Arch / Port Method
    public void confirmLicense(){
        
        //implement business logic here:
        


        DriverApproved driverApproved = new DriverApproved(this);
        driverApproved.publishAfterCommit();
        DriverDisapproved driverDisapproved = new DriverDisapproved(this);
        driverDisapproved.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void acceptCarhailing(){
        
        //implement business logic here:
        

        hailingservice.external.DriverQuery driverQuery = new hailingservice.external.DriverQuery();
        // driverQuery.set??()        
          = DriverApplication.applicationContext
            .getBean(hailingservice.external.Service.class)
            .driver(driverQuery);

        HailingAccepted hailingAccepted = new HailingAccepted(this);
        hailingAccepted.publishAfterCommit();
        HailingRejected hailingRejected = new HailingRejected(this);
        hailingRejected.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void changeOperationstatus(ChangeOperationstatusCommand changeOperationstatusCommand){
        
        //implement business logic here:
        


        OperationStatusChanged operationStatusChanged = new OperationStatusChanged(this);
        operationStatusChanged.publishAfterCommit();
    }
//>>> Clean Arch / Port Method

//<<< Clean Arch / Port Method
    public static void servePassengerBoardingLocation(ReservationCreated reservationCreated){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Driver driver = new Driver();
        repository().save(driver);

        */

        /** Example 2:  finding and process
        

        repository().findById(reservationCreated.get???()).ifPresent(driver->{
            
            driver // do something
            repository().save(driver);


         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void hailDriver(GpsBasedLocationConfirmed gpsBasedLocationConfirmed){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Driver driver = new Driver();
        repository().save(driver);

        */

        /** Example 2:  finding and process
        
        // if gpsBasedLocationConfirmed.tmapIddriverIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> matchingMap = mapper.convertValue(gpsBasedLocationConfirmed.getTmapId(), Map.class);
        // Map<Long, Object> matchingMap = mapper.convertValue(gpsBasedLocationConfirmed.getDriverId(), Map.class);
        // Map<Long, Object> matchingMap = mapper.convertValue(gpsBasedLocationConfirmed.getUserId(), Map.class);

        repository().findById(gpsBasedLocationConfirmed.get???()).ifPresent(driver->{
            
            driver // do something
            repository().save(driver);


         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void serveDestination(DestinationCalculated destinationCalculated){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Driver driver = new Driver();
        repository().save(driver);

        */

        /** Example 2:  finding and process
        
        // if destinationCalculated.tmapIddriverIduserId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> matchingMap = mapper.convertValue(destinationCalculated.getTmapId(), Map.class);
        // Map<Long, Object> matchingMap = mapper.convertValue(destinationCalculated.getDriverId(), Map.class);
        // Map<Long, Object> matchingMap = mapper.convertValue(destinationCalculated.getUserId(), Map.class);

        repository().findById(destinationCalculated.get???()).ifPresent(driver->{
            
            driver // do something
            repository().save(driver);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
