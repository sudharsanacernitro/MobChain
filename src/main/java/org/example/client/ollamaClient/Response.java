package org.example.client.ollamaClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class Response {

    private JSONObject message;
    private JSONArray toolsCalls;
    private JSONObject metaData;
    private String content;

    Response(JSONObject message, JSONArray toolCalls, JSONObject metaData, String content) {
        this.message = message;
        this.toolsCalls = toolCalls;
        this.metaData = metaData;
        this.content = content;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Response {\n");

        sb.append("  content: ").append(content != null ? "\"" + content + "\"" : "null").append(",\n");

        sb.append("  message: ").append(message != null ? message.toString(2) : "null").append(",\n");
        sb.append("  toolCalls: ").append(toolsCalls != null ? toolsCalls.toString(2) : "null").append(",\n");
        sb.append("  metaData: ").append(metaData != null ? metaData.toString(2) : "null").append("\n");

        sb.append("}");
        return sb.toString();
    }

    public static class Builder {

        private JSONObject message;
        private String content;
        private JSONArray toolCalls;
        private JSONObject metaData;

        Builder() { }

        public Builder parseOutput(String jsonOutput) {
            JSONObject root = new JSONObject(jsonOutput);

            this.message = root.getJSONObject("message");

            if (message != null && message.has("tool_calls")) {
                this.toolCalls = message.getJSONArray("tool_calls");
            }

            this.content = message.optString("content", null);

            this.metaData = new JSONObject();
            metaData.put("model", root.optString("model"));
            metaData.put("created_at", root.optString("created_at"));
            metaData.put("done", root.optBoolean("done"));
            metaData.put("done_reason", root.optString("done_reason"));
            metaData.put("total_duration", root.optLong("total_duration"));
            metaData.put("eval_count", root.optInt("eval_count"));
            metaData.put("load_duration", root.optLong("load_duration"));

            return this;
        }

        public Response build() {
            return new Response(this.message, this.toolCalls, this.metaData, this.content);
        }
    }
}
