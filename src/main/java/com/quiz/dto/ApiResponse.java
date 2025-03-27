package com.quiz.dto;

public class ApiResponse {

    private final String msg;
    private final Boolean flag;
    private final Object data;
    private final Object error;

    public String getMsg() {
        return msg;
    }

    public Boolean getFlag() {
        return flag;
    }

    public Object getData() {
        return data;
    }

    public Object getError() {
        return error;
    }

    public ApiResponse(ApiResponseBuilder builder) {
        this.msg = builder.msg;
        this.flag = builder.flag;
        this.data = builder.data;
        this.error = builder.error;
    }

    public static class ApiResponseBuilder {

        private String msg;
        private Boolean flag;
        private Object data;
        private Object error;

        public ApiResponseBuilder() {
        }

        public ApiResponseBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public ApiResponseBuilder setFlag(Boolean flag) {
            this.flag = flag;
            return this;
        }

        public ApiResponseBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder setError(Object error) {
            this.error = error;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }
}
