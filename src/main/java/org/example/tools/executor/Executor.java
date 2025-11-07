package org.example.tools.executor;

import org.example.client.Response;
import org.example.memory.InMemory;
import org.example.messages.AiMessages;
import org.example.messages.ToolMessages;
import org.example.tools.OwnTools.Tool;
import org.example.tools.ToolsManager;
import org.json.JSONObject;

import java.util.List;

public class Executor {


    public static void execute(Response res , ToolsManager toolsManager , InMemory memory ) {


        List< Response.Function > functionList = res.getFunctions();


        for( Response.Function function : functionList ) {

            Tool tool = toolsManager.getToolByName( function.getFunctionName() );

            JSONObject toolOutput = tool.runTool( function.getArg() );



            memory.addToolMessage( new ToolMessages( function.getFunctionName() , toolOutput ) );


        }

    }

}
