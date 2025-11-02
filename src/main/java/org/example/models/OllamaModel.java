package org.example.models;

import org.example.client.ollamaClient.Request;
import org.example.memory.InMemory;
import org.example.messages.HumanMessages;
import org.example.messages.Messages;
import org.example.tools.OwnTools.Tool;
import org.example.tools.StructuredOllamaTool;
import org.example.tools.ToolsManager;
import org.json.JSONObject;

import java.util.Collection;
import java.util.List;

public class OllamaModel implements ChatModel {

    private String base_url = "http://localhost:11434";
    private String model ;
    private boolean stream = false;
    private ToolsManager toolsManager;

    public InMemory memory;



    private OllamaModel( Builder builder ) {

        this.base_url = builder.base_url;
        this.model = builder.modelName;
        this.stream = builder.isStream;
        this.memory = builder.chatMemory;
        this.toolsManager = builder.toolsManager;


    }

    public String getBase_url() {
        return base_url;
    }

    public String getModel() {

        return model;

    }

    public boolean isStream() {
        return stream;
    }

    public void updateMemory( InMemory memory ) {

        this.memory = memory;

    }

    public InMemory getMemory( ) {

        return memory;

    }

    public void addTool( List<Tool> tools ) {

        for( Tool tool : tools ) {

            toolsManager.addTools( tool.getToolName() , tool );

        }

    }

    public void addTool( Tool tool ) {

        toolsManager.addTools( tool.getToolName() , tool );

    }

    public String chat( HumanMessages message ) {

        memory.addHumanMessage( message );

        JSONObject requestObject = StructuredOllamaTool.toJSON( toolsManager.getToolsArray() , memory.getAllMessages() , this );

        try{

            return Request.sendRequest( requestObject , base_url );

        } catch( Exception e ) {

        }



        return "";

//        return "working";

    }



    public static Builder build( ) {

        return new Builder();

    }


    public static class Builder {

        private String base_url = "http://localhost:11434";
        private String modelName;
        private boolean isStream = false;
        private InMemory chatMemory;
        private ToolsManager toolsManager;

        public Builder baseURL( String base_url ) {

            this.base_url = base_url;
            return this;

        }

        public Builder model( String model ) {

            this.modelName = model;
            return this;

        }

        public Builder stream( boolean allowStream ) {

            this.isStream = allowStream;
            return this;

        }

        public Builder memory( InMemory chatMemory ) {

            this.chatMemory =  chatMemory;

            return this;
        }

        public Builder tools( List<Tool> tools ) {

            this.toolsManager = new ToolsManager();

            for( Tool tool : tools ) {

                toolsManager.addTools( tool.getToolName() , tool );

            }

            return this;

        }


        public OllamaModel build() throws IllegalArgumentException{

            if( modelName == null ) {

                throw new IllegalArgumentException( "Model is not initialized" );

            }

            return new OllamaModel( this );


        }


    }
}
