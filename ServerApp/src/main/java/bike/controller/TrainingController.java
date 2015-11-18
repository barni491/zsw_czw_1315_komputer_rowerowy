
package bike.controller;

import bike.model.Training;
import bike.model.TrainingDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {
    
    @Autowired
    private TrainingDao trainingDao;
    
     
    @RequestMapping(value = "/training/add",consumes="application/json", method = RequestMethod.POST)
    public String createTraining(@RequestBody Training training)
    {
       try{ 
        trainingDao.save(training);
        return "User added";
       }
       catch (Exception ex){
           return "Error creating User";
       }  
    }
    
    
    @RequestMapping(value = "/training/read", method = RequestMethod.GET)
    public @ResponseBody Training getTraining(long id)
    {   
        System.out.println("Hello CORS");
       Training training = null;
       try{ 
    	   training =  trainingDao.findOne(id);
       // return "User added";
       }
       catch (Exception ex){
        //   return "Error creating User";
       }
       return training;
    }
    
    
    
    
}
