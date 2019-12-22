package com.stepan.kursov.Controllers;

import com.stepan.kursov.Model.CPU;
import com.stepan.kursov.Repository.CRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CPUController {

    @Autowired
    private CRUDRepository crudRepository;

    @GetMapping("/cpu")
    public String main_cpu(Model model) {
        return "cpu";
    }

    // генерация БД
    private void fillAnDB() {
        List<String> listManufacturer = new ArrayList<>();
        //listManufacturer.add("AMD");
        listManufacturer.add("Intel");

        List<String> listSeries = new ArrayList<>();
        //listSeries.add("A10");
        //listSeries.add("A6");
        //listSeries.add("A8");
        //listSeries.add("Athlon");
        //listSeries.add("Athlon II X4");
        //listSeries.add("Athlon X4");
        listSeries.add("Celeron");
        listSeries.add("Core i3");
        listSeries.add("Core i5");
        listSeries.add("Core i7");
        listSeries.add("Core i9");
        //listSeries.add("FX");
        //listSeries.add("Pentium Dual-Core");
        //listSeries.add("Pentium Gold");
        //listSeries.add("Ryzen 3");
        //listSeries.add("Ryzen 5");
        //listSeries.add("Ryzen 7");
        //listSeries.add("Ryzen 9");
        //listSeries.add("Ryzen Threadripper");

        List<String> listCore = new ArrayList<>();
        listCore.add("Carrizo");
        listCore.add("Coffee Lake");
        listCore.add("Excavator");
        listCore.add("Excavator");
        listCore.add("Bristol Ridge");
        listCore.add("Excavator Carrizo");
        listCore.add("Kaby Lake");
        listCore.add("Matisse");
        listCore.add("Picasso");
        listCore.add("Piledriver Volan Vishera");
        listCore.add("Pinnacle Ridge");
        listCore.add("Raven Ridge");
        listCore.add("Skylake-S Skylake");
        listCore.add("Steamroller Kaveri");
        listCore.add("Summit Ridge");
        listCore.add("Threadripper");

        List<String> listSocket = new ArrayList<>();
        listSocket.add("LGA 1151");
        listSocket.add("LGA 1151v2");
        //listSocket.add("SocketAM3+");
        //listSocket.add("SocketAM4");
        //listSocket.add("SocketFM2+");
        //listSocket.add("TR4");

        List<Integer> listCount_Core = new ArrayList<>();
        listCount_Core.add(2);
        listCount_Core.add(4);
        listCount_Core.add(6);
        listCount_Core.add(8);
        listCount_Core.add(12);
        listCount_Core.add(16);
        listCount_Core.add(24);

        List<String> listFrequency = new ArrayList<>();
        listFrequency.add("1,5");
        listFrequency.add("2");
        listFrequency.add("2,5");
        listFrequency.add("3");
        listFrequency.add("3.5");
        listFrequency.add("4");

        List<Boolean> listInserted_GPU = new ArrayList<>();
        listInserted_GPU.add(true);
        listInserted_GPU.add(false);


        for (String lM : listManufacturer) {
            for (String lS : listSeries) {
                for (String lC : listCore) {
                    for (String lSc : listSocket) {
                        for (Integer lCC : listCount_Core) {
                            for (String lF : listFrequency) {
                                for (Boolean lIG : listInserted_GPU) {
                                    crudRepository.save(new CPU(lM, lS, lC, lSc, lCC, lF, lIG));
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Загрузили объект в БД");
    }

    @PostMapping(value = "/cpu")
    public String filterCPU(@RequestParam String manufacturer, @RequestParam String series,
                            @RequestParam String core, @RequestParam String socket,
                            @RequestParam String count_core, @RequestParam String frequency,
                            @RequestParam String inserted_GPU, Model model) {


        //fillAnDB();

        List<String> requestStrs = new LinkedList<String>();

        if (!manufacturer.equals("")) { requestStrs.add(String.format(" manufacturer like \"%s\"", manufacturer)); }

        if (!series.equals("")) { requestStrs.add(String.format(" series like \"%s\"", series)); }

        if (!core.equals("")) { requestStrs.add(String.format(" core like \"%s\"", core)); }

        if (!socket.equals("")) { requestStrs.add(String.format(" socket like \"%s\"", socket)); }

        try {
            Integer int_count_core = Integer.parseInt(count_core);
            requestStrs.add(String.format(" count_core = %d", int_count_core));
        } catch (Exception ex) { }


        try {
            Double doub_frequency = Double.parseDouble(frequency);
            requestStrs.add(String.format(" frequency like \"%s\"", frequency));
        } catch (Exception ex) { }

        if (!inserted_GPU.equals("")) { requestStrs.add(String.format(" inserted_GPU like \"%s\"", inserted_GPU)); }

        String resRequest = "where";

        for(int i = 0; i < requestStrs.size() - 1; i++) {
            resRequest = resRequest + requestStrs.get(i) + " and";
        }
        if (requestStrs.size() > 0)
            resRequest = resRequest + requestStrs.get(requestStrs.size() - 1);

        System.out.println(resRequest);

        List<CPU> list = crudRepository.hardRequest("SELECT c from CPU c");

        model.addAttribute("list", list);
        model.addAttribute("request", resRequest);

        return "cpu";
    }
}
