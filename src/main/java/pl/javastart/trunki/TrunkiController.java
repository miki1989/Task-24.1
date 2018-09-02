package pl.javastart.trunki;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import java.util.List;

@Controller
public class TrunkiController {


    private TrunkiRepository trunkiRepository;

    public TrunkiController(TrunkiRepository trunkiRepository) {
        this.trunkiRepository = trunkiRepository;
    }

    @GetMapping("/home")
    public String trunks(Model model, @RequestParam(defaultValue = "id", required = false) String sort,
                                        @RequestParam(defaultValue = "asc", required = false) String order){
        //List<Trunki> trunkis = trunkiRepository.findByVolumeGreaterThan(minVolume);

        Sort.Direction direction;

        if(order.equals("asc")){
            direction = Sort.Direction.ASC;
        }
        else {
            direction = Sort.Direction.DESC;
        }

        Sort voltage = Sort.by(direction, sort);
        List<Trunki> all = trunkiRepository.findAll(voltage);
        model.addAttribute("trunkis", all);
        model.addAttribute("newAlco", new Trunki());


        String order2;

        if(order.equals("asc")){
            order2 = "desc";
        }
        else {
            order2 = "asc";
        }
        model.addAttribute("order", order2);
        //model.addAttribute("trunkis", trunkis1);
        return "home"; // resources/templates/home.html

    }

    @GetMapping("/s")
    public String trunkiWithS(Model model, @RequestParam String pattern) {
        List<Trunki> trunkiWithS = trunkiRepository.findAllByNameStartsWith("S%");
        model.addAttribute("trunkiswiths", trunkiWithS);

        return "s";
    }
    @GetMapping("/trunki")
    public String trunek(Model model, @RequestParam String name){

        Trunki trunki = trunkiRepository.findByName(name);
        model.addAttribute("trunki", trunki);
        return "trunki";

    }

    @PostMapping("/addAlcohol")
    public String addAlcohol(Trunki trunki){

        trunkiRepository.save(trunki);

        return "redirect:/home";
    }
}
