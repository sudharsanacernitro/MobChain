package org.example.tools;


import org.example.tools.OwnTools.Tool;

import java.util.Collection;
import java.util.HashMap;

//=============[ TOOLS ]=====================
import org.example.tools.OwnTools.RAGTool;


public class ToolsManager {

   public  HashMap< String , Tool > toolsRegistry = new HashMap<>();



   public ToolsManager( ) {

   }



   public HashMap<String , Tool> getToolsRegistry( ) {

       return new HashMap<>( toolsRegistry );

   }


   public Collection<Tool> getToolsArray( ) {

       return toolsRegistry.values();

   }


   public void addTools( String functionName , Tool newTool ) {

       toolsRegistry.put( functionName , newTool );

   }



}
