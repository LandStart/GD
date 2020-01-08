package com.dong.start;


public class GexfGraphFactory {

    public static void CreateStaticGexfFile(StaticGexfGraph staticGexfGraph,String path){
         staticGexfGraph.staticGexfGraph(path);
    }

    public static void CreateDynamicGexfFile(DynamicGexfGraph dynamicGexfGraph,String path){
        System.out.println("start create dynamic graph");
        dynamicGexfGraph.dynamicGexfGraph(path);
    }
}
