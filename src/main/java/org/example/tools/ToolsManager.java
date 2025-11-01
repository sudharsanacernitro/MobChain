package org.example.tools;


import org.example.tools.OwnTools.Tool;

import java.util.Collection;
import java.util.HashMap;

//=============[ TOOLS ]=====================
import org.example.tools.OwnTools.RAGTool;


public class ToolsManager {

   public  static HashMap< String , Tool > toolsRegistry = new HashMap<>();

   private static ToolsManager instance;

   static {

       toolsRegistry.put( "rag_tool" , new RAGTool() );

   }


   private ToolsManager( ) {

   }

   public static ToolsManager getInstance( ) {

       if( instance == null ) instance = new ToolsManager();

       return instance;

   }




   public HashMap<String , Tool> getToolsRegistry( ) {

       return new HashMap<>( toolsRegistry );

   }


   public Collection<Tool> getToolsArray( ) {

       return toolsRegistry.values();

   }


   public static void addTools( String functionName , Tool newTool ) {

       toolsRegistry.put( functionName , newTool );

   }



}
