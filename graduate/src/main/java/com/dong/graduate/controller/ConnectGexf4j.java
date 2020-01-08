package com.dong.graduate.controller;

import com.dong.start.DynamicGexfGraph;
import com.dong.start.GexfGraphFactory;
import it.uniroma1.dis.wsngroup.gexf4j.core.Gexf;
import it.uniroma1.dis.wsngroup.gexf4j.core.impl.GexfImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/graph")
public class ConnectGexf4j {

    @RequestMapping("/static")
    @ResponseBody
    public void DynamicGexfGraph(){
        GexfGraphFactory.CreateDynamicGexfFile(new DynamicGexfGraph(),"F:");
    }

}
