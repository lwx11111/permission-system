package org.example.common.web;

import lombok.Data;

/**
 * @Author: lwx
 */
@Data
public class SimpleResponse {
    private Integer code = 200;
    private String message = "成功";
    private Object data;

    public static class SimpleResponseBuilder {
        private Integer code;
        private String message;
        private Object data;

        public SimpleResponseBuilder success(Object data) {
            this.code = 200;
            this.message = "成功";
            this.data = data;
            return this;
        }

        public SimpleResponseBuilder success() {
            this.code = 200;
            this.message = "成功";
            return this;
        }

        public SimpleResponseBuilder failure(String message) {
            this.code = 500;
            this.message = message;
            return this;
        }

        public SimpleResponse build() {
            SimpleResponse simpleResponse = new SimpleResponse();
            simpleResponse.setCode(code);
            simpleResponse.setMessage(message);
            simpleResponse.setData(data);
            return simpleResponse;
        }
    }

}
