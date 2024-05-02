package vn.hdi.spring.caculater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private Caculator myCaculator;

    @Autowired
    public Controller(Caculator myCaculator){
        this.myCaculator=myCaculator;
    }
    @GetMapping("/canbachai")
    public String tinhCanBacHai(@RequestParam("value") double variable) {
        return "SQRT (" +variable +") = " + myCaculator.canBacHai(variable);
    }

    @GetMapping("/binhphuong")
    public String tinhBinhPhuong(@RequestParam("value") double variable) {
        return "BINH PHUONG (" +variable +") = " + myCaculator.binhPhuong(variable);
    }
}
