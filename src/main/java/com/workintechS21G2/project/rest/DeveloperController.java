package com.workintechS21G2.project.rest;

import com.workintechS21G2.project.Developer.Developer;
import com.workintechS21G2.project.Developer.DeveloperFactory;
import com.workintechS21G2.project.mapping.DeveloperResponse;
import com.workintechS21G2.project.tax.DeveloperTax;
import com.workintechS21G2.project.tax.Taxable;
import com.workintechS21G2.project.validation.DeveloperValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    @Value("${programmer.name}")
    private String name;

    @Value("${programmer.surname}")
    private String surname;

    @GetMapping("/deneme")
    public String welcome() {
        return name + " - " + surname + ", Hoşgeldin";
    }

    private Map<Integer, Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    };

   @Autowired
    public DeveloperController(@Qualifier("developerTax")Taxable taxable) {
        this.taxable = taxable;
    }

    @GetMapping("/")
    public List<Developer> get(){
        return developers.values().stream().toList();
    }
    @GetMapping("/{id}")
    public DeveloperResponse get(@PathVariable int id){
        if(!DeveloperValidation.isIdValid(id)){
            return new DeveloperResponse(null, "Please enter a valid ID", 400);
        }
        if(!developers.containsKey(id)){
            return new DeveloperResponse(null,
                    "Developer with given id doesn't exist: " + id, 400);
        }
        return new DeveloperResponse(developers.get(id), "Success", 200);
    }
    @PostMapping("/")
    public DeveloperResponse save(@RequestBody Developer developer){
        Developer savedDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        if(savedDeveloper == null){
            return new DeveloperResponse(null,
                    "Developer with given experience is not valid", 400);
        }
        if(developers.containsKey(developer.getId())){
            return new DeveloperResponse(null,
                    "Developer with given id already exists: " + developer.getId(), 400);
        }
        if(!DeveloperValidation.isDeveloperValid(developer)){
            return new DeveloperResponse(null,
                    "Developer credentials are not valid", 400);
        }
        developers.put(developer.getId(), savedDeveloper);
        return new DeveloperResponse(developers.get(developer.getId()), "Succes", 201);
    };

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable int id,@RequestBody Developer developer){
        if(!developers.containsKey(id)){
            return new DeveloperResponse(null,
                    "Developer with given id does not exist: " + id, 400);
        }
        developer.setId(id);
        Developer updatedDeveloper = DeveloperFactory.createDeveloper(developer, taxable);

        if(updatedDeveloper == null){
            return new DeveloperResponse(null,
                    "Developer with given experience is not valid", 400);
        }
        if(!DeveloperValidation.isDeveloperValid(developer)){
            return new DeveloperResponse(null,
                    "Developer credentials are not valid", 400);
        }

        developers.put(id, updatedDeveloper);
        return new DeveloperResponse(developers.get(id), "Success", 200);
    };
    @DeleteMapping("/{id}")
    public DeveloperResponse delete(@PathVariable int id){
        if(!developers.containsKey(id)){
            return new DeveloperResponse(null,
                    "Developer with given id is not exist:" +id, 400);
        }
        Developer developer = developers.get(id);
        developers.remove(id);
        return new DeveloperResponse(developer, "Success", 200);
    }


}
