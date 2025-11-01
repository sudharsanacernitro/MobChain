package org.example.models;

import java.nio.charset.StandardCharsets;

public class OllamaModel {

    private String base_url = "http://localhost:11434";
    private String model ;
    private boolean stream = false;

    private OllamaModel( Builder builder ) {

        this.base_url = builder.base_url;
        this.model = builder.model;
        this.stream = builder.stream;

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

    public static Builder build( ) {

        return new Builder();

    }


    public static class Builder {

        private String base_url = "http://localhost:11434";
        private String model ;
        private boolean stream = false;

        public Builder baseURL( String base_url ) {

            this.base_url = base_url;
            return this;

        }

        public Builder model( String model ) {

            this.model = model;
            return this;

        }

        public Builder stream( boolean allowStream ) {

            this.stream = allowStream;
            return this;

        }

        public OllamaModel build() throws IllegalArgumentException{

            if( model == null ) {

                throw new IllegalArgumentException( "Model is not initialized" );

            }

            return new OllamaModel( this );


        }
    }
}
